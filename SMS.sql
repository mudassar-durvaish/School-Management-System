-- Create Attendance table next
CREATE TABLE Attendance (
    attendance INT PRIMARY KEY,
    attendance_date DATE
);

-- Create Students table first
CREATE TABLE Students (
    student_id INT PRIMARY KEY,
    first_name VARCHAR(300),
    last_name VARCHAR(300),
    session_number INT,
    attendance INT,
    FOREIGN KEY (attendance) REFERENCES Attendance(attendance)
);

ALTER TABLE students
ADD (class INT DEFAULT 1);

-- Create Room table
CREATE TABLE Room (
    room_id INT PRIMARY KEY,
    capacity INT,
    room_type VARCHAR(100)
);
select * from room;


-- Create Employees table
CREATE TABLE Employees (
    employee_id INT PRIMARY KEY,
    first_name VARCHAR(300),
    last_name VARCHAR(300),
    gender VARCHAR(20),
    salary INT,
    level_t VARCHAR(50)
);

select * from Employees;

SELECT *
FROM Employees
WHERE employeeType = 'Staff';


ALTER TABLE Employees
ADD employeeType VARCHAR(200) DEFAULT 'Staff';


-- Create TimeSchedule table
CREATE TABLE TimeSchedule (
    schedule_id INT PRIMARY KEY,
    room_id INT,
    subject_id INT,
    employee_id INT,
    period INT,
    FOREIGN KEY (room_id) REFERENCES Room(room_id),
    FOREIGN KEY (employee_id) REFERENCES Employees(employee_id)
);
select * from TimeSchedule;
-- Create Student_class table
CREATE TABLE Student_class (
    student_id INT,
    class_id INT,
    PRIMARY KEY (student_id, class_id),
    FOREIGN KEY (student_id) REFERENCES Students(student_id),
    FOREIGN KEY (class_id) REFERENCES TimeSchedule(schedule_id)
);

-- Create Leaves table
CREATE TABLE Leaves (
    leave_id INT PRIMARY KEY,
    leave_type VARCHAR(100),
    teacher_replacement VARCHAR(255)
);

-- Create Teacher table
CREATE TABLE Teacher (
    teacher_id INT PRIMARY KEY,
    subjects INT,
    leave_id INT,
    attendance INT,
    FOREIGN KEY (leave_id) REFERENCES Leaves(leave_id),
    FOREIGN KEY (attendance) REFERENCES Attendance(attendance)
);

select * from teacher;


-- Create Auditorium table
CREATE TABLE Auditorium (
    auditorium_name VARCHAR(100),
    no_of_seats VARCHAR(300),
    event_name VARCHAR(300)
);

ALTER TABLE Auditorium
ADD PRIMARY KEY (auditorium_name);

CREATE TABLE EventRegistration (
    registration_id INT PRIMARY KEY,
    name VARCHAR(300),
    event_type VARCHAR(20),
    auditorium_name VARCHAR(100),
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_event_type CHECK (event_type IN ('Educational', 'Poetry', 'Motivational')),
    FOREIGN KEY (auditorium_name) REFERENCES Auditorium(auditorium_name)
);

INSERT INTO EventRegistration (registration_id, name, event_type, auditorium_name, registration_date)
VALUES (1, 'John Doe', 'Educational', 'Main Auditorium', CURRENT_TIMESTAMP);

INSERT INTO EventRegistration (registration_id, name, event_type, auditorium_name, registration_date)
VALUES (2, 'Jane Smith', 'Poetry', 'Small Auditorium', CURRENT_TIMESTAMP);

INSERT INTO EventRegistration (registration_id, name, event_type, auditorium_name, registration_date)
VALUES (3, 'Michael Johnson', 'Motivational', 'Grand Hall', CURRENT_TIMESTAMP);

INSERT INTO EventRegistration (registration_id, name, event_type, auditorium_name, registration_date)
VALUES (4, 'Emily Brown', 'Educational', 'Conference Center', CURRENT_TIMESTAMP);

INSERT INTO EventRegistration (registration_id, name, event_type, auditorium_name, registration_date)
VALUES (14, 'Muhammad Mudassar', 'Motivational', 'MP Hall', CURRENT_TIMESTAMP);

select * from EventRegistration;


