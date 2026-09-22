import React, { useState, useEffect } from 'react';
import {
  Users,
  UserPlus,
  Crown,
  LogOut,
  Mail,
  Check,
  X,
  Edit2,
  AlertCircle,
  Shield,
  Layers
} from 'lucide-react';
import teamService from '../services/teamService';
import { useAuth } from '../context/AuthContext';

export const TeamsPage = () => {
  const { user } = useAuth();
  const [team, setTeam] = useState(null);
  const [invitations, setInvitations] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Modals state
  const [createModalOpen, setCreateModalOpen] = useState(false);
  const [newTeamName, setNewTeamName] = useState('');
  const [inviteModalOpen, setInviteModalOpen] = useState(false);
  const [inviteStudentCode, setInviteStudentCode] = useState('');
  const [inviteMessage, setInviteMessage] = useState('');
  const [actionSuccess, setActionSuccess] = useState(null);

  useEffect(() => {
    loadData();
  }, []);

  const loadData = async () => {
    setLoading(true);
    setError(null);
    try {
      // 1. Fetch team
      try {
        const teamRes = await teamService.getMyTeam();
        setTeam(teamRes || null);
      } catch (err) {
        // If 404 or no team, setTeam(null)
        setTeam(null);
      }

      // 2. Fetch pending invitations
      const invRes = await teamService.getMyInvitations();
      setInvitations(invRes || []);
    } catch (err) {
      setError(err.message || 'Failed to load team data');
    } finally {
      setLoading(false);
    }
  };

  const handleCreateTeam = async (e) => {
    e.preventDefault();
    if (!newTeamName.trim()) return;
    try {
      await teamService.createTeam({
        name: newTeamName,
        periodId: 1, // Active period default
      });
      setCreateModalOpen(false);
      setNewTeamName('');
      loadData();
    } catch (err) {
      alert(err.message || 'Failed to create team');
    }
  };

  const handleInviteMember = async (e) => {
    e.preventDefault();
    if (!inviteStudentCode.trim() || !team) return;
    try {
      await teamService.sendInvitation(team.id, {
        inviteeIdentifier: inviteStudentCode.trim(),
        message: inviteMessage,
      });
      setActionSuccess('Invitation sent successfully!');
      setTimeout(() => {
        setInviteModalOpen(false);
        setActionSuccess(null);
        setInviteStudentCode('');
        setInviteMessage('');
      }, 1500);
    } catch (err) {
      alert(err.message || 'Failed to send invitation');
    }
  };

  const handleRespondInvitation = async (invitationId, accept) => {
    try {
      await teamService.respondToInvitation(invitationId, accept);
      loadData();
    } catch (err) {
      alert(err.message || 'Failed to respond to invitation');
    }
  };

  const handleLeaveTeam = async () => {
    if (!team) return;
    if (!window.confirm('Are you sure you want to leave this team?')) return;
    try {
      await teamService.leaveTeam(team.id);
      loadData();
    } catch (err) {
      alert(err.message || 'Failed to leave team');
    }
  };

  const handleRemoveMember = async (memberUserId) => {
    if (!team) return;
    if (!window.confirm('Remove this member from your team?')) return;
    try {
      await teamService.removeMember(team.id, memberUserId);
      loadData();
    } catch (err) {
      alert(err.message || 'Failed to remove member');
    }
  };

  const isLeader = team && team.leaderId === user?.id;

  if (loading) {
    return <div className="py-16 text-center text-slate-400">Loading team information...</div>;
  }

  return (
    <div className="space-y-6 animate-in fade-in duration-200">
      {/* Page Header */}
      <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4">
        <div>
          <h1 className="text-2xl font-bold text-slate-800 tracking-tight">Team Management</h1>
          <p className="text-sm text-slate-500 mt-0.5">
            Form your capstone roster (up to 3 members), invite teammates, or manage team invitations.
          </p>
        </div>

        {!team && (
          <button
            onClick={() => setCreateModalOpen(true)}
            className="px-4 py-2 bg-gradient-to-r from-[#da8cff] to-[#9a55ff] hover:opacity-95 text-white font-medium rounded-xl text-sm flex items-center space-x-2 transition-all shadow-xs"
          >
            <Users className="w-4 h-4" />
            <span>Form New Team</span>
          </button>
        )}
      </div>

      {/* Has Team View */}
      {team ? (
        <div className="space-y-6">
          {/* Team Overview Card */}
          <div className="bg-white rounded-2xl border border-[#ebedf2] p-6 shadow-xs">
            <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4">
              <div className="flex items-center space-x-4">
                <div className="w-12 h-12 rounded-2xl bg-[#f8f2ff] border border-[#e1c2ff] flex items-center justify-center text-[#b66dff] font-bold text-lg">
                  {team.name?.charAt(0) || 'T'}
                </div>
                <div>
                  <div className="flex items-center space-x-2">
                    <h2 className="text-xl font-bold text-slate-800">{team.name}</h2>
                    <span className="text-xs px-2.5 py-0.5 rounded-full font-medium bg-emerald-50 text-emerald-700 border border-emerald-100">
                      {team.status || 'FORMING'}
                    </span>
                  </div>
                  <p className="text-xs text-slate-400 mt-0.5">
                    {team.members?.length || 1} / 3 members enrolled • Registration Period active
                  </p>
                </div>
              </div>

              <div className="flex items-center space-x-2">
                {isLeader && team.members?.length < 3 && (
                  <button
                    onClick={() => setInviteModalOpen(true)}
                    className="px-3.5 py-2 bg-[#f8f2ff] hover:bg-brand-100 text-[#b66dff] rounded-xl text-sm font-semibold flex items-center space-x-1.5 transition-colors"
                  >
                    <UserPlus className="w-4 h-4" />
                    <span>Invite Member</span>
                  </button>
                )}

                {!isLeader && (
                  <button
                    onClick={handleLeaveTeam}
                    className="px-3.5 py-2 bg-rose-50 hover:bg-rose-100 text-rose-700 rounded-xl text-sm font-semibold flex items-center space-x-1.5 transition-colors"
                  >
                    <LogOut className="w-4 h-4" />
                    <span>Leave Team</span>
                  </button>
                )}
              </div>
            </div>
          </div>

          {/* Members Roster */}
          <div className="bg-white rounded-2xl border border-[#ebedf2] overflow-hidden shadow-xs">
            <div className="p-5 border-b border-slate-100 flex items-center justify-between">
              <h3 className="font-bold text-slate-800 text-base">Team Roster ({team.members?.length || 0}/3)</h3>
              <span className="text-xs text-slate-400">Allowed: 1 to 3 members per project</span>
            </div>

            <div className="divide-y divide-slate-100">
              {team.members?.map((m) => {
                const isMemberLeader = m.roleInTeam === 'LEADER';

                return (
                  <div key={m.id} className="p-4 flex items-center justify-between hover:bg-slate-50/50 transition-colors">
                    <div className="flex items-center space-x-3.5">
                      <div className="w-10 h-10 rounded-full bg-slate-100 border border-slate-200 flex items-center justify-center font-bold text-slate-700 text-sm">
                        {m.fullName?.charAt(0) || m.username?.charAt(0) || 'U'}
                      </div>
                      <div>
                        <div className="flex items-center space-x-2">
                          <span className="font-semibold text-slate-800 text-sm">{m.fullName || m.username}</span>
                          {isMemberLeader && (
                            <span className="flex items-center space-x-1 text-[11px] font-semibold text-amber-700 bg-amber-50 px-2 py-0.5 rounded-full border border-amber-200">
                              <Crown className="w-3 h-3 text-amber-500" />
                              <span>Leader</span>
                            </span>
                          )}
                        </div>
                        <div className="flex items-center space-x-3 text-xs text-slate-400 mt-0.5 font-mono">
                          <span>MSSV: {m.studentCode || 'N/A'}</span>
                          <span>•</span>
                          <span>{m.email}</span>
                        </div>
                      </div>
                    </div>

                    {isLeader && !isMemberLeader && (
                      <button
                        onClick={() => handleRemoveMember(m.userId)}
                        className="p-1.5 rounded-lg text-slate-400 hover:text-rose-600 hover:bg-rose-50 transition-colors"
                        title="Remove member"
                      >
                        <X className="w-4 h-4" />
                      </button>
                    )}
                  </div>
                );
              })}
            </div>
          </div>
        </div>
      ) : (
        /* No Team View: Empty State + Pending Invitations Inbox */
        <div className="space-y-6">
          <div className="bg-white rounded-2xl border border-[#ebedf2] p-8 text-center max-w-xl mx-auto shadow-xs">
            <div className="w-12 h-12 rounded-2xl bg-[#f8f2ff] border border-[#e1c2ff] flex items-center justify-center text-[#b66dff] mx-auto mb-4">
              <Users className="w-6 h-6" />
            </div>
            <h2 className="text-lg font-bold text-slate-800">You Are Not in a Team</h2>
            <p className="text-xs text-slate-500 mt-1 max-w-md mx-auto leading-relaxed">
              To register a graduation capstone or thesis, you must either create a new team or accept an invitation from an existing team.
            </p>
            <div className="mt-5 flex justify-center space-x-3">
              <button
                onClick={() => setCreateModalOpen(true)}
                className="px-4 py-2 bg-gradient-to-r from-[#da8cff] to-[#9a55ff] hover:opacity-95 text-white rounded-xl text-sm font-semibold transition-all shadow-xs"
              >
                Form a New Team
              </button>
            </div>
          </div>

          {/* Pending Invitations Inbox */}
          <div className="bg-white rounded-2xl border border-[#ebedf2] overflow-hidden shadow-xs">
            <div className="p-5 border-b border-slate-100 flex items-center justify-between">
              <div className="flex items-center space-x-2">
                <Mail className="w-4 h-4 text-[#b66dff]" />
                <h3 className="font-bold text-slate-800 text-base">Pending Team Invitations ({invitations.length})</h3>
              </div>
              <span className="text-xs text-slate-400">Accepting one invitation automatically cancels other pending invites</span>
            </div>

            {invitations.length === 0 ? (
              <div className="p-8 text-center text-slate-400 text-sm">No pending team invitations.</div>
            ) : (
              <div className="divide-y divide-slate-100">
                {invitations.map((inv) => (
                  <div key={inv.id} className="p-4 flex flex-col sm:flex-row sm:items-center sm:justify-between gap-3 hover:bg-slate-50/50">
                    <div>
                      <div className="flex items-center space-x-2">
                        <span className="font-semibold text-slate-800 text-sm">{inv.teamName}</span>
                        <span className="text-[11px] px-2 py-0.5 rounded-md bg-indigo-50 text-indigo-700 font-mono">
                          Invited by {inv.inviterName}
                        </span>
                      </div>
                      {inv.message && <p className="text-xs text-slate-500 mt-1 italic">"{inv.message}"</p>}
                    </div>

                    <div className="flex items-center space-x-2">
                      <button
                        onClick={() => handleRespondInvitation(inv.id, true)}
                        className="px-3 py-1.5 bg-emerald-600 hover:bg-emerald-700 text-white rounded-xl text-xs font-semibold flex items-center space-x-1 shadow-xs"
                      >
                        <Check className="w-3.5 h-3.5" />
                        <span>Accept & Join</span>
                      </button>
                      <button
                        onClick={() => handleRespondInvitation(inv.id, false)}
                        className="px-3 py-1.5 bg-slate-100 hover:bg-slate-200 text-slate-600 rounded-xl text-xs font-medium"
                      >
                        Decline
                      </button>
                    </div>
                  </div>
                ))}
              </div>
            )}
          </div>
        </div>
      )}

      {/* Create Team Modal */}
      {createModalOpen && (
        <div className="fixed inset-0 z-50 bg-slate-900/40 backdrop-blur-xs flex items-center justify-center p-4">
          <div className="bg-white w-full max-w-md rounded-2xl border border-slate-200 shadow-xl overflow-hidden">
            <div className="p-6 border-b border-slate-100 flex items-center justify-between">
              <h3 className="font-bold text-slate-800">Form New Team</h3>
              <button onClick={() => setCreateModalOpen(false)} className="text-slate-400 hover:text-slate-600">
                <X className="w-5 h-5" />
              </button>
            </div>

            <form onSubmit={handleCreateTeam} className="p-6 space-y-4">
              <div>
                <label className="block text-xs font-semibold text-slate-600 mb-1">Team Name</label>
                <input
                  type="text"
                  required
                  placeholder="e.g. Nexus Software Team"
                  value={newTeamName}
                  onChange={(e) => setNewTeamName(e.target.value)}
                  className="w-full px-3 py-2 bg-slate-50 border border-slate-200 rounded-xl text-sm text-slate-800 focus:outline-none focus:border-[#b66dff]"
                />
              </div>

              <div className="pt-2 flex justify-end space-x-3">
                <button
                  type="button"
                  onClick={() => setCreateModalOpen(false)}
                  className="px-4 py-2 border border-slate-200 text-slate-600 rounded-xl text-sm font-medium hover:bg-slate-50"
                >
                  Cancel
                </button>
                <button
                  type="submit"
                  className="px-4 py-2 bg-gradient-to-r from-[#da8cff] to-[#9a55ff] hover:opacity-95 text-white rounded-xl text-sm font-semibold shadow-xs"
                >
                  Create Team
                </button>
              </div>
            </form>
          </div>
        </div>
      )}

      {/* Invite Member Modal */}
      {inviteModalOpen && (
        <div className="fixed inset-0 z-50 bg-slate-900/40 backdrop-blur-xs flex items-center justify-center p-4">
          <div className="bg-white w-full max-w-md rounded-2xl border border-slate-200 shadow-xl overflow-hidden">
            <div className="p-6 border-b border-slate-100 flex items-center justify-between">
              <h3 className="font-bold text-slate-800">Invite Student to Team</h3>
              <button onClick={() => setInviteModalOpen(false)} className="text-slate-400 hover:text-slate-600">
                <X className="w-5 h-5" />
              </button>
            </div>

            <form onSubmit={handleInviteMember} className="p-6 space-y-4">
              {actionSuccess ? (
                <div className="p-4 bg-emerald-50 text-emerald-700 border border-emerald-200 rounded-xl text-sm">
                  {actionSuccess}
                </div>
              ) : (
                <>
                  <div>
                    <label className="block text-xs font-semibold text-slate-600 mb-1">
                      Student Code (MSSV) or Username
                    </label>
                    <input
                      type="text"
                      required
                      placeholder="e.g. 21110001 or student_username"
                      value={inviteStudentCode}
                      onChange={(e) => setInviteStudentCode(e.target.value)}
                      className="w-full px-3 py-2 bg-slate-50 border border-slate-200 rounded-xl text-sm text-slate-800 focus:outline-none focus:border-[#b66dff]"
                    />
                  </div>

                  <div>
                    <label className="block text-xs font-semibold text-slate-600 mb-1">Invitation Message (Optional)</label>
                    <textarea
                      rows={3}
                      placeholder="Join our capstone team for topic..."
                      value={inviteMessage}
                      onChange={(e) => setInviteMessage(e.target.value)}
                      className="w-full p-3 bg-slate-50 border border-slate-200 rounded-xl text-sm text-slate-800 focus:outline-none focus:border-[#b66dff]"
                    />
                  </div>

                  <div className="pt-2 flex justify-end space-x-3">
                    <button
                      type="button"
                      onClick={() => setInviteModalOpen(false)}
                      className="px-4 py-2 border border-slate-200 text-slate-600 rounded-xl text-sm font-medium hover:bg-slate-50"
                    >
                      Cancel
                    </button>
                    <button
                      type="submit"
                      className="px-4 py-2 bg-gradient-to-r from-[#da8cff] to-[#9a55ff] hover:opacity-95 text-white rounded-xl text-sm font-semibold shadow-xs"
                    >
                      Send Invitation
                    </button>
                  </div>
                </>
              )}
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default TeamsPage;
