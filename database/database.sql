drop database if exists online_test;
create database if not exists online_test;

use online_test;

-- Creating base tables
create table Student(SRN varchar(50) primary key, Branch varchar(50), Name varchar(50), Password varchar(50));

create table Admin(ID varchar(50) primary key, Name varchar(50), Password varchar(50));

create table Teacher(ID varchar(50) primary key, Name varchar(50), Password varchar(50), Branch varchar(50));

create table Test (
    TestID varchar(50) primary key,
    TeacherID varchar(50),
    Subject VARCHAR(50),
    Difficulty ENUM("Easy", "Medium", "Hard"),
    StartDateTime DATETIME,
    EndDateTime DATETIME,
    Description varchar(500),
    Accepted bool
);

CREATE TABLE Questions (
    questionID VARCHAR(50) PRIMARY KEY,
    question VARCHAR(100),
    option1 VARCHAR(100),
    option2 VARCHAR(100),
    option3 VARCHAR(100),
    option4 VARCHAR(100),
    correct_option ENUM("a", "b", "c", "d"),
    marks INT
);

CREATE TABLE Test_Questions (
    testId VARCHAR(50),
    questionId VARCHAR(50),
    PRIMARY KEY (testId, questionId),
    FOREIGN KEY (testId) REFERENCES Test(TestID),
    FOREIGN KEY (questionId) REFERENCES Questions(questionID)
);

CREATE TABLE Student_Test (
    SRN VARCHAR(50),
    TestID varchar(50),
    MarksSecured INT,
    PRIMARY KEY (SRN, TestID)
);

CREATE TABLE Student_Answers (
    SRN VARCHAR(50),
    testId VARCHAR(50),
    questionId VARCHAR(50),
    markedAnswer ENUM("a", "b", "c", "d"),
    reviewComments varchar(100),
    PRIMARY KEY (SRN, testId, questionId),
    FOREIGN KEY (SRN) REFERENCES Student(SRN),
    FOREIGN KEY (testId) REFERENCES Test_Questions(testId),
    FOREIGN KEY (questionId) REFERENCES Questions(questionId)
);

 -- Altering tables

alter table Test add foreign key (TeacherID) references Teacher(ID);
 
alter table Student_Test add foreign key (SRN) references Student(SRN);
alter table Student_Test add foreign key (TestID) references Test(TestID);

ALTER TABLE Test ADD CONSTRAINT time_constraint CHECK(StartDateTime < EndDateTime);

-- Insert into Student table
-- Student table
INSERT INTO Student (SRN, Branch, Name, Password)
VALUES
    ('SRN001', 'Computer Science', 'John Doe', 'password123'),
    ('SRN002', 'Electrical Engineering', 'Jane Smith', 'securepass'),
    ('SRN003', 'Mechanical Engineering', 'Bob Johnson', '123456');

-- Admin table
INSERT INTO Admin (ID, Name, Password)
VALUES
    ('AD001', 'Admin1', 'adminpass1'),
    ('AD002', 'Admin2', 'adminpass2');

-- Teacher table
INSERT INTO Teacher (ID, Name, Password, Branch)
VALUES
    ('T001', 'Teacher1', 'teacherpass1', 'Computer Science'),
    ('T002', 'Teacher2', 'teacherpass2', 'Electrical Engineering');

-- Test table
INSERT INTO Test (TestID, TeacherID, StartDateTime, EndDateTime, Description, Accepted)
VALUES
    ('TEST001', 'T001', '2024-04-12 08:00:00', '2024-04-12 10:00:00', 'Test description 1', true),
    ('TEST002', 'T002', '2024-04-12 10:00:00', '2024-04-12 12:00:00', 'Test description 2', false);

-- Student_Test table
INSERT INTO Student_Test (SRN, TestID, MarksSecured)
VALUES
    ('SRN001', 'TEST001', 80),
    ('SRN002', 'TEST001', 75),
    ('SRN003', 'TEST001', 70),
    ('SRN001', 'TEST002', 85),
    ('SRN002', 'TEST002', 90),
    ('SRN003', 'TEST002', 95);

-- Test table (continued)
INSERT INTO Test (TestID, TeacherID, StartDateTime, EndDateTime, Description, Accepted)
VALUES
    ('TEST003', 'T001', '2024-04-12 09:00:00', '2024-04-12 11:00:00', 'Test description 3', true),
    ('TEST004', 'T002', '2024-04-12 11:00:00', '2024-04-12 13:00:00', 'Test description 4', false);

-- Student_Test table (continued)
INSERT INTO Student_Test (SRN, TestID, MarksSecured)
VALUES
    ('SRN001', 'TEST003', 78),
    ('SRN002', 'TEST003', 80),
    ('SRN003', 'TEST003', 85),
    ('SRN001', 'TEST004', 92),
    ('SRN002', 'TEST004', 88),
    ('SRN003', 'TEST004', 90);


INSERT INTO questions (questionID, question, option1, option2, option3, option4, correct_option, marks)
VALUES ('Q001', 'What is the entry point of a Java program?', 'main() method', 'start() method', 'run() method', 'init() method', 'a', 2);

