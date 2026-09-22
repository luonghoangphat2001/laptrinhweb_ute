import React, { useEffect, useState } from 'react';
import api from '../services/api';
import { useAuth } from '../context/AuthContext';
import {
  Users,
  UserCheck,
  UserX,
  Search,
  Shield,
  RefreshCw,
  AlertTriangle,
  GraduationCap,
  Building2,
  Mail,
  Calendar
} from 'lucide-react';

export const UsersPage = () => {
  const { hasRole } = useAuth();
  const isAdmin = hasRole('ROLE_ADMIN');
  const isPrincipal = hasRole('ROLE_PRINCIPAL');
  const isTeacher = hasRole('ROLE_TEACHER');

  const [activeTab, setActiveTab] = useState(isAdmin ? 'all' : 'lecturers');
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [searchQuery, setSearchQuery] = useState('');

  const fetchUsers = async () => {
    setLoading(true);
    setError(null);
    try {
      if (activeTab === 'lecturers' || !isAdmin) {
        // Fetch scoped lecturers
        const res = await api.get('/users/lecturers');
        if (res && res.data) {
          setUsers(Array.isArray(res.data) ? res.data : []);
        } else {
          setUsers([]);
        }
      } else {
        // Fetch all users for Admin
        const res = await api.get('/users?page=0&size=50');
        if (res && res.data && res.data.content) {
          setUsers(res.data.content);
        } else if (res && res.data) {
          setUsers(Array.isArray(res.data) ? res.data : []);
        } else {
          setUsers([]);
        }
      }
    } catch (err) {
      setError(err.message);
      setUsers([]);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchUsers();
  }, [activeTab]);

  const handleToggleStatus = async (id) => {
    if (!isAdmin) return;
    try {
      await api.patch(`/users/${id}/toggle-status`);
      fetchUsers();
    } catch (err) {
      alert(`Could not toggle user status: ${err.message}`);
    }
  };

  const filteredUsers = users.filter((u) => {
    const q = searchQuery.toLowerCase();
    const username = u.username ? u.username.toLowerCase() : '';
    const fullName = u.fullName ? u.fullName.toLowerCase() : '';
    const email = u.email ? u.email.toLowerCase() : '';
    return username.includes(q) || fullName.includes(q) || email.includes(q);
  });

  return (
    <div className="space-y-6 animate-in fade-in duration-200">
      {/* Header section */}
      <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4">
        <div>
          <h1 className="text-2xl font-bold text-[#343a40] tracking-tight flex items-center space-x-2">
            <Users className="w-6 h-6 text-[#b66dff]" />
            <span>{isAdmin && activeTab === 'all' ? 'User & Account Management' : 'Teaching Staff Directory'}</span>
          </h1>
          <p className="text-slate-500 text-xs mt-1">
            {isAdmin && activeTab === 'all'
              ? 'Manage system administrators, faculty heads, teachers, and student accounts.'
              : isPrincipal
              ? 'Khoa / Bộ môn Kỹ thuật phần mềm: Trưởng bộ môn chỉ quản lý và xem giảng viên thuộc đơn vị phụ trách.'
              : isTeacher
              ? 'Giảng viên hướng dẫn: Chỉ hiển thị giảng viên phụ trách & giảng viên đồng hướng dẫn liên quan.'
              : 'University academic faculty and thesis advisory committee members.'}
          </p>
        </div>

        <div className="flex items-center space-x-3">
          {/* Admin Tab Switcher */}
          {isAdmin && (
            <div className="bg-slate-100 p-1 rounded-xl flex items-center space-x-1 border border-slate-200">
              <button
                onClick={() => setActiveTab('all')}
                className={`px-3 py-1.5 rounded-lg text-xs font-semibold transition-all ${
                  activeTab === 'all'
                    ? 'bg-white text-[#b66dff] shadow-xs'
                    : 'text-slate-600 hover:text-slate-800'
                }`}
              >
                All Accounts
              </button>
              <button
                onClick={() => setActiveTab('lecturers')}
                className={`px-3 py-1.5 rounded-lg text-xs font-semibold transition-all ${
                  activeTab === 'lecturers'
                    ? 'bg-white text-[#b66dff] shadow-xs'
                    : 'text-slate-600 hover:text-slate-800'
                }`}
              >
                Teaching Staff
              </button>
            </div>
          )}

          <button
            onClick={fetchUsers}
            disabled={loading}
            className="px-3.5 py-2 bg-white border border-slate-200 hover:bg-slate-50 text-slate-700 rounded-xl text-xs font-semibold flex items-center space-x-1.5 transition-colors cursor-pointer shadow-xs"
          >
            <RefreshCw className={`w-3.5 h-3.5 ${loading ? 'animate-spin text-[#b66dff]' : ''}`} />
            <span>Refresh</span>
          </button>
        </div>
      </div>

      {/* Scope Banner for Principal and Teacher */}
      {(isPrincipal || isTeacher) && (
        <div className="p-4 rounded-xl bg-gradient-to-r from-[#f8f2ff] to-[#f4f7fa] border border-[#da8cff]/30 flex items-center space-x-3">
          <div className="w-10 h-10 rounded-lg bg-gradient-to-r from-[#da8cff] to-[#9a55ff] flex items-center justify-center text-white shrink-0 shadow-xs">
            <GraduationCap className="w-5 h-5" />
          </div>
          <div className="flex-1">
            <h3 className="text-xs font-bold text-[#7922cc] uppercase tracking-wider">
              {isPrincipal ? 'Department Scope: Software Engineering (Bộ môn Kỹ thuật phần mềm)' : 'Supervision Scope: Assigned Advisors'}
            </h3>
            <p className="text-xs text-slate-600 mt-0.5">
              {isPrincipal
                ? 'Quyền hạn Trưởng bộ môn: Giới hạn nghiêm ngặt trong phạm vi Bộ môn KTPM. Không thể xem thông tin ngoài phạm vi.'
                : 'Quyền hạn Giảng viên: Bạn chỉ xem được thông tin của chính mình và giảng viên đồng hướng dẫn đề tài.'}
            </p>
          </div>
          <span className="px-3 py-1 rounded-full text-xs font-bold bg-white text-[#b66dff] border border-[#ebedf2] shadow-xs">
            {isPrincipal ? 'Trưởng bộ môn KTPM' : 'Giảng viên HD'}
          </span>
        </div>
      )}

      {/* Error Banner when Backend request fails */}
      {error && (
        <div className="p-4 rounded-2xl bg-rose-50 border border-rose-200 flex items-start space-x-3 text-rose-700 text-sm">
          <AlertTriangle className="w-5 h-5 shrink-0 mt-0.5" />
          <div>
            <p className="font-semibold">API Connection Error</p>
            <p className="text-xs mt-0.5">{error}</p>
          </div>
        </div>
      )}

      {/* Filter toolbar */}
      <div className="bg-white border border-[#ebedf2] rounded-xl p-4 flex items-center justify-between shadow-xs">
        <div className="relative w-full max-w-sm">
          <Search className="w-4 h-4 text-slate-400 absolute left-3.5 top-3 pointer-events-none" />
          <input
            type="text"
            placeholder="Search by name, email, or username..."
            value={searchQuery}
            onChange={(e) => setSearchQuery(e.target.value)}
            className="w-full bg-[#f4f7fa] border border-[#ebedf2] rounded-lg pl-10 pr-4 py-2 text-xs text-slate-800 placeholder-slate-400 focus:outline-none focus:border-[#b66dff]"
          />
        </div>
        <span className="text-xs text-slate-400 font-mono hidden sm:inline">
          Showing {filteredUsers.length} staff record(s)
        </span>
      </div>

      {/* Data Table */}
      <div className="bg-white border border-[#ebedf2] rounded-xl overflow-hidden shadow-xs">
        <div className="overflow-x-auto">
          <table className="w-full text-left text-xs text-slate-700">
            <thead className="bg-[#f4f7fa] uppercase font-semibold text-slate-500 border-b border-[#ebedf2]">
              <tr>
                <th className="px-6 py-3.5">Staff Details</th>
                <th className="px-6 py-3.5">Assigned Roles</th>
                <th className="px-6 py-3.5">Status</th>
                <th className="px-6 py-3.5">Joined Date</th>
                {isAdmin && <th className="px-6 py-3.5 text-right">Actions</th>}
              </tr>
            </thead>
            <tbody className="divide-y divide-slate-100">
              {loading ? (
                <tr>
                  <td colSpan={isAdmin ? 5 : 4} className="px-6 py-8 text-center text-slate-400">
                    <div className="flex items-center justify-center space-x-2">
                      <RefreshCw className="w-4 h-4 animate-spin text-[#b66dff]" />
                      <span>Loading records...</span>
                    </div>
                  </td>
                </tr>
              ) : filteredUsers.length > 0 ? (
                filteredUsers.map((user) => (
                  <tr key={user.id} className="hover:bg-slate-50/70 transition-colors">
                    <td className="px-6 py-4">
                      <div className="flex items-center space-x-3">
                        <div className="w-9 h-9 rounded-lg bg-gradient-to-r from-[#da8cff] to-[#9a55ff] flex items-center justify-center text-white font-bold text-xs shadow-xs">
                          {user.fullName ? user.fullName.charAt(0) : user.username ? user.username.charAt(0) : 'U'}
                        </div>
                        <div>
                          <div className="font-bold text-[#343a40] text-xs">{user.fullName || user.username}</div>
                          <div className="text-[11px] text-slate-400 flex items-center space-x-1.5 mt-0.5">
                            <Mail className="w-3 h-3 text-slate-400" />
                            <span>{user.email}</span>
                          </div>
                        </div>
                      </div>
                    </td>
                    <td className="px-6 py-4">
                      <div className="flex flex-wrap gap-1.5">
                        {user.roles &&
                          user.roles.map((role) => (
                            <span
                              key={role}
                              className="inline-flex items-center space-x-1 px-2.5 py-0.5 rounded-md text-[11px] font-mono font-semibold bg-[#f8f2ff] text-[#b66dff] border border-[#da8cff]/40"
                            >
                              <Shield className="w-2.5 h-2.5" />
                              <span>{role.replace('ROLE_', '')}</span>
                            </span>
                          ))}
                      </div>
                    </td>
                    <td className="px-6 py-4">
                      {user.active ? (
                        <span className="inline-flex items-center px-2 py-0.5 rounded-full text-[11px] font-semibold bg-emerald-50 text-emerald-600 border border-emerald-200">
                          Active
                        </span>
                      ) : (
                        <span className="inline-flex items-center px-2 py-0.5 rounded-full text-[11px] font-semibold bg-rose-50 text-rose-600 border border-rose-200">
                          Suspended
                        </span>
                      )}
                    </td>
                    <td className="px-6 py-4 text-slate-500 font-mono text-[11px]">
                      {user.createdAt ? new Date(user.createdAt).toLocaleDateString('vi-VN') : 'N/A'}
                    </td>
                    {isAdmin && (
                      <td className="px-6 py-4 text-right">
                        <button
                          onClick={() => handleToggleStatus(user.id)}
                          className="p-1.5 text-slate-400 hover:text-slate-700 hover:bg-slate-100 rounded-lg transition-colors cursor-pointer"
                          title={user.active ? 'Deactivate account' : 'Activate account'}
                        >
                          {user.active ? (
                            <UserX className="w-4 h-4 text-rose-500" />
                          ) : (
                            <UserCheck className="w-4 h-4 text-emerald-600" />
                          )}
                        </button>
                      </td>
                    )}
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan={isAdmin ? 5 : 4} className="px-6 py-8 text-center text-slate-400">
                    {error ? 'Could not load data due to an error.' : 'No staff matching criteria.'}
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default UsersPage;
