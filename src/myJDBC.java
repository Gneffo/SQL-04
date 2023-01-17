import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class myJDBC {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/newdb", "root", "gneffgneff1M");

            Statement statement = connection.createStatement();

            statement.executeUpdate("create view italian_students as select first_name," +
                    " last_name from students where country = 'Italy'");

            statement.executeUpdate("create view german_students as select first_name," +
                    " last_name from students where country = 'Germany'");


            ResultSet resultSet = statement.executeQuery("select first_name, " +
                    "last_name from italian_students");

            List<Student> italianStudents = new ArrayList<>();

            List<Student> germanStudents = new ArrayList<>();

            while (resultSet.next()) {
                Student student = new Student(resultSet.getString("first_name"),
                        resultSet.getString("last_name"));
                italianStudents.add(student);
                student.printStudentDetails();

            }
            ResultSet resultSet1 = statement.executeQuery("select first_name," +
                    "last_name from german_students");


            while (resultSet1.next()) {
                Student student1 = new Student(resultSet1.getString("first_name"),
                        resultSet1.getString("last_name"));
                germanStudents.add(student1);
                student1.printStudentDetails();

            }
        }catch(Exception e)

        {
            e.printStackTrace();
        }
    }
}