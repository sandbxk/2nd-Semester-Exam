package Application.BLL;

import Application.BE.Account;
import Application.BE.School;
import Application.DAL.AccountDAO;
import Application.DAL.TemplatePatternDAO;

import java.util.List;

public class AdminDataManager {

    private TemplatePatternDAO accountDAO;


    public AdminDataManager() {
        accountDAO = new AccountDAO();
    }

    public void createStudent(String username, String password, String firstName, String lastName, String email, School school) {
        accountDAO.create(new Account(-1, username, password, firstName, lastName, email, school, 0));
    }

    public void updateAccount(Account student){
        accountDAO.update(student);
    }

    public void deleteAccount(Account student){
        accountDAO.delete(student.getId());
    }

    public Account getStudent(int id) {
        return (Account) accountDAO.read(id);
    }

    public List<Account> getAllStudents(){
        return accountDAO.readAll();
    }

    public void createTeacher(String username, String password, String firstName, String lastName, String email, School school) {
        accountDAO.create(new Account(-1, username, password, firstName, lastName, email, school, 1));
    }

}
