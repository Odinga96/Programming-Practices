import java.util.*;
public class Personnel {

	public ArrayList<Person> personList;

	public Personnel() {
	   personList = new ArrayList<Person>();
	}

	public void addPersonnel(Person p)
	{
		personList.add(p);
	}


	public ArrayList<Person> getPersonList() {
		return personList;
	}

	public void setPersonList(ArrayList<Person> personList) {
		this.personList = personList;
	}
}