INSERT INTO questions (questionID, question, option1, option2, option3, option4, correct_option, marks)
VALUES ('Q002', 'Which of the following is not an access modifier in Java?', 'public', 'private', 'protected', 'global', 'd', 1);

INSERT INTO questions (questionID, question, option1, option2, option3, option4, correct_option, marks)
VALUES ('Q003', 'What is the purpose of the "super" keyword in Java?', 'To call a constructor of the parent class', 'To call a method of the parent class', 'Both A and B', 'None of the above', 'c', 3);

INSERT INTO questions (questionID, question, option1, option2, option3, option4, correct_option, marks)
VALUES ('Q004', 'Which of the following is not a Java primitive data type?', 'boolean', 'char', 'string', 'double', 'c', 2);

INSERT INTO questions (questionID, question, option1, option2, option3, option4, correct_option, marks)
VALUES ('Q005', 'Which Java keyword is used to define a constant?', 'var', 'const', 'final', 'static', 'c', 1);

INSERT INTO questions (questionID, question, option1, option2, option3, option4, correct_option, marks)
VALUES ('Q006', 'What is the result of "9 + 3 * 2"?', '15', '18', '24', '12', 'a', 2);

INSERT INTO questions (questionID, question, option1, option2, option3, option4, correct_option, marks)
VALUES ('Q007', 'Which of the following is a valid way to create an object in Java?', 'ClassName obj = new ClassName();', 'ClassName obj = ClassName();', 'ClassName obj = new ClassName;', 'None of the above', 'a', 2);

INSERT INTO questions (questionID, question, option1, option2, option3, option4, correct_option, marks)
VALUES ('Q008', 'Which of the following is a Java code editor?', 'Eclipse', 'NetBeans', 'IntelliJ IDEA', 'All of the above', 'd', 1);

INSERT INTO questions (questionID, question, option1, option2, option3, option4, correct_option, marks)
VALUES ('Q009', 'What is the purpose of the "static" keyword in Java?', 'To create a method that can be called without creating an object', 'To create a variable that is shared among all instances of a class', 'Both A and B', 'None of the above', 'c', 3);

INSERT INTO questions (questionID, question, option1, option2, option3, option4, correct_option, marks)
VALUES ('Q010', 'Which of the following is a Java collection interface?', 'List', 'Set', 'Map', 'All of the above', 'd', 2);

INSERT INTO Test (TestID, TeacherID, Subject, Difficulty, StartDateTime, EndDateTime, Description, Accepted)
VALUES
  ('TEST006', 'T001', 'Java1', 'Medium', '2024-04-25 09:00:00', '2024-04-25 11:00:00', 'Java Basics Test', 0),
  ('TEST007', 'T002', 'Java2', 'Hard', '2024-04-18 20:43:00', '2024-04-18 21:00:00', 'Object-Oriented Programming Test', 1),
  ('TEST008', 'T002', 'Java3', 'Easy', '2024-04-18 23:20:00', '2024-04-18 23:40:00', 'Collections and Generics Test', 1),
  ('TEST009', 'T002', 'Java4', 'Medium', '2024-05-02 14:00:00', '2024-05-02 14:02:00', 'Exception Handling Test', 1),
  ('TEST010', 'T002', 'Java5', 'Hard', '2024-05-04 09:00:00', '2024-05-04 11:00:00', 'Multithreading Test', 0),
  ('TEST011', 'T001', 'Java6', 'Easy', '2024-05-06 13:00:00', '2024-05-06 15:00:00', 'Input/Output Test', 1),
  ('TEST012', 'T001', 'Java7', 'Medium', '2024-05-08 10:00:00', '2024-05-08 12:00:00', 'JDBC Test', 0),
  ('TEST013', 'T001', 'Java8', 'Hard', '2024-05-10 14:00:00', '2024-05-10 16:00:00', 'Networking Test', 1),
  ('TEST014', 'T001', 'Java9', 'Easy', '2024-05-12 09:00:00', '2024-05-12 11:00:00', 'Servlets and JSP Test', 0),
  ('TEST015', 'T002', 'Java10', 'Medium', '2024-05-14 13:00:00', '2024-05-14 15:00:00', 'Java EE Test', 1);


INSERT INTO test_questions (TestID, QuestionID) VALUES
('TEST006', 'Q001'),
('TEST006', 'Q002'),
('TEST006', 'Q003'),
('TEST006', 'Q004'),
('TEST006', 'Q005');


INSERT INTO test_questions (TestID, QuestionID) VALUES
('TEST007', 'Q006'),
('TEST007', 'Q007'),
('TEST007', 'Q008'),
('TEST007', 'Q009'),
('TEST007', 'Q010');


INSERT INTO test_questions (TestID, QuestionID) VALUES
('TEST008', 'Q001'),
('TEST008', 'Q002'),
('TEST008', 'Q003'),
('TEST008', 'Q004'),
('TEST008', 'Q005');

INSERT INTO test_questions (TestID, QuestionID) VALUES
('TEST009', 'Q001'),
('TEST009', 'Q002'),
('TEST009', 'Q003'),
('TEST009', 'Q004'),
('TEST009', 'Q005');