-- Insert data into Attendance table
INSERT INTO Attendance (attendance, attendance_date) VALUES (1, TO_DATE('2024-05-01', 'YYYY-MM-DD'));
INSERT INTO Attendance (attendance, attendance_date) VALUES (2, TO_DATE('2024-05-02', 'YYYY-MM-DD'));
INSERT INTO Attendance (attendance, attendance_date) VALUES (3, TO_DATE('2024-05-03', 'YYYY-MM-DD'));
INSERT INTO Attendance (attendance, attendance_date) VALUES (4, TO_DATE('2024-05-04', 'YYYY-MM-DD'));

CREATE or replace VIEW EventDetails AS
SELECT e.registration_id, e.name AS name, e.event_type, e.registration_date, a.auditorium_name, a.no_of_seats
FROM EventRegistration e
JOIN Auditorium a ON e.auditorium_name = a.auditorium_name;

SELECT *
FROM EventDetails
WHERE event_type = 'Educational';




-- Insert data into Room table
INSERT INTO Room (room_id, capacity, room_type) VALUES (201, 30, 'Lecture Hall');
INSERT INTO Room (room_id, capacity, room_type)VALUES(202, 40, 'Lab');
INSERT INTO Room (room_id, capacity, room_type) VALUES (203, 50, 'Conference Room');
INSERT INTO Room (room_id, capacity, room_type) VALUES (204, 60, 'Auditorium');

-- Insert data into Employees table
INSERT INTO Employees (employee_id, first_name, last_name, gender, salary, level_t)VALUES(401, 'James', 'Wilson', 'Male', 50000, 'Junior');
INSERT INTO Employees (employee_id, first_name, last_name, gender, salary, level_t)VALUES(402, 'Emily', 'Brown', 'Female', 55000, 'Senior');
INSERT INTO Employees (employee_id, first_name, last_name, gender, salary, level_t)VALUES(403, 'David', 'Jones', 'Male', 60000, 'Intermediate');
INSERT INTO Employees (employee_id, first_name, last_name, gender, salary, level_t)VALUES(404, 'Sophia', 'Taylor', 'Female', 65000, 'Senior');

-- Insert data into Leaves table
INSERT INTO Leaves (leave_id, leave_type, teacher_replacement)VALUES (1, 'Sick Leave', 'None');
INSERT INTO Leaves (leave_id, leave_type, teacher_replacement)VALUES(2, 'Vacation', 'Substitute Teacher 1');
INSERT INTO Leaves (leave_id, leave_type, teacher_replacement)VALUES(3, 'Maternity Leave', 'Substitute Teacher 2');
INSERT INTO Leaves (leave_id, leave_type, teacher_replacement)VALUES(4, 'Paternity Leave', 'Substitute Teacher 3');

-- Insert data into TimeSchedule table
INSERT INTO TimeSchedule (schedule_id, room_id, subject_id, employee_id, period)VALUES(101, 201, 301, 401, 1);
INSERT INTO TimeSchedule (schedule_id, room_id, subject_id, employee_id, period)VALUES(102, 202, 302, 402, 2);
INSERT INTO TimeSchedule (schedule_id, room_id, subject_id, employee_id, period)VALUES(103, 203, 303, 403, 3);
INSERT INTO TimeSchedule (schedule_id, room_id, subject_id, employee_id, period)VALUES(104, 204, 304, 404, 4);

-- Insert data into Students table
INSERT INTO Students (student_id, first_name, last_name, session_number, attendance)VALUES(1, 'John', 'Doe', 2023, 1);
INSERT INTO Students (student_id, first_name, last_name, session_number, attendance)VALUES(2, 'Alice', 'Smith', 2024, 2);
INSERT INTO Students (student_id, first_name, last_name, session_number, attendance)VALUES(3, 'Michael', 'Johnson', 2022, 3);
INSERT INTO Students (student_id, first_name, last_name, session_number, attendance)VALUES(4, 'Sarah', 'Brown', 2025, 4);

-- Insert data into Student_class table
INSERT INTO Student_class (student_id, class_id)VALUES(1, 101);
INSERT INTO Student_class (student_id, class_id)VALUES(2, 102);
INSERT INTO Student_class (student_id, class_id)VALUES(3, 103);
INSERT INTO Student_class (student_id, class_id)VALUES(4, 104);

-- Insert data into Auditorium table
INSERT INTO Auditorium (auditorium_name, no_of_seats, event_name)VALUES('Main Auditorium', '500', 'Conference');
INSERT INTO Auditorium (auditorium_name, no_of_seats, event_name)VALUES('Small Auditorium', '200', 'Concert');
INSERT INTO Auditorium (auditorium_name, no_of_seats, event_name)VALUES('Grand Hall', '1000', 'Seminar');
INSERT INTO Auditorium (auditorium_name, no_of_seats, event_name)VALUES('Conference Center', '300', 'Workshop');

