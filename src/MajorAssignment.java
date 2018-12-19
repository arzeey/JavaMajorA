import java.util.Scanner; //Importing Scanner to be able to take user input
import java.sql.Connection; //Importing Connection to be able to connect to a database
import java.sql.Date; //Importing Date to format dates to SQL standards
import java.sql.ResultSet; //ResultSets to get SQL result-sets
import java.sql.SQLException; //Errors
import java.sql.Statement; //SQL Statements
import java.util.ArrayList; //ArrayLists uses
import java.util.Iterator; //ArrayList iterator output

import util.DBConnect;
/**
 * FOLLOW THE COMMENTS CAREFULLY TO COMPLETE THIS FILE
 * 
 * A user menu facilitates interaction with a MySQL schema by calling methods from this file 
 * which execute SQL procedures stored in a MYSQL schema. Connection to this is specified by the user.
 * Ensure that the correct name for the style of connection (setUpForLocalMySQL or setUpForKnuthMySQL) you are using is accessible (uncommented)
 * 
 * Common Errors
 * - "The last packet sent successfully to the server was 0 milliseconds ago. The driver has not received any packets from the server."
 *    - Your MySQL server is not running
 *    - There is no MySQL server running on the port you have specified
 * 
 * @author Alex Cronin
 */
public class MajorAssignment { //Main Class

