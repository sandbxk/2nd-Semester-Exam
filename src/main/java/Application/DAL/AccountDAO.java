package Application.DAL;

import Application.BE.Account;
import Application.BE.School;
import Application.DAL.DBConnector.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO extends TemplatePatternDAO<Account> {

    @Override
    public Account create(Account input) {
        String sql = """
                    INSERT INTO account (login, password, firstName, surname, email, school, auth) 
                    VALUES (?, ?, ?, ?, ?, ?, ?)
                    """;

        try {
            Connection conn = DBConnectionPool.getInstance().checkOut().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, input.getLogin());
            pstmt.setString(2, input.getPassword());
            pstmt.setString(3, input.getFirstName());
            pstmt.setString(4, input.getLastName());
            pstmt.setString(5, input.getEmail());
            pstmt.setInt(6, input.getSchool().getSchoolID());
            pstmt.setInt(7, input.getAuthorization());

            pstmt.executeUpdate();

            int id = -1;

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }

            pstmt.close();
            return new Account(
                    id,
                    input.getLogin(),
                    input.getPassword(),
                    input.getFirstName(),
                    input.getLastName(),
                    input.getEmail(),
                    input.getSchool(),
                    input.getAuthorization()
            );

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void delete(int accountid){
        String sql = "DELETE FROM account WHERE id = ?";

        try {
            Connection conn = DBConnectionPool.getInstance().checkOut().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, accountid);

            pstmt.executeUpdate();

            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Account read(int accountID){
        String sql = """
                    SELECT id, firstName, surname, email FROM accounts WHERE id = ?
                    JOIN school ON accounts.school = school.id
                    """;

        School school = null;
        Account student = null;

        try {
            Connection conn = DBConnectionPool.getInstance().checkOut().getConnection();

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, accountID);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                school = new School(
                        rs.getInt(""),
                        rs.getString("schoolName"),
                        rs.getInt("zipCode"),
                        rs.getString("cityName")
                );

                student = new Account(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("studentFirstName"),
                        rs.getString("studentSurname"),
                        rs.getString("studentEmail"),
                        school,
                        rs.getInt("auth")
                );
            }

            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    @Override
    public List<Account> readAll() {
        String sql = """
                    SELECT id, firstName, surname, email, auth FROM accounts
                    """;
        List<Account> studentsList = new ArrayList<>();

        try {
            Connection conn = DBConnectionPool.getInstance().checkOut().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                School school = new School(
                        rs.getInt(""),
                        rs.getString("schoolName"),
                        rs.getInt("zipCode"),
                        rs.getString("cityName")
                );

                Account student = new Account(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("studentFirstName"),
                        rs.getString("studentSurname"),
                        rs.getString("email"),
                        school,
                        rs.getInt("auth"));
                studentsList.add(student);
            }

            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentsList;
    }

    @Override
    public void update(Account input) {
        String sql = "UPDATE accounts SET firstName = ?, surname = ?, email = ? WHERE id = ?";

        Account account = input;
        try {
            Connection conn = DBConnectionPool.getInstance().checkOut().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, account.getFirstName());
            pstmt.setString(2, account.getLastName());
            pstmt.setString(3, account.getEmail());
            pstmt.setInt(4, account.getId());

            pstmt.executeUpdate();

            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
