package Application.DAL;

import Application.BE.City;
import Application.DAL.DBConnector.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ZipcodeDAO {

    public List<City> readAllCity()
    {
        List<City> cityList = new ArrayList<City>();
        String sqlreadAll = """
                SELECT * FROM Zipcode
                """;
        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {
            PreparedStatement psrac = conn.prepareStatement(sqlreadAll);

            ResultSet rs = psrac.executeQuery();

            while (rs.next()) {
                String cityName = rs.getString("city");
                int zipCode = rs.getInt("Zip");
                City city = new City(cityName, zipCode);
                cityList.add(city);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cityList;
    }

    public City readCity(int zipCode) throws SQLException {
        City city = new City();
        String cityName = null;
        String sqlRead = """
                Select city FROM Zipcode
                where Zip = ?
                """;
        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {
            PreparedStatement psrc = conn.prepareStatement(sqlRead);

            ResultSet rs = psrc.executeQuery();
            cityName = rs.getString("city");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        city.setZipCode(zipCode);
        city.setCityName(cityName);
        return city;
    }
}
