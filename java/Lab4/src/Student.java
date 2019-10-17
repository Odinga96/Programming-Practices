import java.util.Objects;

public class Student {

    private  int  StudentID;
    private  String  FirstName;
    private  String  LastName;
    private  String  Email;
    private  String  Major;
    private  String  Faculty;

    public Student(int studentID, String firstName, String lastName, String email, String major, String faculty) {
        StudentID = studentID;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        Major = major;
        Faculty = faculty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;

        return  Objects.equals(StudentID, student.StudentID) &&
                Objects.equals(FirstName, student.FirstName) &&
                Objects.equals(LastName, student.LastName) &&
                Objects.equals(Email, student.Email) &&
                Objects.equals(Major, student.Major) &&
                Objects.equals(Faculty, student.Faculty);
    }

    @Override
    public String toString() {
        return  getStudentID() + "    "+
                getFirstName() + "    "+
                getLastName()  + "    "+
                getEmail()     + "    "+
                getFaculty()   + "    "+
                getMajor();
    }

    public int  getStudentID() {
        return StudentID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getEmail() {
        return Email;
    }

    public String getMajor() {
        return Major;
    }

    public String getFaculty() {
        return Faculty;
    }
}
