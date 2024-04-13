drop database if exists online_test;
create database if not exists online_test;

use online_test;

-- Creating base tables
create table Student(SRN varchar(50) primary key, Branch varchar(50), Name varchar(50), Password varchar(50));

create table Admin(ID varchar(50) primary key, Name varchar(50), Password varchar(50));

create table Teacher(ID varchar(50) primary key, Name varchar(50), Password varchar(50), Branch varchar(50));

create table Test(TestID varchar(50) primary key,  TeacherID varchar(50), StartDateTime DATETIME,  EndDateTime DATETIME ,  Questions varchar(500), Answers varchar(500), Description varchar(500), Accepted bool);

CREATE TABLE Student_Test (
    SRN VARCHAR(50),
    TestID varchar(50),
    MarksSecured INT,
    MarkedAnswers VARCHAR(100),
    PRIMARY KEY (SRN, TestID)
);

 -- Altering tables

alter table Test add column Teacher_ID varchar(50);
alter table Test add foreign key (Teacher_ID) references Teacher(ID);
 
alter table Test add column Admin_ID varchar(50);
alter table Test add foreign key (Admin_ID) references Admin(ID);
alter table Test add foreign key (Teacher_ID) references Teacher(ID);

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
INSERT INTO Test (TestID, TeacherID, StartDateTime, EndDateTime, Questions, Answers, Description, Accepted, Teacher_ID, Admin_ID)
VALUES
    ('TEST001', 'T001', '2024-04-12 08:00:00', '2024-04-12 10:00:00', 'Question 1, Question 2, Question 3', 'Answer 1, Answer 2, Answer 3', 'Test description 1', true, 'T001', 'AD001'),
    ('TEST002', 'T002', '2024-04-12 10:00:00', '2024-04-12 12:00:00', 'Question 1, Question 2, Question 3', 'Answer 1, Answer 2, Answer 3', 'Test description 2', false, 'T002', 'AD002');

-- Student_Test table
INSERT INTO Student_Test (SRN, TestID, MarksSecured, MarkedAnswers)
VALUES
    ('SRN001', 'TEST001', 80, 'Answer 1, Answer 2, Answer 3'),
    ('SRN002', 'TEST001', 75, 'Answer 1, Answer 2, Answer 3'),
    ('SRN003', 'TEST001', 70, 'Answer 1, Answer 2, Answer 3'),
    ('SRN001', 'TEST002', 85, 'Answer 1, Answer 2, Answer 3'),
    ('SRN002', 'TEST002', 90, 'Answer 1, Answer 2, Answer 3'),
    ('SRN003', 'TEST002', 95, 'Answer 1, Answer 2, Answer 3');

-- Test table (continued)
INSERT INTO Test (TestID, TeacherID, StartDateTime, EndDateTime, Questions, Answers, Description, Accepted, Teacher_ID, Admin_ID)
VALUES
    ('TEST003', 'T001', '2024-04-12 09:00:00', '2024-04-12 11:00:00', 'Question 1, Question 2, Question 3', 'Answer 1, Answer 2, Answer 3', 'Test description 3', true, 'T001', 'AD001'),
    ('TEST004', 'T002', '2024-04-12 11:00:00', '2024-04-12 13:00:00', 'Question 1, Question 2, Question 3', 'Answer 1, Answer 2, Answer 3', 'Test description 4', false, 'T002', 'AD002');

-- Student_Test table (continued)
INSERT INTO Student_Test (SRN, TestID, MarksSecured, MarkedAnswers)
VALUES
    ('SRN001', 'TEST003', 78, 'Answer 1, Answer 2, Answer 3'),
    ('SRN002', 'TEST003', 80, 'Answer 1, Answer 2, Answer 3'),
    ('SRN003', 'TEST003', 85, 'Answer 1, Answer 2, Answer 3'),
    ('SRN001', 'TEST004', 92, 'Answer 1, Answer 2, Answer 3'),
    ('SRN002', 'TEST004', 88, 'Answer 1, Answer 2, Answer 3'),
    ('SRN003', 'TEST004', 90, 'Answer 1, Answer 2, Answer 3');


insert into student values("a","b","c","d");
delete from student;
TRUNCATE TABLE Student;


select * from student;

select Password from student where SRN='b';
  

 
 
