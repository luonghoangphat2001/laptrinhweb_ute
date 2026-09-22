-- ==========================================================
-- 01_roles.sql: System Roles Seed Data
-- ==========================================================
-- Seeds default application roles for Role-Based Access Control (RBAC).

INSERT INTO roles (id, name, description)
VALUES 
  (1, 'ROLE_ADMIN', 'System Administrator with full management permissions'),
  (2, 'ROLE_PRINCIPAL', 'Department Head / Dean / Principal managing graduation rounds and topics'),
  (3, 'ROLE_TEACHER', 'Academic Lecturer / Teacher advising and approving student topics'),
  (4, 'ROLE_COUNCIL', 'Defense Council Member evaluating and grading student theses'),
  (5, 'ROLE_USER', 'Student participating in teams, project registration, and submissions')
ON DUPLICATE KEY UPDATE description = VALUES(description);
