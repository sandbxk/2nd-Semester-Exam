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
        String sqlRead = "Select * FROM school JOIN zipCode ON school.zipCode = zipCode.zipCode";
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
        return schoolList;
    }


    public static void main(String[] args) throws SQLException {
        SchoolDAO DAO = new SchoolDAO();
        System.out.println(DAO.getAllSchools());
    }
}
