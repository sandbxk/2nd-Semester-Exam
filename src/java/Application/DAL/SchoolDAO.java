package Application.DAL;

import Application.BE.School;
import Application.DAL.DBConnector.DBConnectionPool;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SchoolDAO {

    public void createSchool(String name, int zipCode)
    {
        String sqlCreate = "INSERT INTO school (schoolName, zipCode) VALUES (?, ?);";

        try {
            Connection conn = DBConnectionPool.getInstance().checkOut().getConnection();
            PreparedStatement pscs = conn.prepareStatement(sqlCreate);
            pscs.setString(1,name);
            pscs.setInt(2,zipCode);

            pscs.execute();
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<School> getAllSchools() throws SQLException {
        String sqlRead = """
                        SELECT * FROM school 
                        JOIN zipCode ON school.zipCode = zipCode.zipCode
                        """;
        List<School> schoolList = new ArrayList<>();
        String name;
        String city;
        int zipCode;
        int id;


        Connection conn = DBConnectionPool.getInstance().checkOut().getConnection();
        PreparedStatement psas = conn.prepareStatement(sqlRead);

        ResultSet rs = psas.executeQuery();

        while (rs.next()) {
            name = rs.getString("schoolName");
            city = rs.getString("cityName");
            zipCode = rs.getInt("zipCode");
            id = rs.getInt("id");
            School school = new School(id, name, zipCode, city);
            schoolList.add(school);
        }
        psas.close();
        return schoolList;
    }

    public void updateSchool(School school)
    {
        String sqlUpdate = """
                UPDATE school SET
                schoolName =  ?,
                zipCode = ?
                WHERE id = ?
                """;

        try {
            Connection conn = DBConnectionPool.getInstance().checkOut().getConnection();
            PreparedStatement psus = conn.prepareStatement(sqlUpdate);

            psus.setString(1,school.getName());
            psus.setInt(2,school.getZipCode());
            psus.setInt(3,school.getId());
            psus.executeUpdate();
            psus.close();
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteSchool(School school)
    {
        String sqlDelete = """
                DELETE FROM School WHERE id = ?
                """;

        try {
            Connection conn = DBConnectionPool.getInstance().checkOut().getConnection();
            PreparedStatement psds = conn.prepareStatement(sqlDelete);

            psds.setInt(1,school.getId());

            psds.executeUpdate();
            psds.close();
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void main(String[] args) throws SQLException {
        SchoolDAO DAO = new SchoolDAO();
        System.out.println(DAO.getAllSchools());
    }
}
