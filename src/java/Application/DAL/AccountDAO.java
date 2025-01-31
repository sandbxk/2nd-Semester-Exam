package Application.DAL;

import Application.BE.Account;
import Application.BE.Location;
import Application.BE.School;
import Application.DAL.DBConnector.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO extends TemplatePatternDAO<Account> {

    /**
     * Creates an account from account entity given.
     * @param input
     * @return
     */
    @Override
    public Account create(Account input) {
        String sql = """
                    INSERT INTO Account (username, hashed_pwd, firstName, lastname, email, FK_AccountSchool, privilegeLevel) 
                    VALUES (?, ?, ?, ?, ?, ?, ?)
                    """;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, input.getUsername());
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
                    input.getUsername(),
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
        finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }


    /**
     * Deletes a specific account based on account entity given.
     * @param accountid
     */
    @Override
    public void delete(int accountid){
        String sql = """
                    DELETE FROM account
                    WHERE AID = ?
                    """;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, accountid);

            pstmt.executeUpdate();

            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }

    /**
     * Returns a specific account based on account entity id given.
     * @param accountID
     * @return
     */
    @Override
    public Account read(int accountID){
        String sql = """
                    SELECT * FROM Account
                    JOIN School ON Account.FK_AccountSchool = School.SID
                    JOIN zipCode ON School.FK_Zipcode = zipCode.Zip
                    WHERE AID = ?
                    """;

        School school = null;
        Account account = null;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, accountID);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                school = new School(
                        rs.getInt("schoolId"),
                        rs.getString("schoolName"),
                        new Location(rs.getInt("zipCode"), rs.getString("cityName"))
                );

                account = new Account(
                        rs.getInt("accountId"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("firstName"),
                        rs.getString("surname"),
                        rs.getString("email"),
                        school,
                        rs.getInt("auth")
                );
            }
            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }

        return account;
    }


    /**
     * Returns a list of all accounts.
     * @return
     */
    @Override
    public List<Account> readAll() {
        String sql = """
                    SELECT * FROM Account
                    JOIN School ON Account.FK_AccountSchool = School.SID
                    JOIN ZipCode ON School.FK_Zipcode = ZipCode.Zip
                    """;
        List<Account> studentsList = new ArrayList<>();

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                School school = new School(
                        rs.getInt("schoolID"),
                        rs.getString("schoolName"),
                        new Location(rs.getInt("zipCode"), rs.getString("cityName"))
                );

                Account student = new Account(
                        rs.getInt("accountId"),
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
        finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }

        return studentsList;
    }

    /**
     * Updates accounts using an account entity.
     * @param input
     */
    @Override
    public void update(Account input) {
        String sql = """
                     UPDATE Account
                     SET firstName = ?, lastname = ?, email = ? 
                     WHERE AID = ?
                     """;

        Account account = input;
        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {
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
        finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }

    public Account read(String username)
    {
        return null;
    }
}
