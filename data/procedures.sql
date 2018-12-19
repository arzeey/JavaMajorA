/*
  	EDIT THIS FILE SO THAT IT CONTAINS PROCEDURES WHICH CAN BE IMPORTED INTO YOUR MYSQL SCHEMA
	You will need to have sufficent procedures to facilitate menu options 1 to 6
	Informaiton on the correct formation of procedures can be found here

	Note: do not use the key words "BEGIN" and "END" as they are not recognised when the .sql file is being imported.

	Use the "DROP PROCEDURE" command to allow you to up date existing scripts with subsuequent imports.
 	Take care if you rename procedures that you DROP the old procedures
*/

DROP PROCEDURE IF EXISTS getEmployeeCount;
DROP PROCEDURE IF EXISTS getEmployeeDetail;
DROP PROCEDURE IF EXISTS getEmployeeByLastName;
DROP PROCEDURE IF EXISTS insertEmployee;
DROP PROCEDURE IF EXISTS getEmpDept;
DROP PROCEDURE IF EXISTS getManager;
-- Option 0 - no procedure required

-- Option 1
CREATE PROCEDURE getEmployeeCount()
SELECT COUNT(emp_no) AS num FROM oop_employees;

-- Option 2 - We're Defining the columns we want to get from the employee table 
CREATE PROCEDURE getEmployeeDetail()
SELECT emp_no, birth_date, first_name, last_name, gender, hire_date  FROM oop_employees;

-- Option 3 - We're getting all columns of the employee table and filtering it by the last name in parameters
CREATE PROCEDURE getEmployeeByLastName(IN lname varchar(255))
SELECT * FROM oop_employees WHERE oop_employees.last_name = lname;

-- Option 4 - We're inserting the employee details using the parameters taken from the user.
CREATE PROCEDURE insertEmployee
(IN emp_no int(255), birth_date date,
first_name varchar(255),
last_name varchar(255), gender varchar(255), hire_date date)
INSERT INTO oop_employees VALUES
(emp_no, birth_date, first_name, last_name, gender, hire_date);

-- Option 5 - We're getting the Employee name and the department they
-- belong to by joining the oop_employees and oop_departments
-- tables all joined by the oop_emp_dept
CREATE PROCEDURE getEmpDept()
SELECT CONCAT(oop_employees.first_name, ' ', oop_employees.last_name)
as employee, oop_departments.dept_name as department FROM oop_dept_emp
INNER JOIN oop_employees on oop_dept_emp.emp_no = oop_employees.emp_no
INNER JOIN oop_departments on oop_departments.dept_no = oop_dept_emp.dept_no;

-- Option 6 - We're displaying all the departments and their managers
-- and the salaries of those managers. Joins the oop_employees,
-- oop_dept_manager and oop_departments
CREATE PROCEDURE getManager()
SELECT CONCAT(oop_employees.first_name, ' ', oop_employees.last_name)
as employee, oop_departments.dept_name as department, oop_salaries.salary
as salary FROM oop_dept_manager INNER JOIN oop_employees
on oop_dept_manager.emp_no = oop_employees.emp_no
INNER JOIN oop_departments on oop_departments.dept_no = oop_dept_manager.dept_no
INNER JOIN oop_salaries on oop_salaries.emp_no = oop_employees.emp_no;