	public static void main(String[] args) { //Main method
		System.out.println("MajorAssignment.java - starting main method\n");

		// Create a scanner to accept input from the user
		Scanner scan = new Scanner(System.in);
		// Create a DBConnect object and open a  connection
		DBConnect dbc2 = new DBConnect();
		int objectcount = 0; //We're using a global variable (for the main method) to store the current number of employees
		dbc2.setUpForKnuthMySQL("s2991625", "ellesses", "s2991625", "s2991625", "ellesses"); //Setting up the connection
		// Below are two options ( a and b) for how to access your MySQL . 
		// - Comment out the option you DO NOT want
		// - then fill in the appropriate values in the constructor
		// a) Use the option below if you are accessing MySQL on your local machine
		// b) Use the option below if you are accessing MySQL over an SSH connection
		//dbc2.setUpForLocalMySQL("s2991625", "ellesses", "s2991625");
		// Open the connection - call the correct method on the correct object
		dbc2.openConnection();
		do {
			// Menu of the application
			System.out.println("\n===========================================================");
			System.out.println("USER MENU");
			System.out.println("Please select an option by typing the corresponding number.");
			System.out.println("0. Exit");				
			System.out.println("1. Display the count of the employees");
			System.out.println("2. Display records of all employees");
			System.out.println("3. Search for an employee by last name");
			System.out.println("4. Add a new employee");
			System.out.println("5. List Employees and their departments");				
			System.out.println("6. List the Managers of all available departments");
			System.out.println("===========================================================");
			System.out.print("option> ");
			int res123 = MajorAssignment.getEmployeeCount(dbc2.getConnection()); //We initialize employeecount at the start because we need to count the index. Used for no. 4: adding a new employee
			objectcount = ++res123;
			// Initialize a variable of the correct type called option to store the value entered by the user 
			System.out.print("Select your choice: ");
			int option = scan.nextInt(); //Getting the input of the user using the scanner scan.
			/**
			 * When the user selects an option of 7 options are executed. 
			 * 		Option 0 and 1 specified. 
			 * 		You must specify options 2 to 6. 
			 * Each option will call a method below
			 * 		No method is required for option 0. 
			 * 		Method for option 1 is specified below. 
			 * 		You must specify methods of option 2 to 6. 
			 */
			// 0. exit the program 
			if (option == 0) {
				System.out.println("Exiting the program");
				break; //exit the while loop
			}

			// 1. Display the count of employees
			else if (option == 1) { //Executes if the user inputs a 1
				// Output the result of the getEmployeeCount method which is implemented after the main method
				int result = MajorAssignment.getEmployeeCount(dbc2.getConnection()); 
				//We call the class method getEmployeeCount to get the number of employees. 
				//The value of result (int) is the return value of the method
				System.out.println("There are a total of " +result+ " in the table employees");
				//We display the number of employees using a print command
			}

			// 2. displaying all employees 
			else if (option == 2) { //Executes if the user inputs a 2
				Employee [] result = MajorAssignment.getAllEmployeesDetails(dbc2.getConnection(), objectcount);
				//We're executing the getAllEmployeeDetails method and storing the returned employee array in result variable
				for(int j =0; j< result.length; j++) { //Print out all instances of employee
					System.out.println(result[j]); //Cycle through all the values of employee using a for loop
				}
			}

			// 3. search for an employee by name
			else if (option == 3) { //Executes if the user inputs a 3
				System.out.print("Which employee's last name would you like to view?: "); //Asking the user for input
				String lname = scan.next(); //Using scan to take input from the user
				ArrayList<Employee> result = MajorAssignment.getEmployeeByLastName(dbc2.getConnection(), lname); 
				//Storing the returned value from getEmployeeByLastName in an ArrayList
				System.out.println(result); //Printing out the results
			}
			
			// 4. adding an employee
			else if (option == 4) { //Executes if the user inputs a 4
				//We're getting inputs from the user here
				//Printing out the question and using the scanner to pick up the answer which is stored in their 
				//respective variable names that uses the appropriate data type
				System.out.print("Birth Year YYYY: ");
				String birth_date = scan.next();
				System.out.print("Birth Month MM: ");
				birth_date = birth_date+"-"+scan.next();
				System.out.print("Birth Day DD: ");
				birth_date = birth_date+"-"+scan.next();
				Date b_date = Date.valueOf(birth_date); //Converting the date to a date variable
						
				System.out.print("First Name: ");
				String first_name = scan.next();
				System.out.print("Last Name: ");
				String last_name = scan.next();
				System.out.print("Gender: ");
				String gender = scan.next();
				
				System.out.print("Hire Year YYYY: ");
				String hire_date = scan.next();
				System.out.print("Hire Month MM: ");
				hire_date = hire_date+"-"+scan.next();
				System.out.print("Hire Day DD: ");
				hire_date = hire_date+"-"+scan.next();
				Date h_date = Date.valueOf(hire_date); //Converting the date to a date variable
				
				int emp_no = objectcount++; //Incrementing on the employee object count to add to the parameter values 
				String result = MajorAssignment.insertEmployee(dbc2.getConnection(), emp_no, b_date, first_name, last_name, gender, h_date);
				//Passing the parameters to insertEmployee and returning a string 
				System.out.print(result); //Printing the results
			}
			// 5. Additional functionality 
			else if (option == 5) { //Executes if the user inputs a 5
				System.out.println("Here's a list of all the employees and the departments they work for;"); 
				ArrayList<String> result = MajorAssignment.getEmpDept(dbc2.getConnection());
				//Printing the arrayList result that outputs all string stored in it using an iterator
				Iterator<String> itr=result.iterator();  
				while(itr.hasNext()){ //Loop while itr has values
					System.out.println(itr.next());  //Printing a next instance of itr.
				}  
			}	
			// 6. Additional functionality 
			else if (option == 6) { //Executes if the user inputs a 6
				System.out.println("Here's a list of all the employees and the departments they work for;");
				ArrayList<String> result = MajorAssignment.getManager(dbc2.getConnection());
				//Getting the ArrayList String outputs that getManager method returns
				Iterator<String> itr=result.iterator();  //itr to iterate through the results
				while(itr.hasNext()){  
					System.out.println(itr.next());  
				}  
			}	

			// Unknown option
			else {
				System.out.println("Invalid option.");
			}
		}while(true); //Loop until option 0 is selected

		// Close the connection
		dbc2.closeConnection();
		// Close the scanner
		scan.close();
		System.out.println("MajorAssignment.java - ending main method\n");

	}

