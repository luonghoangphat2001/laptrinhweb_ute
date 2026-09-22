-- ==========================================================
-- 02_users.sql: Default User Accounts & Role Mapping
-- ==========================================================
-- Seed accounts:
--   1. Administrator:
--      - Username: superadmin
--      - Email: superadmin@nexus.local
--      - Password: password123 (BCrypt 10 rounds)
--      - Role: ROLE_ADMIN
--
--   2. Individual User:
--      - Username: individual_user
--      - Email: individual@nexus.local
--      - Password: password123 (BCrypt 10 rounds)
--      - Role: ROLE_USER
-- ==========================================================

-- 1. Insert or update default user accounts (Default Password for all: password123)
INSERT INTO users (id, username, email, password, full_name, avatar_url, is_active, created_at, updated_at)
VALUES 
  (1, 'superadmin', 'superadmin@nexus.edu.vn', '$2a$10$mqOEWL36ye2QnsBKHuZYxecktptwsNxJ0e2ooJrCNdIuklI/mgvyS', 'System Administrator', 'https://ui-avatars.com/api/?name=Super+Admin&background=6366f1&color=fff', 1, NOW(6), NOW(6)),
  (2, 'principal', 'principal@nexus.edu.vn', '$2a$10$mqOEWL36ye2QnsBKHuZYxecktptwsNxJ0e2ooJrCNdIuklI/mgvyS', 'Dr. Principal Dean', 'https://ui-avatars.com/api/?name=Principal+Dean&background=f59e0b&color=fff', 1, NOW(6), NOW(6)),
  (3, 'teacher', 'teacher@nexus.edu.vn', '$2a$10$mqOEWL36ye2QnsBKHuZYxecktptwsNxJ0e2ooJrCNdIuklI/mgvyS', 'Prof. Academic Teacher', 'https://ui-avatars.com/api/?name=Teacher+Advisor&background=3b82f6&color=fff', 1, NOW(6), NOW(6)),
  (4, 'student', 'student@nexus.edu.vn', '$2a$10$mqOEWL36ye2QnsBKHuZYxecktptwsNxJ0e2ooJrCNdIuklI/mgvyS', 'Alex Student', 'https://ui-avatars.com/api/?name=Alex+Student&background=10b981&color=fff', 1, NOW(6), NOW(6)),
  (5, 'council', 'council@nexus.edu.vn', '$2a$10$mqOEWL36ye2QnsBKHuZYxecktptwsNxJ0e2ooJrCNdIuklI/mgvyS', 'Prof. Council Evaluator', 'https://ui-avatars.com/api/?name=Council+Judge&background=8b5cf6&color=fff', 1, NOW(6), NOW(6))
ON DUPLICATE KEY UPDATE 
  password = VALUES(password),
  is_active = VALUES(is_active),
  full_name = VALUES(full_name),
  updated_at = NOW(6);

-- 2. Associate users with their respective roles
DELETE FROM user_roles WHERE user_id IN (1, 2, 3, 4, 5);

INSERT IGNORE INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u, roles r WHERE u.username = 'superadmin' AND r.name = 'ROLE_ADMIN';

INSERT IGNORE INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u, roles r WHERE u.username = 'principal' AND r.name = 'ROLE_PRINCIPAL';

INSERT IGNORE INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u, roles r WHERE u.username = 'teacher' AND r.name = 'ROLE_TEACHER';

INSERT IGNORE INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u, roles r WHERE u.username = 'council' AND r.name = 'ROLE_COUNCIL';

INSERT IGNORE INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u, roles r WHERE u.username = 'student' AND r.name = 'ROLE_USER';
