package Application.DAL;

import Application.BE.*;
import Application.DAL.DBConnector.DBConnectionPool;
import Application.GUI.Models.SessionModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CitizenTemplateDAO extends TemplatePatternDAO<CitizenTemplate> {


    @Override
    public CitizenTemplate create(CitizenTemplate input)
    {
        String fName = input.getBaseData().getName();
        String lName = input.getBaseData().getSurname();
        int age = input.getBaseData().getAge();
        int schoolID = SessionModel.getSchool().getSchoolID();
        GeneralJournal genInfo = input.getGeneralInfo();

        String baseDataSQL = """
                    INSERT INTO citizens (cFirstName, cSurname, cAge, cSchool) 
                    VALUES (?, ?, ?, ?);
                    """;

        String genInfoSQL = """
                    INSERT INTO citizens (cFirstName, cSurname, cAge, cSchool) 
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
                    """;

        String categorySQL = """
                    INSERT INTO citizens (cFirstName, cSurname, cAge, cSchool) 
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
                    """;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {
            conn.setAutoCommit(false);

            //Base data transaction
            PreparedStatement baseDataPSTMT = conn.prepareStatement(baseDataSQL, PreparedStatement.RETURN_GENERATED_KEYS);
            baseDataPSTMT.setString(1, fName);
            baseDataPSTMT.setString(2, lName);
            baseDataPSTMT.setInt(3, age);
            baseDataPSTMT.setInt(4, schoolID);
            baseDataPSTMT.executeUpdate();

            //General info transaction
            PreparedStatement genInfoPSTMT = conn.prepareStatement(genInfoSQL);
            genInfoPSTMT.setString(1, genInfo.getMastering());
            genInfoPSTMT.setString(2, genInfo.getMotivation());
            genInfoPSTMT.setString(3, genInfo.getResources());
            genInfoPSTMT.setString(4, genInfo.getRoles());
            genInfoPSTMT.setString(5, genInfo.getHabits());
            genInfoPSTMT.setString(6, genInfo.getEduAndJob());
            genInfoPSTMT.setString(7, genInfo.getLifeStory());
            genInfoPSTMT.setString(8, genInfo.getHealthInfo());
            genInfoPSTMT.setString(9, genInfo.getAssistiveDevices());
            genInfoPSTMT.setString(10, genInfo.getHomeLayout());
            genInfoPSTMT.setString(11, genInfo.getNetwork());
            genInfoPSTMT.executeUpdate();

            //Category entries transaction
            PreparedStatement categoryPSTMT = conn.prepareStatement(categorySQL);
            for (CategoryEntry categoryEntry : input.getFunctionalAbilities()) {

            }
            for (CategoryEntry categoryEntry : input.getHealthConditions()) {

            }




            conn.commit();

            int id = -1;

            ResultSet generatedKeys = baseDataPSTMT.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
                input.setId(id);
            }
            conn.setAutoCommit(true);
            baseDataPSTMT.close();

            return input;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
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
    public void delete(int id) {

    }

    @Override
    public CitizenTemplate read(int id) {
        return null;
    }

    @Override
    public List readAll() {

        String sql = """
                    SELECT * FROM citizens WHERE template_id = NULL;
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

                CitizenTemplate citizen = new CitizenTemplate(id, new CitizenBaseData(fName, lName, age), readGeneralInfo(id));
                citizens.add(citizen);
                citizen.setFunctionalAbilities(readFunctionalAbilities(id));
                citizen.setHealthConditions(readHealthConditions(id));
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

    private List<CategoryEntry> readHealthConditions(int id) {
        List<CategoryEntry> healthConditions = new ArrayList<>();

        return healthConditions;
    }

    private List<CategoryEntry> readFunctionalAbilities(int id) {
        List<CategoryEntry> functionalAbilities = new ArrayList<>();

        String sql = """
                    SELECT * FROM citizens WHERE citizenId = ?
                    """;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
            //CategoryEntry entry = new CategoryEntry(resultSet.getInt());

            }

            return functionalAbilities;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }


    public GeneralJournal readGeneralInfo(int templateID){
        String sql = """
                    SELECT * FROM GeneralInfo WHERE InfoID = ?
                    """;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        GeneralJournal journal = new GeneralJournal();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                journal.setMastering(resultSet.getString("mastering"));
                journal.setMotivation(resultSet.getString("motivation"));
                journal.setResources(resultSet.getString("resources"));
                journal.setRoles(resultSet.getString("roles"));
                journal.setHabits(resultSet.getString("habits"));
                journal.setEduAndJob(resultSet.getString("eduAndJob"));
                journal.setLifeStory(resultSet.getString("lifeStory"));
                journal.setHealthInfo(resultSet.getString("healthInfo"));
                journal.setAssistiveDevices(resultSet.getString("assistiveDevices"));
                journal.setHomeLayout(resultSet.getString("homeLayout"));
                journal.setNetwork(resultSet.getString("network"));


            }

            pstmt.close();

            return journal;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }

    public void updateGeneralInfo(){

    }

    public HashMap<CategoryEntry, CategoryEntry> readCategoryEntries(){
        HashMap<CategoryEntry, CategoryEntry> categories = new HashMap<>();

        return categories;
    }

    public void updateCategoryEntries(){

    }

}
