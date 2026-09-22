import api from './api';

export const matchmakingService = {
  getDashboard: async () => {
    const response = await api.get('/matchmaking/dashboard');
    return response.data;
  },

  getPosts: async (params = {}) => {
    const response = await api.get('/matchmaking/posts', { params });
    return response.data;
  },

  getPostById: async (id) => {
    const response = await api.get(`/matchmaking/posts/${id}`);
    return response.data;
  },

  createPost: async (postData) => {
    const response = await api.post('/matchmaking/posts', postData);
    return response.data;
  },

  closePost: async (id) => {
    const response = await api.patch(`/matchmaking/posts/${id}/close`);
    return response.data;
  },

  getCandidates: async (params = {}) => {
    const response = await api.get('/matchmaking/candidates', { params });
    return response.data;
  },

  submitJoinRequest: async (postId, data) => {
    const response = await api.post(`/matchmaking/posts/${postId}/join-requests`, data);
    return response.data;
  },

  getPostJoinRequests: async (postId) => {
    const response = await api.get(`/matchmaking/posts/${postId}/join-requests`);
    return response.data;
  },

  getMyJoinRequests: async () => {
    const response = await api.get('/matchmaking/join-requests/my-requests');
    return response.data;
  },

  reviewJoinRequest: async (requestId, decision) => {
    const response = await api.put(`/matchmaking/join-requests/${requestId}/review`, null, {
      params: { decision },
    });
    return response.data;
  },
};

export default matchmakingService;
