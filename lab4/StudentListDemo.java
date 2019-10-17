
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StudentListDemo {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the filename to read from: ");
        String filename = keyboard.nextLine();

        File file = new File(filename);
        Scanner inputFile = new Scanner(file);

        StudentList studentList = new StudentList();


        String  firstName, lastNAme, email, major,faculty;
        Student student=null;

        while (inputFile.hasNext())
        {   String[] line =inputFile.nextLine().split(" ");
            int id    = Integer.parseInt(line[0]);
            firstName = line[1];
            lastNAme  = line[2];
            email     = line[3];
            major     = line[4];
            faculty   = line[5];

            student   = new Student(id,firstName,lastNAme,email,major,faculty);
            studentList.addRecord(student);
        }

        inputFile.close();

        System.out.println("\nThe Student List contains the following entries:");
        List<Student> list=studentList.getStudents();
        Student student1 = list.first();
        while (student1 != null) {
            System.out.println(student1.toString());
            student1 = list.next();
        }


        System.out.println("\n\nThese students are majoring in Music:");
        studentList.displayMajors("music");

        System.out.println("\n\nThese students are studying in the faculty of Science:");
        studentList.displayFaculty("science");

        System.out.println("\n\nFind the record for the student with ID '200128':");
        System.out.println(studentList.searchID(200128).toString());

        System.out.println("\n\nFind the record for the student with the name 'Mike Williams':");
        studentList.displayName("Williams");

        System.out.println("\n\nFind the record for the student with the email 'apage@email.com':");
        studentList.displayEmail("apage@email.com");

        System.out.println("\n\nRemove 5 students from the Student List...\n" +
                "The Student List now contains the following entries:");
        studentList.deleteRecord(200120);
        studentList.deleteRecord(200121);
        studentList.deleteRecord(200122);
        studentList.deleteRecord(200123);
        studentList.deleteRecord(200124);

        student1 = list.first();
        while (student1 != null) {
            System.out.println(student1.toString());
            student1 = list.next();
        }


    }
}
