import React, { useState, useEffect } from 'react';
import {
  Compass,
  UserCheck,
  Plus,
  Send,
  CheckCircle,
  XCircle,
  MessageSquare,
  Tag,
  Phone,
  Mail,
  GraduationCap,
  Users,
  Search,
  X
} from 'lucide-react';
import matchmakingService from '../services/matchmakingService';
import teamService from '../services/teamService';
import { useAuth } from '../context/AuthContext';

export const MatchmakingPage = () => {
  const { user } = useAuth();
  const [activeTab, setActiveTab] = useState('feed'); // 'feed' | 'candidates' | 'my-requests'

  // Posts State
  const [posts, setPosts] = useState([]);
  const [loadingPosts, setLoadingPosts] = useState(true);
  const [postTypeFilter, setPostTypeFilter] = useState('');
  const [searchFilter, setSearchFilter] = useState('');

  // Candidates State
  const [candidates, setCandidates] = useState([]);
  const [loadingCandidates, setLoadingCandidates] = useState(false);

  // My Requests State
  const [myRequests, setMyRequests] = useState([]);
  const [loadingRequests, setLoadingRequests] = useState(false);

  // Modals
  const [createPostModalOpen, setCreatePostModalOpen] = useState(false);
  const [applyModalOpen, setApplyModalOpen] = useState(false);
  const [selectedPost, setSelectedPost] = useState(null);
  const [applyMessage, setApplyMessage] = useState('');

  // New Post Form
  const [postTitle, setPostTitle] = useState('');
  const [postGoals, setPostGoals] = useState('');
  const [postType, setPostType] = useState('STUDENT_LOOKING_FOR_TEAM');
  const [slotsNeeded, setSlotsNeeded] = useState(1);
  const [skillsInput, setSkillsInput] = useState('');
  const [contactInfo, setContactInfo] = useState('');

  useEffect(() => {
    fetchPosts();
  }, [postTypeFilter]);

  useEffect(() => {
    if (activeTab === 'candidates') {
      fetchCandidates();
    } else if (activeTab === 'my-requests') {
      fetchMyRequests();
    }
  }, [activeTab]);

  const fetchPosts = async () => {
    setLoadingPosts(true);
    try {
      const res = await matchmakingService.getPosts({
        type: postTypeFilter || undefined,
        query: searchFilter || undefined,
      });
      setPosts(res || []);
    } catch (err) {
      console.error(err);
    } finally {
      setLoadingPosts(false);
    }
  };

  const fetchCandidates = async () => {
    setLoadingCandidates(true);
    try {
      const res = await matchmakingService.getCandidates();
      setCandidates(res || []);
    } catch (err) {
      console.error(err);
    } finally {
      setLoadingCandidates(false);
    }
  };

  const fetchMyRequests = async () => {
    setLoadingRequests(true);
    try {
      const res = await matchmakingService.getMyJoinRequests();
      setMyRequests(res || []);
    } catch (err) {
      console.error(err);
    } finally {
      setLoadingRequests(false);
    }
  };

  const handleCreatePost = async (e) => {
    e.preventDefault();
    try {
      const skillsArray = skillsInput
        .split(',')
        .map((s) => s.trim())
        .filter((s) => s.length > 0);

      await matchmakingService.createPost({
        title: postTitle,
        goals: postGoals,
        postType,
        slotsNeeded: parseInt(slotsNeeded, 10),
        contactInfo,
        skills: skillsArray,
      });

      setCreatePostModalOpen(false);
      setPostTitle('');
      setPostGoals('');
      setSkillsInput('');
      setContactInfo('');
      fetchPosts();
    } catch (err) {
      alert(err.message || 'Failed to create post');
    }
  };

  const handleApplySubmit = async (e) => {
    e.preventDefault();
    if (!selectedPost) return;
    try {
      await matchmakingService.submitJoinRequest(selectedPost.id, {
        message: applyMessage,
      });
      alert('Application submitted successfully!');
      setApplyModalOpen(false);
      setSelectedPost(null);
      setApplyMessage('');
    } catch (err) {
      alert(err.message || 'Failed to submit application');
    }
  };

  return (
    <div className="space-y-6 animate-in fade-in duration-200">
      {/* Header */}
      <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4">
        <div>
          <h1 className="text-2xl font-bold text-slate-800 tracking-tight">Team Matchmaking</h1>
          <p className="text-sm text-slate-500 mt-0.5">
            Connect with students in your faculty and cohort to form graduation thesis teams.
          </p>
        </div>

        <button
          onClick={() => setCreatePostModalOpen(true)}
          className="px-4 py-2 bg-gradient-to-r from-[#da8cff] to-[#9a55ff] hover:opacity-95 text-white font-medium rounded-xl text-sm flex items-center space-x-2 transition-all shadow-xs self-start sm:self-auto"
        >
          <Plus className="w-4 h-4" />
          <span>Publish Post</span>
        </button>
      </div>

      {/* Tabs */}
      <div className="flex border-b border-slate-200 space-x-8 text-sm">
        <button
          onClick={() => setActiveTab('feed')}
          className={`py-3 font-semibold border-b-2 transition-colors ${
            activeTab === 'feed'
              ? 'border-[#b66dff] text-[#b66dff]'
              : 'border-transparent text-slate-500 hover:text-slate-700'
          }`}
        >
          Collaboration Feed
        </button>
        <button
          onClick={() => setActiveTab('candidates')}
          className={`py-3 font-semibold border-b-2 transition-colors ${
            activeTab === 'candidates'
              ? 'border-[#b66dff] text-[#b66dff]'
              : 'border-transparent text-slate-500 hover:text-slate-700'
          }`}
        >
          Student Candidates
        </button>
        <button
          onClick={() => setActiveTab('my-requests')}
          className={`py-3 font-semibold border-b-2 transition-colors ${
            activeTab === 'my-requests'
              ? 'border-[#b66dff] text-[#b66dff]'
              : 'border-transparent text-slate-500 hover:text-slate-700'
          }`}
        >
          My Applications
        </button>
      </div>

      {/* Tab 1: Collaboration Feed */}
      {activeTab === 'feed' && (
        <div className="space-y-4">
          {/* Filter Bar */}
          <div className="bg-white p-4 rounded-2xl border border-[#ebedf2] flex flex-col sm:flex-row gap-3">
            <div className="relative flex-1">
              <Search className="w-4 h-4 text-slate-400 absolute left-3 top-3" />
              <input
                type="text"
                placeholder="Search posts by skill, keywords..."
                value={searchFilter}
                onChange={(e) => setSearchFilter(e.target.value)}
                onKeyDown={(e) => e.key === 'Enter' && fetchPosts()}
                className="w-full pl-9 pr-3 py-2 bg-slate-50 border border-slate-200 rounded-xl text-sm text-slate-800 focus:outline-none focus:border-[#b66dff]"
              />
            </div>

            <div className="flex space-x-2">
              <select
                value={postTypeFilter}
                onChange={(e) => setPostTypeFilter(e.target.value)}
                className="px-3 py-2 bg-slate-50 border border-slate-200 rounded-xl text-sm text-slate-700 focus:outline-none focus:border-[#b66dff]"
              >
                <option value="">All Types</option>
                <option value="STUDENT_LOOKING_FOR_TEAM">Student Seeking Team</option>
                <option value="TEAM_LOOKING_FOR_MEMBER">Team Seeking Member</option>
              </select>

              <button
                onClick={fetchPosts}
                className="px-4 py-2 bg-slate-800 text-white rounded-xl text-sm font-medium hover:bg-slate-900"
              >
                Filter
              </button>
            </div>
          </div>

          {/* Posts List */}
          {loadingPosts ? (
            <div className="py-12 text-center text-slate-400">Loading matchmaking posts...</div>
          ) : posts.length === 0 ? (
            <div className="py-12 bg-white rounded-2xl border border-[#ebedf2] text-center">
              <Compass className="w-10 h-10 text-slate-300 mx-auto mb-2" />
              <p className="text-slate-600 font-medium">No posts found</p>
              <p className="text-xs text-slate-400 mt-1">Be the first to publish a collaboration post!</p>
            </div>
          ) : (
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              {posts.map((post) => (
                <div
                  key={post.id}
                  className="bg-white rounded-2xl border border-[#ebedf2] p-5 shadow-xs hover:shadow-md transition-all flex flex-col justify-between"
                >
                  <div>
                    <div className="flex items-start justify-between gap-2 mb-2">
                      <span
                        className={`text-xs px-2.5 py-0.5 rounded-full font-medium ${
                          post.postType === 'TEAM_LOOKING_FOR_MEMBER'
                            ? 'bg-indigo-50 text-indigo-700'
                            : 'bg-emerald-50 text-emerald-700'
                        }`}
                      >
                        {post.postType === 'TEAM_LOOKING_FOR_MEMBER' ? 'Team Seeking Member' : 'Student Seeking Team'}
                      </span>

                      <span className="text-[11px] text-slate-400 font-mono">
                        {post.createdAt?.replace('T', ' ').substring(0, 10)}
                      </span>
                    </div>

                    <h3 className="font-bold text-slate-800 text-base">{post.title}</h3>
                    <p className="text-xs text-slate-500 mt-1.5 leading-relaxed">{post.goals}</p>

                    {/* Skills */}
                    {post.skills && post.skills.length > 0 && (
                      <div className="flex flex-wrap gap-1 mt-3">
                        {post.skills.map((skill, idx) => (
                          <span
                            key={idx}
                            className="text-[11px] px-2 py-0.5 bg-slate-100 text-slate-600 rounded-md font-mono"
                          >
                            {skill}
                          </span>
                        ))}
                      </div>
                    )}

                    {/* Author & Meta */}
                    <div className="mt-4 pt-3 border-t border-slate-100 flex items-center justify-between text-xs text-slate-500">
                      <span>Author: {post.authorName}</span>
                      <span>Slots: {post.slotsNeeded} open</span>
                    </div>
                  </div>

                  <div className="mt-4 pt-3 border-t border-slate-100 flex items-center justify-between">
                    <span className="text-xs text-slate-400 font-mono truncate max-w-[200px]">
                      Contact: {post.contactInfo}
                    </span>

                    {post.authorId !== user?.id && post.status === 'OPEN' && (
                      <button
                        onClick={() => {
                          setSelectedPost(post);
                          setApplyModalOpen(true);
                        }}
                        className="px-3 py-1.5 bg-gradient-to-r from-[#da8cff] to-[#9a55ff] hover:opacity-95 text-white rounded-xl text-xs font-semibold flex items-center space-x-1 shadow-xs"
                      >
                        <Send className="w-3 h-3" />
                        <span>Apply</span>
                      </button>
                    )}
                  </div>
                </div>
              ))}
            </div>
          )}
        </div>
      )}

      {/* Tab 2: Candidates Directory */}
      {activeTab === 'candidates' && (
        <div className="space-y-4">
          {loadingCandidates ? (
            <div className="py-12 text-center text-slate-400">Loading candidates...</div>
          ) : candidates.length === 0 ? (
            <div className="py-12 bg-white rounded-2xl border border-[#ebedf2] text-center">
              <UserCheck className="w-10 h-10 text-slate-300 mx-auto mb-2" />
              <p className="text-slate-600 font-medium">No candidates currently listed</p>
            </div>
          ) : (
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
              {candidates.map((cand) => (
                <div
                  key={cand.userId}
                  className="bg-white rounded-2xl border border-[#ebedf2] p-5 shadow-xs hover:shadow-md transition-all flex flex-col justify-between"
                >
                  <div>
                    <div className="flex items-center space-x-3 mb-3">
                      <div className="w-10 h-10 rounded-full bg-slate-100 border border-slate-200 flex items-center justify-center font-bold text-slate-700 text-sm">
                        {cand.fullName?.charAt(0) || 'S'}
                      </div>
                      <div>
                        <h4 className="font-bold text-slate-800 text-sm">{cand.fullName}</h4>
                        <p className="text-xs text-slate-400 font-mono">MSSV: {cand.studentCode || 'N/A'}</p>
                      </div>
                    </div>

                    <div className="text-xs text-slate-500 space-y-1 mb-3">
                      <p>
                        <span className="font-semibold text-slate-600">Major:</span> {cand.major || 'Software Eng.'}
                      </p>
                      <p>
                        <span className="font-semibold text-slate-600">Cohort:</span> {cand.cohort || 'K21'}
                      </p>
                      {cand.goals && (
                        <p className="italic text-slate-600 line-clamp-2 mt-1">"{cand.goals}"</p>
                      )}
                    </div>

                    {cand.skills && cand.skills.length > 0 && (
                      <div className="flex flex-wrap gap-1">
                        {cand.skills.map((s, idx) => (
                          <span
                            key={idx}
                            className="text-[10px] px-2 py-0.5 bg-indigo-50 text-indigo-700 rounded-md font-mono"
                          >
                            {s}
                          </span>
                        ))}
                      </div>
                    )}
                  </div>

                  <div className="mt-4 pt-3 border-t border-slate-100 flex items-center justify-between text-xs">
                    <span className="text-slate-400 truncate max-w-[150px]">{cand.email}</span>
                  </div>
                </div>
              ))}
            </div>
          )}
        </div>
      )}

      {/* Tab 3: My Applications */}
      {activeTab === 'my-requests' && (
        <div className="bg-white rounded-2xl border border-[#ebedf2] overflow-hidden shadow-xs">
          <div className="p-5 border-b border-slate-100">
            <h3 className="font-bold text-slate-800 text-base">My Submitted Join Requests</h3>
            <p className="text-xs text-slate-400 mt-0.5">
              Applications you sent to collaborate with teams or peers
            </p>
          </div>

          {loadingRequests ? (
            <div className="p-8 text-center text-slate-400">Loading your applications...</div>
          ) : myRequests.length === 0 ? (
            <div className="p-8 text-center text-slate-400 text-sm">
              You haven't submitted any applications yet.
            </div>
          ) : (
            <div className="divide-y divide-slate-100">
              {myRequests.map((req) => (
                <div key={req.id} className="p-4 flex items-center justify-between hover:bg-slate-50/50">
                  <div>
                    <div className="flex items-center space-x-2">
                      <span className="font-bold text-slate-800 text-sm">{req.postTitle}</span>
                      <span
                        className={`text-xs px-2.5 py-0.5 rounded-full font-semibold ${
                          req.status === 'ACCEPTED'
                            ? 'bg-emerald-50 text-emerald-700'
                            : req.status === 'REJECTED'
                            ? 'bg-rose-50 text-rose-700'
                            : req.status === 'CANCELLED'
                            ? 'bg-slate-100 text-slate-500'
                            : 'bg-amber-50 text-amber-700'
                        }`}
                      >
                        {req.status}
                      </span>
                    </div>
                    {req.message && <p className="text-xs text-slate-500 mt-1 italic">"{req.message}"</p>}
                    <p className="text-[11px] text-slate-400 font-mono mt-1">
                      Submitted: {req.createdAt?.replace('T', ' ').substring(0, 16)}
                    </p>
                  </div>
                </div>
              ))}
            </div>
          )}
        </div>
      )}

      {/* Publish Post Modal */}
      {createPostModalOpen && (
        <div className="fixed inset-0 z-50 bg-slate-900/40 backdrop-blur-xs flex items-center justify-center p-4">
          <div className="bg-white w-full max-w-lg rounded-2xl border border-slate-200 shadow-xl overflow-hidden">
            <div className="p-6 border-b border-slate-100 flex items-center justify-between">
              <h3 className="font-bold text-slate-800">Publish Collaboration Post</h3>
              <button onClick={() => setCreatePostModalOpen(false)} className="text-slate-400 hover:text-slate-600">
                <X className="w-5 h-5" />
              </button>
            </div>

            <form onSubmit={handleCreatePost} className="p-6 space-y-4">
              <div>
                <label className="block text-xs font-semibold text-slate-600 mb-1">Post Type</label>
                <select
                  value={postType}
                  onChange={(e) => setPostType(e.target.value)}
                  className="w-full px-3 py-2 bg-slate-50 border border-slate-200 rounded-xl text-sm text-slate-800 focus:outline-none focus:border-[#b66dff]"
                >
                  <option value="STUDENT_LOOKING_FOR_TEAM">Student Seeking Team</option>
                  <option value="TEAM_LOOKING_FOR_MEMBER">Team Seeking Members</option>
                </select>
              </div>

              <div>
                <label className="block text-xs font-semibold text-slate-600 mb-1">Post Title</label>
                <input
                  type="text"
                  required
                  placeholder="e.g. Looking for frontend React developer for AI capstone"
                  value={postTitle}
                  onChange={(e) => setPostTitle(e.target.value)}
                  className="w-full px-3 py-2 bg-slate-50 border border-slate-200 rounded-xl text-sm text-slate-800 focus:outline-none focus:border-[#b66dff]"
                />
              </div>

              <div>
                <label className="block text-xs font-semibold text-slate-600 mb-1">Objectives & Motivation</label>
                <textarea
                  rows={3}
                  required
                  placeholder="Describe your project goal, direction, or team expectation..."
                  value={postGoals}
                  onChange={(e) => setPostGoals(e.target.value)}
                  className="w-full p-3 bg-slate-50 border border-slate-200 rounded-xl text-sm text-slate-800 focus:outline-none focus:border-[#b66dff]"
                />
              </div>

              <div>
                <label className="block text-xs font-semibold text-slate-600 mb-1">
                  Required Skills (comma-separated)
                </label>
                <input
                  type="text"
                  placeholder="React, Spring Boot, Python, Docker"
                  value={skillsInput}
                  onChange={(e) => setSkillsInput(e.target.value)}
                  className="w-full px-3 py-2 bg-slate-50 border border-slate-200 rounded-xl text-sm text-slate-800 focus:outline-none focus:border-[#b66dff]"
                />
              </div>

              <div className="grid grid-cols-2 gap-3">
                <div>
                  <label className="block text-xs font-semibold text-slate-600 mb-1">Slots Needed</label>
                  <input
                    type="number"
                    min="1"
                    max="3"
                    value={slotsNeeded}
                    onChange={(e) => setSlotsNeeded(e.target.value)}
                    className="w-full px-3 py-2 bg-slate-50 border border-slate-200 rounded-xl text-sm text-slate-800 focus:outline-none focus:border-[#b66dff]"
                  />
                </div>
                <div>
                  <label className="block text-xs font-semibold text-slate-600 mb-1">Contact Info</label>
                  <input
                    type="text"
                    required
                    placeholder="Zalo / Email / Discord"
                    value={contactInfo}
                    onChange={(e) => setContactInfo(e.target.value)}
                    className="w-full px-3 py-2 bg-slate-50 border border-slate-200 rounded-xl text-sm text-slate-800 focus:outline-none focus:border-[#b66dff]"
                  />
                </div>
              </div>

              <div className="pt-2 flex justify-end space-x-3">
                <button
                  type="button"
                  onClick={() => setCreatePostModalOpen(false)}
                  className="px-4 py-2 border border-slate-200 text-slate-600 rounded-xl text-sm font-medium hover:bg-slate-50"
                >
                  Cancel
                </button>
                <button
                  type="submit"
                  className="px-4 py-2 bg-gradient-to-r from-[#da8cff] to-[#9a55ff] hover:opacity-95 text-white rounded-xl text-sm font-semibold shadow-xs"
                >
                  Publish Post
                </button>
              </div>
            </form>
          </div>
        </div>
      )}

      {/* Apply to Post Modal */}
      {applyModalOpen && selectedPost && (
        <div className="fixed inset-0 z-50 bg-slate-900/40 backdrop-blur-xs flex items-center justify-center p-4">
          <div className="bg-white w-full max-w-md rounded-2xl border border-slate-200 shadow-xl overflow-hidden">
            <div className="p-6 border-b border-slate-100 flex items-center justify-between">
              <h3 className="font-bold text-slate-800">Apply to Collaboration Post</h3>
              <button onClick={() => setApplyModalOpen(false)} className="text-slate-400 hover:text-slate-600">
                <X className="w-5 h-5" />
              </button>
            </div>

            <form onSubmit={handleApplySubmit} className="p-6 space-y-4">
              <div>
                <label className="block text-xs font-semibold text-slate-600 mb-1">Target Post</label>
                <p className="text-sm font-semibold text-slate-800 p-2.5 bg-slate-50 border border-slate-200 rounded-xl">
                  {selectedPost.title}
                </p>
              </div>

              <div>
                <label className="block text-xs font-semibold text-slate-600 mb-1">
                  Introduction / Skills Pitch
                </label>
                <textarea
                  rows={4}
                  required
                  placeholder="Introduce yourself, key strengths, experience with technologies..."
                  value={applyMessage}
                  onChange={(e) => setApplyMessage(e.target.value)}
                  className="w-full p-3 bg-slate-50 border border-slate-200 rounded-xl text-sm text-slate-800 focus:outline-none focus:border-[#b66dff]"
                />
              </div>

              <div className="pt-2 flex justify-end space-x-3">
                <button
                  type="button"
                  onClick={() => setApplyModalOpen(false)}
                  className="px-4 py-2 border border-slate-200 text-slate-600 rounded-xl text-sm font-medium hover:bg-slate-50"
                >
                  Cancel
                </button>
                <button
                  type="submit"
                  className="px-4 py-2 bg-gradient-to-r from-[#da8cff] to-[#9a55ff] hover:opacity-95 text-white rounded-xl text-sm font-semibold shadow-xs"
                >
                  Submit Application
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default MatchmakingPage;