-- Insert data into Teacher table
INSERT INTO Teacher (teacher_id, subjects, leave_id, attendance)VALUES(1, 3, 1, 1);
INSERT INTO Teacher (teacher_id, subjects, leave_id, attendance)VALUES(2, 2, 2, 2);
INSERT INTO Teacher (teacher_id, subjects, leave_id, attendance)VALUES(3, 4, 3, 3);
INSERT INTO Teacher (teacher_id, subjects, leave_id, attendance)VALUES(4, 3, 4, 4);


select concat(first_name,last_name) as name from students;
SELECT student_id, concat(concat(first_name, ' '), last_name) AS name, session_number, attendance, class FROM students;
select * from students;


INSERT INTO Employees (employee_Id, first_name, last_name, gender,salary, level_t, employeeType) values
(405,'Haroon','Ali','Male',40000,'Junior','Teacher');
select * from employees;

SELECT room_id, capacity, room_type, scheduled_periods FROM RoomView WHERE room_type = 'Lab';

ALTER TABLE employees
MODIFY (LEVEL_T DEFAULT 'Junior');

DELETE FROM employees
WHERE level_t IS NULL;

CREATE VIEW RoomView AS
SELECT Room.room_id, Room.capacity, Room.room_type, 
       COUNT(TimeSchedule.schedule_id) AS scheduled_periods
FROM Room
LEFT JOIN TimeSchedule ON Room.room_id = TimeSchedule.room_id
GROUP BY Room.room_id, Room.capacity, Room.room_type;

select * from roomview;

INSERT INTO Room (room_id, capacity, room_type) VALUES
(1, 30, 'Class Room');
INSERT INTO Room (room_id, capacity, room_type) VALUES (2, 20, 'Lab');
INSERT INTO Room (room_id, capacity, room_type) VALUES (3, 25, 'Class Room');


SELECT 
    sc.student_id,
    e.first_name || ' ' || e.last_name AS teacher_name,
    ts.period AS class_period,
    ts.subject_id AS class_subject,
    r.room_id,
    r.capacity AS room_capacity,
    r.room_type
FROM 
    Student_class sc
INNER JOIN 
    TimeSchedule ts ON sc.class_id = ts.schedule_id
INNER JOIN 
    Employees e ON ts.employee_id = e.employee_id
INNER JOIN 
    Room r ON ts.room_id = r.room_id
ORDER BY 
    sc.student_id, ts.period;



CREATE VIEW schedule AS
SELECT 
    e.first_name || ' ' || e.last_name AS teacher_name,
    ts.period AS class_period,
    ts.subject_id AS class_subject,
    r.room_id,
    r.capacity AS room_capacity,
    r.room_type
FROM 
    Student_class sc
INNER JOIN 
    TimeSchedule ts ON sc.class_id = ts.schedule_id
INNER JOIN 
    Employees e ON ts.employee_id = e.employee_id
INNER JOIN 
    Room r ON ts.room_id = r.room_id
ORDER BY 
    ts.period;
    
    
select * from Schedule;

select * from users;
drop table users;
CREATE TABLE users (
    email VARCHAR(255) PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255)
);

select * from students;

ALTER TABLE students
ADD Marks_percentage INT DEFAULT 60;


SELECT password FROM users WHERE email = 'user@gmail.com';

INSERT INTO users (email, username, password)
VALUES ('user@gmail.com', 'user', '1234');

select * from users;

INSERT INTO Students (student_id, first_name, last_name, session_number, attendance, class,Marks_Percentage)
VALUES 
(14, 'John', 'Doe', 2023, 1, 1,90);
INSERT INTO Students (student_id, first_name, last_name, session_number, attendance, class, Marks_Percentage)
VALUES (20, 'Emma', 'Watson', 2023, 1, 1, 85);

INSERT INTO Students (student_id, first_name, last_name, session_number, attendance, class, Marks_Percentage)
VALUES (19, 'Michael', 'Jordan', 2023, 1, 2, 78);

INSERT INTO Students (student_id, first_name, last_name, session_number, attendance, class, Marks_Percentage)
VALUES (25, 'Sophia', 'Garcia', 2023, 1, 1, 88);
select student_id,first_name,last_name,session_number,session_number,Marks_Percentage class from students;

SELECT first_name, last_name, session_number, Marks_Percentage, class FROM students WHERE student_id=1