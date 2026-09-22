-- ==========================================================
-- 01_roles.sql: System Roles Seed Data
-- ==========================================================
-- Seeds default application roles for Role-Based Access Control (RBAC).

INSERT INTO roles (id, name, description)
VALUES 
  (1, 'ROLE_ADMIN', 'System Administrator with full management permissions'),
  (2, 'ROLE_USER', 'Standard individual user with basic portal access'),
  (3, 'ROLE_MANAGER', 'Manager role with supervisory and monitoring capabilities')
ON DUPLICATE KEY UPDATE description = VALUES(description);
