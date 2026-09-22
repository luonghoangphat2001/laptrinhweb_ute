-- ==========================================================
-- 05_teams_matchmaking.sql: Sample Teams and Matchmaking Collaboration Data
-- ==========================================================

-- 1. Student Team (Created by student id=4)
INSERT INTO teams (id, name, leader_id, period_id, faculty_id, cohort_id, major_id, status, created_at, updated_at) VALUES
  (1, 'Team Nexus Innovators', 4, 1, 1, 1, 1, 'FORMING', NOW(6), NOW(6))
ON DUPLICATE KEY UPDATE name = VALUES(name);

-- 2. Team Members (Leader: Alex Student)
INSERT INTO team_members (id, team_id, user_id, role_in_team, joined_at, created_at, updated_at) VALUES
  (1, 1, 4, 'LEADER', NOW(), NOW(6), NOW(6))
ON DUPLICATE KEY UPDATE role_in_team = VALUES(role_in_team);

-- 3. Matchmaking Posts
-- Post 1: Team 1 Looking for 1 Backend/DevOps Member
INSERT INTO matchmaking_posts (id, post_type, author_id, team_id, title, goals, department_id, faculty_id, cohort_id, slots_needed, contact_info, status, created_at, updated_at) VALUES
  (1, 'TEAM_LOOKING_FOR_MEMBER', 4, 1, 
   'Team Nexus looking for 1 Fullstack / DevOps member for AI Code Scanner thesis',
   'Aiming for excellent grade on AI and Cloud-native software engineering capstone project.',
   1, 1, 1, 1, 'Email: student@nexus.edu.vn / Phone: 0901000004', 'OPEN', NOW(6), NOW(6)),
  (2, 'STUDENT_LOOKING_FOR_TEAM', 7, NULL,
   'Chloe (IS K21) looking for ambitious team working on Data Analytics or Information Systems',
   'Passionate about business process modeling, SQL query tuning, and interactive dashboards.',
   3, 1, 1, 1, 'Email: student3@nexus.edu.vn / Telegram: @chloe_nexus', 'OPEN', NOW(6), NOW(6))
ON DUPLICATE KEY UPDATE title = VALUES(title);

-- 4. Matchmaking Post Skills
INSERT INTO matchmaking_post_skills (post_id, skill) VALUES
  (1, 'Spring Boot'),
  (1, 'ReactJS'),
  (1, 'Docker'),
  (2, 'SQL'),
  (2, 'PowerBI'),
  (2, 'Python')
ON DUPLICATE KEY UPDATE skill = VALUES(skill);

-- 5. Team Join Request (Student 2 applies to Team 1's post)
INSERT INTO team_join_requests (id, post_id, applicant_id, faculty_id, cohort_id, message, status, created_at, updated_at) VALUES
  (1, 1, 6, 1, 1, 'Hi Alex, I am Brian from SE K21. I have solid experience with Docker and Spring Boot microservices. Would love to join Nexus!', 'PENDING', NOW(6), NOW(6))
ON DUPLICATE KEY UPDATE message = VALUES(message);
