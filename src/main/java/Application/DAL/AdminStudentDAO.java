package Application.DAL;

import Application.BE.Student;
import Application.DAL.DBConnector.DBConnection;
import Application.DAL.DBConnector.DBConnectionPool;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminStudentDAO {

    public Student createStudent(String username, String password, String firstName, String lastName, String studentEmail, int salt, int schoolID){
        String sql = "INSERT INTO students (studentLogin, studentPassword, studentFirstName, studentSurname, studentEmail, salt, school) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = DBConnectionPool.getInstance().checkOut().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, firstName);
            pstmt.setString(4, lastName);
            pstmt.setString(5, studentEmail);
            pstmt.setInt(6, salt);
            pstmt.setInt(7, schoolID);

            pstmt.executeUpdate();

            int id = -1;

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }

            pstmt.close();
            return new Student(id, firstName, lastName, studentEmail);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public void updateStudent(Student student){
        String sql = "UPDATE students SET studentFirstName = ?, studentSurname = ?, studentEmail = ? WHERE id = ?";

        try {
            Connection conn = DBConnectionPool.getInstance().checkOut().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, student.getFirstName());
            pstmt.setString(2, student.getLastName());
            pstmt.setString(3, student.getEmail());
            pstmt.setInt(4, student.getId());

            pstmt.executeUpdate();

            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int studentID){
        String sql = "DELETE FROM students WHERE studentID = ?";

        try {
            Connection conn = DBConnectionPool.getInstance().checkOut().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, studentID);

            pstmt.executeUpdate();

            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Student getStudent(int studentID){
        String sql = "SELECT id, studentFirstName, studentSurname, studentEmail FROM students WHERE studentID = ?";
        Student student = null;

        try {
            Connection conn = DBConnectionPool.getInstance().checkOut().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, studentID);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                student = new Student(rs.getInt("studentID"), rs.getString("studentFirstName"), rs.getString("studentSurname"), rs.getString("studentEmail"));
            }

            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    public List<Student> getAllStudents(){
        String sql = "SELECT studentID, studentFirstName, studentSurname, studentEmail FROM students";
        List<Student> studentsList = new ArrayList<>();

        try {
            Connection conn = DBConnectionPool.getInstance().checkOut().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Student student = new Student(rs.getInt("studentID"), rs.getString("studentFirstName"), rs.getString("studentSurname"), rs.getString("studentEmail"));
                studentsList.add(student);
            }

            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentsList;
    }



}
