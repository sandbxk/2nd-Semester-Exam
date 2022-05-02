package Application.BLL;

import Application.BE.Student;
import Application.DAL.AdminStudentDAO;

import java.util.List;

public class AdminDataManager {

    private AdminStudentDAO adminStudentDAO;


    public AdminDataManager() {
        adminStudentDAO = new AdminStudentDAO();
    }

    public void createStudent(String username, String password, String firstName, String lastName, String email, int salt, int schoolID) {
        adminStudentDAO.createStudent(username, password, firstName, lastName, email, salt, schoolID);
    }

    public void updateStudent(Student student){
        adminStudentDAO.updateStudent(student);
    }

    public void deleteStudent(Student student){
        adminStudentDAO.deleteStudent(student.getId());
    }

    public Student getStudent(int id){
        return adminStudentDAO.getStudent(id);
    }

    public List<Student> getAllStudents(){
        return adminStudentDAO.getAllStudents();
    }

}
