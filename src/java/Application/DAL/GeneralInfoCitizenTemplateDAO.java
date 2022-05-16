package Application.DAL;

import Application.BE.CitizenTemplate;
import Application.DAL.DBConnector.DBConnectionPool;
import Application.GUI.Models.SessionModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GeneralInfoCitizenTemplateDAO extends TemplatePatternDAO<List<CitizenTemplate>> {

    @Override
    public List<CitizenTemplate> create(List<CitizenTemplate> input)  {
        int schoolID = SessionModel.getSchool().getSchoolID();

        String sql = """
                    INSERT INTO citizens (cFirstName, cSurname, cAge, cSchool) 
                    VALUES (?, ?, ?, ?)
                    """;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {

            PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);


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
    public void update(List<CitizenTemplate> input) {

    }

    @Override
    public List<CitizenTemplate> read(int id) {
        return null;
    }

    @Override
    public List<List<CitizenTemplate>> readAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
