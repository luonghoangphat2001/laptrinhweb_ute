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

-- 1. Insert or update default user accounts
INSERT INTO users (id, username, email, password, full_name, avatar_url, is_active, created_at, updated_at)
VALUES 
  (1, 'superadmin', 'superadmin@nexus.local', '$2a$10$mqOEWL36ye2QnsBKHuZYxecktptwsNxJ0e2ooJrCNdIuklI/mgvyS', 'Super Administrator', 'https://ui-avatars.com/api/?name=Super+Admin&background=6366f1&color=fff', 1, NOW(6), NOW(6)),
  (2, 'individual_user', 'individual@nexus.local', '$2a$10$KSu2nAwH5uJUoi7E/H8BSOiOGkCNB5BSY23xfaaAR5sny/O0T9Mva', 'Individual User', 'https://ui-avatars.com/api/?name=Individual+User&background=10b981&color=fff', 1, NOW(6), NOW(6))
ON DUPLICATE KEY UPDATE 
  password = VALUES(password),
  is_active = VALUES(is_active),
  full_name = VALUES(full_name),
  updated_at = NOW(6);

-- 2. Associate users with their respective roles
-- superadmin (user_id 1) -> ROLE_ADMIN (role_id 1)
-- individual_user (user_id 2) -> ROLE_USER (role_id 2)
INSERT IGNORE INTO user_roles (user_id, role_id)
VALUES 
  (1, 1),
  (2, 2);
