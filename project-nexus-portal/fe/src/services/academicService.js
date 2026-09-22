import api from './api';

export const academicService = {
  getAllFaculties: async () => {
    const response = await api.get('/academic/faculties');
    return response.data;
  },

  getDepartmentsByFaculty: async (facultyId) => {
    if (!facultyId) return [];
    const response = await api.get(`/academic/departments?facultyId=${facultyId}`);
    return response.data;
  },

  getAllDepartments: async () => {
    const response = await api.get('/academic/departments/all');
    return response.data;
  },

  getMajorsByDepartment: async (departmentId) => {
    if (!departmentId) return [];
    const response = await api.get(`/academic/majors?departmentId=${departmentId}`);
    return response.data;
  },

  getAllMajors: async () => {
    const response = await api.get('/academic/majors/all');
    return response.data;
  },

  getAllCohorts: async () => {
    const response = await api.get('/academic/cohorts');
    return response.data;
  },
};

export default academicService;
