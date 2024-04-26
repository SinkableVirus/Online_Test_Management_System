DROP DATABASE IF EXISTS online_test;

create database online_test;

use online_test;


-- Creating base tables
create table Student(SRN varchar(50) primary key, Branch varchar(50), Name varchar(50), Password varchar(50));

create table Admin(ID varchar(50) primary key, Name varchar(50), Password varchar(50));

create table Teacher(ID varchar(50) primary key, Name varchar(50), Password varchar(50), Branch varchar(50));

create table Test(TestID varchar(50) primary key, Time datetime,  Questions varchar(50), Answers varchar(50));

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


alter table Student_Test add foreign key (SRN) references Student(SRN);
alter table Student_Test add foreign key (TestID) references Test(TestID);




  
  

 
 
