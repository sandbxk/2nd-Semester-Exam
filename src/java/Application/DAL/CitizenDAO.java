package Application.DAL;

import Application.BE.Citizen;
import Application.BE.GeneralJournal;
import Application.BE.Location;
import Application.BE.School;
import Application.DAL.DBConnector.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class CitizenDAO extends TemplatePatternDAO<Citizen>
{
    @Override
    public Citizen create(Citizen input) {
        return null;
    }

    @Override
    public void update(Citizen input)
    {

    }

    @Override
    public Citizen read(int id)
    {
        String sql = """
                    SELECT * FROM Citizen
                    JOIN School ON School.SID = Citizen.FK_SchoolOwner
                    JOIN GeneralInfo ON GeneralInfo.InfoID = Citizen.FK_Info
                    JOIN Zipcode ON Zipcode.zip = School.FK_Zipcode
                    WHERE InfoID = ?
                    """;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try
        {
            PreparedStatement ptsm = conn.prepareStatement(sql);
            ptsm.setInt(1, id);

            ResultSet result = ptsm.executeQuery();

            result.next();

            Location location = new Location(
                    result.getInt("FK_Zipcode"),
                    result.getString("city")
            );

            School school = new School(
                    result.getInt("SID"),
                    result.getString("schoolName"),
                    location
            );

            GeneralJournal journal = new GeneralJournal(
                    result.getInt("InfoID"),
                    result.getString("coping"),
                    result.getString("motivation"),
                    result.getString("resources"),
                    result.getString("roles"),
                    result.getString("habits"),
                    result.getString("eduAndJob"),
                    result.getString("lifestory"),
                    result.getString("healthInfo"),
                    result.getString("assistiveDevices"),
                    result.getString("homelayout"),
                    result.getString("network")
            );

            return new Citizen(
                    result.getInt("CID"),
                    journal,
                    school,
                    result.getString("firstName"),
                    result.getString("lastName"),
                    result.getInt("age")
            );
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Citizen> readAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    public static void main(String[] args) {
        CitizenDAO daoTest = new CitizenDAO();

        System.out.print(daoTest.read(2));
    }
}
