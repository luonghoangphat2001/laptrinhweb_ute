import api from './api';

export const teamService = {
  getMyTeam: async (periodId) => {
    const params = periodId ? { periodId } : {};
    const response = await api.get('/teams/my-team', { params });
    return response.data;
  },

  getTeamById: async (id) => {
    const response = await api.get(`/teams/${id}`);
    return response.data;
  },

  createTeam: async (data) => {
    const response = await api.post('/teams', data);
    return response.data;
  },

  updateTeamName: async (id, name) => {
    const response = await api.patch(`/teams/${id}/name`, null, {
      params: { name },
    });
    return response.data;
  },

  sendInvitation: async (teamId, data) => {
    const response = await api.post(`/teams/${teamId}/invitations`, data);
    return response.data;
  },

  getMyInvitations: async () => {
    const response = await api.get('/teams/invitations/my-invitations');
    return response.data;
  },

  respondToInvitation: async (invitationId, accept) => {
    const response = await api.post(`/teams/invitations/${invitationId}/respond`, null, {
      params: { accept },
    });
    return response.data;
  },

  removeMember: async (teamId, memberUserId) => {
    const response = await api.delete(`/teams/${teamId}/members/${memberUserId}`);
    return response.data;
  },

  leaveTeam: async (teamId) => {
    const response = await api.post(`/teams/${teamId}/leave`);
    return response.data;
  },

  transferLeader: async (teamId, newLeaderUserId) => {
    const response = await api.post(`/teams/${teamId}/transfer-leader`, null, {
      params: { newLeaderUserId },
    });
    return response.data;
  },
};

export default teamService;
