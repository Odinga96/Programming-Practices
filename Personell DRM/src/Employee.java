public class Employee extends Person{
	private int empID;
	private double baseSalary;
	private String type;




	public Employee(String last, String first, String middle, int id, double sal,String t) {
		super(last, first, middle);
		empID = id;
		baseSalary = sal;
		this.type = t;

	}

	public int getID() {
		return empID;

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