	/**
	 * THE FOLLOWING METHODS CALL SQL PROCEDURES STORED IN YOUR MYSQL SCHEMA AND 
	 * RETURNs THE RESULTS TO THE IF/ELSE BLOCKS ABOVE WHERE THEY WERE CALLED
	 * 
	 * Each method calls one or more stored SQL procedures from our MySQL schema 
	 * There is currently have one SQL procedure defined in the provided procedures.sql file 
	 * located in the data folder. 
	 * You will need to 
	 * 	- Create additional procedures in procedures.sql
	 * 	- Import them into your schema using Import.java
	 * 	- Write code in the if/else blocks above to call java methods below which in turn call procedures in your MySQL schema
	 * 	- All of these steps combined should enable you to have 7 options (0 to 6) on your user menu.
	 * Using option 1 as a template you must implement option 2 to 6. 
	 * 
	 * The general format of these methods is as follows
	 *  - give the method an appropriate access modifier, return type, name & parameters
	 *  - Create a string containing an SQL statement which calls a procedure
	 *  - Create a statement
	 *  - Create a results set by executing your statement on the MySQL schema
	 *  - Return the results set as a java primitive or object
	 *  - Close the statement
	 *  - Close the results set
	 */

	/**
	 * Option 0 - Exit the program. No method to call SQL required. 
	 */

	/**
	 * Option 1 - Display the count of the employees
	 * @param conn - the connection to the MySQL schema 
	 * @return count of employees
	 */
	public static int getEmployeeCount(Connection conn) {
		int num = -1;
		try {
			// Create the SQL query string which uses the  "getEmployeeCount" stored procedure in the employee 
			String sql = "CALL getEmployeeCount()";
			// Create a new SQL statement 
			Statement st = conn.createStatement();
			// Create a new results set which store the value returned by the  when the sql statement is executed 
			ResultSet rs = st.executeQuery(sql); 
			// While there are still elements in the results set
			while (rs.next()) 
				num = rs.getInt(1); // assign the next int in the results set to num
			rs.close(); // close the results set
			st.close(); // close the statement
		}
		catch (SQLException e) { //Catching errors
			System.out.println("Error in getEmployeeCount");
			e.printStackTrace();
		}
		return num;		
	}

