import React from 'react';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import { AuthProvider } from './context/AuthContext';
import { ProtectedRoute } from './routes/ProtectedRoute';
import { RoleBasedRoute } from './routes/RoleBasedRoute';
import { MainLayout } from './components/layout/MainLayout';
import { LoginPage } from './pages/LoginPage';
import { DashboardPage } from './pages/DashboardPage';
import { UsersPage } from './pages/UsersPage';
import { RolesPage } from './pages/RolesPage';
import { NotFoundPage } from './pages/NotFoundPage';

export const App = () => {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Routes>
          {/* Public Route */}
          <Route path="/login" element={<LoginPage />} />

          {/* Protected Admin Dashboard Routes */}
          <Route
            path="/"
            element={
              <ProtectedRoute>
                <MainLayout />
              </ProtectedRoute>
            }
          >
            <Route index element={<Navigate to="/dashboard" replace />} />
            <Route path="dashboard" element={<DashboardPage />} />
            <Route
              path="users"
              element={
                <RoleBasedRoute requiredRole="ROLE_ADMIN">
                  <UsersPage />
                </RoleBasedRoute>
              }
            />
            <Route
              path="roles"
              element={
                <RoleBasedRoute requiredRole="ROLE_ADMIN">
                  <RolesPage />
                </RoleBasedRoute>
              }
            />
            {/* Fallback for other nested paths */}
            <Route path="*" element={<NotFoundPage />} />
          </Route>

          {/* Global 404 */}
          <Route path="*" element={<NotFoundPage />} />
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
};

export default App;
