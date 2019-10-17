public class StudentList {

    private List<Student> students;

    public StudentList()
    {
        students = new List<Student>();
    }

    public boolean isEmpty()
    {
        return students.isEmpty();
    }
    public boolean contains(Student student)
    {
        return students.contains(student);
    }
    public Student first()
    {
        return students.first();
    }
    public Student next()
    {
        return students.next();
    }
    public void enumerate()
    {
        students.enumerate();
    }

    public List<Student> getStudents() {
        return students;
    }

    /**
     * Add a student record to the list.
     *
     * @param s :student to add to our record
     */
    public void addRecord(Student s){
        students.add(s);
    }

    /**
     * : Delete a student record with the specified ID number.
     *
     * @param ID :Deletes record of the student with this ID
     */
    public void deleteRecord(int ID){
        Student student = searchID(ID);
        if (student != null) { students.remove(student); }
    }

    /**
     * : Display records of all the students taking a specified major.
     * @param major :The major to be looked for
     */
    public void displayMajors(String major){
        Student student = students.first();
        while (student != null)
        {
            String MAJOR = student.getMajor();

            if (MAJOR.equalsIgnoreCase(major)){
                String s = student.toString();
                System.out.println(s);
            }

            student = students.next();
        }

    }

    /**
     *: Display records of all students belonging to a particular faculty.
     * @param faculty :faculty to check for
     */
    public void displayFaculty(String faculty){
        Student student = first();
        while (student != null)
        {
            String FACULTY = student.getFaculty();

            if (FACULTY.equalsIgnoreCase(faculty)){
                String s = student.toString();
                System.out.println(s);
            }

            student = next();
        }
    }

    /**
     * : Display records of all students with the specified last name.
     * @param lName :Given last name
     */
    public void displayName(String lName){
        Student student = first();
        while (student != null)
        {
            String LASTNAME = student.getLastName();

            if (LASTNAME.equalsIgnoreCase(lName)){
                String s = student.toString();
                System.out.println(s);
            }

            student = next();
        }
    }

    public void displayEmail(String email){
        Student student = first();
        while (student != null)
        {
            String EMAIL = student.getEmail();

            if (EMAIL.equalsIgnoreCase(email)){
                String s = student.toString();
                System.out.println(s);
            }

            student = next();
        }
    }

    /**
     * : Search for a student’s record given an ID number.
     * @param ID
     * @return Student
     */
    public Student searchID(int ID){
        Student student = first();
        Student searchedStudent= null;
        while (student != null)
        {
            int id = student.getStudentID();

            if (id == ID){
                searchedStudent=student;
            }

            student = next();
        }

        return searchedStudent;

    }
}