	/**
	 * Option 2 - Display records of all the employees 
	 * @param conn - the connection to the MySQL schema 
	 * @param emp_no
	 * @return Employee Array
	 */
	public static Employee[] getAllEmployeesDetails(Connection conn, int emp_no) { //Publicly accessible method that returns an Employee Array
		Employee[] empArray; //Initializing a new Employee array
		if (emp_no > 0){ //Testing if the number of employees exceed 12 which then will initialize a defined array length
			empArray = new Employee[emp_no];
		}else {
			empArray = new Employee[12];
		}
		try {
			// Create the SQL query string which uses the  "getEmployeeDetail" stored procedure in the employee 
			String sql = "CALL getEmployeeDetail()";
			// Create a new SQL statement 
			Statement st = conn.createStatement();
			// Create a new results set which store the value returned by the  when the sql statement is executed 
			ResultSet rs = st.executeQuery(sql); 
			// While there are still elements in the results set
			int i=0;
			while (rs.next()) //Iterating through the resultsets
				empArray[i++] = new Employee(rs.getInt("emp_no"), rs.getDate("birth_date"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("gender"), rs.getDate("hire_date")); //TODO Expand this constructor call to take more arguements
				
			rs.close(); // close the results set
			st.close(); // close the statement
		}
		catch (SQLException e) { //Catching errors
			System.out.println("Error in getEmployeeDetail");
			e.printStackTrace();
		}
		return empArray;
	}

	/**
	 * Option 3 - Search for an employee by last name
	 * @param conn - the connection to the MySQL schema 
	 * @param lname - Last name provided
	 * @return Employee ArrayList
	 */
	public static ArrayList<Employee> getEmployeeByLastName(Connection conn, String lname) { //Publicly accessible method that returns an employee arraylist
		ArrayList<Employee> emps = new ArrayList<>(); //Initializing a new arraylist to store employees
		int i=0;
		try {
			// Create the SQL query string which uses the  "getEmployeeByLastName" stored procedure in the employee 
			String sql = "CALL getEmployeeByLastName('"+lname+"')";
			// Create a new SQL statement 
			Statement st = conn.createStatement();
			// Create a new results set which store the value returned by the  when the sql statement is executed 
			ResultSet rs = st.executeQuery(sql); 
			// While there are still elements in the results set
			Employee emp = null;
			while (rs.next()) 
				emp = new Employee(rs.getInt("emp_no"), rs.getDate("birth_date"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("gender"), rs.getDate("hire_date")); //TODO Expand this constructor call to take more arguements
				emps.add(emp); //adding object in list
				i++;
			rs.close(); // close the results set
			st.close(); // close the statement
		}
		catch (SQLException e) { //Catching errors
			System.out.println("Error in getEmployeeByLastName");
			e.printStackTrace();
		}
		return emps;
	}

	/**
	 *  Option 4 - Add a new employee. This method need only alter the Employee table
	 * @param conn - the connection to the MySQL schema 
	 * @param emp_no - the employee number to add
	 * @param birth_date
	 * @param last_name
	 * @param gender
	 * @param hire_date
	 * @return String
	 */
	public static String insertEmployee(Connection conn, int emp_no, Date birth_date, String first_name, String last_name, String gender, Date hire_date) {
		//Publicly Accessible method that takes 6 parameters and returns a string
		try {
			// Create the SQL query string which uses the  "insertEmployee" stored procedure in the employee 
						String sql = "CALL insertEmployee('"+emp_no+"','"+birth_date+"','"+first_name+"','"+last_name+"','"+gender+"','"+hire_date+"')";
						// Create a new SQL statement 
						Statement st = conn.createStatement();
						// Create a new results set which store the value returned by the  when the sql statement is executed 
						ResultSet rs = st.executeQuery(sql); 
		}catch (SQLException e) { //Catching errors
			System.out.println("Error in insertEmployee");
			e.printStackTrace();
		}
		return "Employee added";
	}
	
	/**
	 *  Option 5 - Display which department the employee belongs to 
	 * @param conn - the connection to the MySQL schema 
	 * @return String ArrayList
	 */
	public static ArrayList<String> getEmpDept(Connection conn) {
		//Publicly Accessible method that returns an ArrayList
		ArrayList<String> emps = new ArrayList<>(); //Initializing a new ArrayList
		int i = 0; //This just outputs a count of the output
		try {
			// Create the SQL query string which uses the  "getEmployeeCount" stored procedure in the employee 
			String sql = "CALL getEmpDept()";
			// Create a new SQL statement 
			Statement st = conn.createStatement();
			// Create a new results set which store the value returned by the  when the sql statement is executed 
			ResultSet rs = st.executeQuery(sql); 
			// While there are still elements in the results set
			String emp = null;
			while (rs.next()) {
				i++; //This just outputs a count of the output
				emp = ((i) + ". "+ rs.getString("employee") + " works at " + rs.getString("department") + " department"); //TODO Expand this constructor call to take more arguements
				emps.add(emp); //adding String to list
			}
			rs.close(); // close the results set
			st.close(); // close the statement
		}
		catch (SQLException e) { //Catching errors
			System.out.println("Error in getEmpDept");
			e.printStackTrace();
		}
		return emps;
	}
	
	/**
	 *  Option 6 - Display the Department an Employee manages as well as how much they make
	 * @param conn - the connection to the MySQL schema 
	 * @return String ArrayList
	 */
	public static ArrayList<String> getManager(Connection conn) {
		//Publicly Accessible method that returns an ArrayList
		ArrayList<String> emps = new ArrayList<>(); //Initializing a new ArrayList
		int i = 0; //This just outputs a count of the output
		try {
			// Create the SQL query string which uses the  "getEmployeeCount" stored procedure in the employee 
			String sql = "CALL getManager()";
			// Create a new SQL statement 
			Statement st = conn.createStatement();
			// Create a new results set which store the value returned by the  when the sql statement is executed 
			ResultSet rs = st.executeQuery(sql); 
			// While there are still elements in the results set
			String emp = null;
			while (rs.next()) {
				i++; //This just outputs a count of the output
				emp = ((i) + ". "+ rs.getString("employee") + " manages " + rs.getString("department") + " department and has a salary of " + rs.getInt("salary")); //TODO Expand this constructor call to take more arguements
				emps.add(emp); //adding String to list
			}
			rs.close(); // close the results set
			st.close(); // close the statement
		}
		catch (SQLException e) { //Catching errors
			System.out.println("Error in getEmpDept");
			e.printStackTrace();
		}
		return emps;
	}
}
