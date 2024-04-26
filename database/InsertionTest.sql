USE online_test;

INSERT INTO Test (TestID, TeacherID, Subject, Difficulty, StartDateTime, EndDateTime, Description, Accepted)
VALUES
  ('TEST006', 'T001', 'Java1', 'Medium', '2024-04-25 09:00:00', '2024-04-25 11:00:00', 'Java Basics Test', 0),
  ('TEST007', 'T002', 'Java2', 'Hard', '2024-04-27 13:00:00', '2024-04-27 15:00:00', 'Object-Oriented Programming Test', 1),
  ('TEST008', 'T002', 'Java3', 'Easy', '2024-04-29 10:00:00', '2024-04-29 12:00:00', 'Collections and Generics Test', 0),
  ('TEST009', 'T002', 'Java4', 'Medium', '2024-05-02 14:00:00', '2024-05-02 16:00:00', 'Exception Handling Test', 1),
  ('TEST010', 'T002', 'Java5', 'Hard', '2024-05-04 09:00:00', '2024-05-04 11:00:00', 'Multithreading Test', 0),
  ('TEST011', 'T001', 'Java6', 'Easy', '2024-05-06 13:00:00', '2024-05-06 15:00:00', 'Input/Output Test', 1),
  ('TEST012', 'T001', 'Java7', 'Medium', '2024-05-08 10:00:00', '2024-05-08 12:00:00', 'JDBC Test', 0),
  ('TEST013', 'T001', 'Java8', 'Hard', '2024-05-10 14:00:00', '2024-05-10 16:00:00', 'Networking Test', 1),
  ('TEST014', 'T001', 'Java9', 'Easy', '2024-05-12 09:00:00', '2024-05-12 11:00:00', 'Servlets and JSP Test', 0),
  ('TEST015', 'T002', 'Java10', 'Medium', '2024-05-14 13:00:00', '2024-05-14 15:00:00', 'Java EE Test', 1);