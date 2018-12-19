import java.sql.Date; //Importing the SQL Date class to format our dates to SQL standards

public class Employee{ //Declaring the Employee class
	private static int _numEmp = 0; //Assigning a static value to the employee count
	
	//Declaring private strings: I use underscore to descern private and public values
	private int _empNo;
	private Date _birthDate;
	private String _firstName;
	private String _lastName;
	private String _gen;
	private Date _hireDate;
	
	
	//@param null constructor: takes no parameters
	public Employee(){
		super(); //Inheritance
		this._empNo = Employee._numEmp++; //Increments the employee number
		this._gen = "N/A"; //U for unknown
	}
	
	//@params birthDate, firstName, lastName, Gen, hireDate constructor: takes 5 parameters
	public Employee(Date birthDate, String firstName, String lastName, String gen, Date hireDate){
		this._empNo = Employee._numEmp++;
		this._birthDate = birthDate;
		this._firstName = firstName;
		this._lastName = lastName;
		this._gen = gen;
		this._hireDate = hireDate;
		
	}
	
	public Employee(int empnum, Date birthDate, String firstName, String lastName, String gen, Date hireDate){
		this._empNo = empnum;
		this._birthDate = birthDate;
		this._firstName = firstName;
		this._lastName = lastName;
		this._gen = gen;
		this._hireDate = hireDate;
		
	}
	//Setters - sets value(s)
	//@param birthDate: sets the birthDate of the current object
	public void setbirthDate(Date birthDate){
		this._birthDate = birthDate;
		System.out.println("Birthdate changed");
	}
	//@param firstName: sets the firstName of the current object
	public void setfirstName(String firstName){
		this._firstName = firstName;
		System.out.println("First name changed");
	}
	//@param lastName: sets the lastName of the current object
	public void setlastName(String lastName){
		this._lastName = lastName;
		System.out.println("Last name changed");
	}
	//@param gen: sets the gen of the current object
	public void setGen(String gen){
		this._gen = gen;
		System.out.println("Gender changed");
	}
	//@param hireDate: sets the hireDate of the current object
	public void sethireDate(Date hireDate){
		this._hireDate = hireDate;
		System.out.println("Hire date changed");
	}
	
	//Getters - returns value
	//Returns an int value of empNo
	public int getEmpNo(){
		return _empNo;
	}
	//Returns Date value of birthDate
	public Date getbirthDate(){
		return _birthDate;
	}
	//Returns String value of firstName
	public String getfirstName(){
		return _firstName;
	}
	//Returns String value of lastName
	public String getlastName(){
		return _lastName;
	}
	//Returns char value of gen
	public String getGen(){
		return _gen;
	}
	//Returns Date value of hireDate
	public Date gethireDate(){
		return _hireDate;
	}
	//Returns Date value of hireDate
	public static int getEmpCount(){
		return _numEmp;
	}
		
	
	//Creates a toString buffer. Appends all values of the Employee object.
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("============================================");
		sb.append("\nEmployee Number:").append(this._empNo);
		sb.append("\nBirth Date:").append(this._birthDate);
		sb.append("\nFirst Name:").append(this._firstName);
		sb.append("\nLast Name:").append(this._lastName);
		sb.append("\nGender:").append(this._gen);
		sb.append("\nHire Date:").append(this._hireDate + "\n\n");
		//Returns the whole String
		return sb.toString();
	} 
	public static void main(String[] args) {
		Employee e = new Employee(java.sql.Date.valueOf("2000-01-01"), "FirstName", "LastName", "M", java.sql.Date.valueOf("2001-01-01"));
		System.out.println(e);
	}
}