-- ==========================================================
-- 04_periods_topics.sql: Registration Periods and Topics Seed Data
-- ==========================================================

-- 1. Registration Periods
INSERT INTO registration_periods (id, name, academic_year, semester, start_date, end_date, submission_deadline, status, created_at, updated_at) VALUES
  (1, 'Graduation Thesis Period Term 1 2026-2027', '2026-2027', 1, '2026-09-01 00:00:00', '2026-11-30 23:59:59', '2027-01-15 17:00:00', 'OPEN', NOW(6), NOW(6))
ON DUPLICATE KEY UPDATE name = VALUES(name);

-- 2. Registration Period Targeted Faculties (Applies to FIT)
INSERT INTO registration_period_faculties (period_id, faculty_id) VALUES
  (1, 1)
ON DUPLICATE KEY UPDATE period_id = VALUES(period_id);

-- 3. Registration Period Targeted Cohorts (Applies to K21)
INSERT INTO registration_period_cohorts (period_id, cohort_id) VALUES
  (1, 1)
ON DUPLICATE KEY UPDATE period_id = VALUES(period_id);

-- 4. Sample Topics
INSERT INTO topics (id, title, description, objectives, requirements, department_id, period_id, type, max_students, duration, status, is_registration_open, created_at, updated_at) VALUES
  (1, 'AI-Powered Automated Code Review and Security Vulnerability Scanner', 
   'Design and construct a microservice-based automated code scanning platform using Large Language Models and static code analysis to detect OWASP vulnerabilities.',
   'Deliver a functional web dashboard, automated git webhook trigger, and vulnerability scoring pipeline.',
   'Proficiency in Java Spring Boot, Python FastAPI, ReactJS, and foundational knowledge of AST parsing.',
   1, 1, 'GRADUATION_THESIS', 3, '15 weeks', 'OPEN', 1, NOW(6), NOW(6)),
  (2, 'Smart Campus IoT Environmental Monitoring and Energy Management System',
   'Develop an IoT telemetry collection and dashboard solution for university lecture halls and laboratories with real-time analytics.',
   'Build MQTT ingestion gateway, time-series visualization dashboard, and alert notification system.',
   'Experience with MQTT protocol, Spring Boot WebSocket, ReactJS, and time-series database concepts.',
   2, 1, 'CAPSTONE_PROJECT', 3, '15 weeks', 'OPEN', 1, NOW(6), NOW(6)),
  (3, 'Decentralized Academic Credential Verification Platform Using Blockchain',
   'Create an immutable academic transcript and diploma verification portal using Ethereum smart contracts.',
   'Implement student wallet integration, university issuer portal, and verifier QR verification flow.',
   'Solidity, Web3.js, Spring Boot, ReactJS, and cryptographic hashing.',
   3, 1, 'GRADUATION_THESIS', 2, '15 weeks', 'OPEN', 1, NOW(6), NOW(6))
ON DUPLICATE KEY UPDATE title = VALUES(title);

-- 5. Topic Lecturers (Teacher as Primary, Council Member as Co-Advisor)
-- User id 3 = teacher, User id 4 = council
INSERT INTO topic_lecturers (topic_id, lecturer_id, advisor_role, created_at, updated_at) VALUES
  (1, 3, 'PRIMARY', NOW(6), NOW(6)),
  (1, 4, 'CO_ADVISOR', NOW(6), NOW(6)),
  (2, 3, 'PRIMARY', NOW(6), NOW(6)),
  (3, 4, 'PRIMARY', NOW(6), NOW(6)),
  (3, 3, 'CO_ADVISOR', NOW(6), NOW(6))
ON DUPLICATE KEY UPDATE advisor_role = VALUES(advisor_role);

-- 6. Topic Majors (Open to Software Engineering and Information Systems)
-- Major id 1 = Software Engineering, Major id 2 = Information Systems
INSERT INTO topic_majors (topic_id, major_id) VALUES
  (1, 1),
  (1, 2),
  (2, 1),
  (2, 4),
  (3, 1),
  (3, 2)
ON DUPLICATE KEY UPDATE topic_id = VALUES(topic_id);
