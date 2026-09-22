import api from './api';

export const registrationService = {
  submitRegistration: async (data) => {
    const response = await api.post('/registrations', data);
    return response.data;
  },

  getMyTeamRegistrations: async () => {
    const response = await api.get('/registrations/my-team');
    return response.data;
  },

  getTopicRegistrations: async (topicId) => {
    const response = await api.get(`/registrations/topic/${topicId}`);
    return response.data;
  },

  reviewRegistration: async (id, data) => {
    const response = await api.put(`/registrations/${id}/review`, data);
    return response.data;
  },

  cancelRegistration: async (id) => {
    const response = await api.delete(`/registrations/${id}`);
    return response.data;
  },
};

export default registrationService;
