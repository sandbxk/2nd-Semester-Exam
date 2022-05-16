package Application.DAL;

import Application.BE.Case;
import Application.BE.CitizenBaseData;
import Application.BE.CitizenTemplate;
import Application.DAL.DBConnector.DBConnectionPool;
import Application.GUI.Models.SessionModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CitizenTemplateDAO extends TemplatePatternDAO<CitizenTemplate> {


    @Override
    public CitizenTemplate create(CitizenTemplate input)
    {
        String fName = input.getBaseData().getName();
        String lName = input.getBaseData().getSurname();
        int age = input.getBaseData().getAge();
        int schoolID = SessionModel.getSchool().getSchoolID();

        String sql = """
                    INSERT INTO citizens (cFirstName, cSurname, cAge, cSchool) 
                    VALUES (?, ?, ?, ?)
                    """;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, fName);
            pstmt.setString(2, lName);
            pstmt.setInt(3, age);
            pstmt.setInt(4, schoolID);

            pstmt.executeUpdate();

            int id = -1;

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
                input.setId(id);
            }

            pstmt.close();

            return input;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }

    @Override
    public void update(CitizenTemplate input) {

    }

    @Override
    public CitizenTemplate read(int id) {
        return null;
    }

    @Override
    public List readAll() {

        String sql = """
                    SELECT * FROM citizens
                    """;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        List<CitizenTemplate> citizens = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("citizenId");
                String fName = resultSet.getString("cFirstName");
                String lName = resultSet.getString("cSurname");
                int age = resultSet.getInt("cAge");

                citizens.add(new CitizenTemplate(id, new CitizenBaseData(fName, lName, age), null));
            }

            pstmt.close();

            return citizens;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }

    @Override
    public void delete(int id) {

    }
}
