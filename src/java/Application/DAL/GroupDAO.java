package Application.DAL;

import Application.BE.Citizen;
import Application.BE.Group;
import Application.DAL.DBConnector.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDAO extends TemplatePatternDAO<Group>
{

    @Override
    public Group create(Group input)
    {
        String sql = "INSERT INTO 'Group' (groupName, FK_Citizen) VALUES (?, ?)";

        Connection conn = DBConnectionPool.getInstance().checkOut();

        try
        {
            PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setString(1,input.getGroupName());
            pstmt.setInt(2, input.getAssignedCitizen().getId());

            pstmt.execute();

            int id = -1;

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }

            pstmt.close();

            input.setId(id);

            return input;

        }
        catch (SQLException throwable)
        {
            throwable.printStackTrace();
            return null;
        }
        finally
        {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }

    @Override
    public void update(Group input) {
        String sql = "UPDATE [Group] SET groupName =  ?, FK_Citizen = ? WHERE GID = ?";

        Connection conn = DBConnectionPool.getInstance().checkOut();

        try {
            PreparedStatement psus = conn.prepareStatement(sql);

            psus.setString(1, input.getGroupName());
            psus.setInt(2, input.getAssignedCitizen().getId());
            psus.setInt(3, input.getId());
            psus.executeUpdate();
            psus.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }

    @Override
    public Group read(int id) {
        String sqlRead = "SELECT * FROM [Group] WHERE GID = ?";


        Connection conn = DBConnectionPool.getInstance().checkOut();

        try {
            PreparedStatement psas = conn.prepareStatement(sqlRead);

            ResultSet rs = psas.executeQuery();
            rs.next();
            return new Group( rs.getInt("GID"),
                    rs.getString("groupName"),
                    null,
                    new Citizen(rs.getInt("Zip")));
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }

    @Override
    public List<Group> readAll()
    {
        String sqlRead = "SELECT * FROM [Group]";

        List<Group> groups = new ArrayList<>();

        Connection conn = DBConnectionPool.getInstance().checkOut();

        try {
            PreparedStatement psas = conn.prepareStatement(sqlRead);

            ResultSet rs = psas.executeQuery();

            while (rs.next()) {
                groups.add(
                        new Group(
                                rs.getInt("GID"),
                                rs.getString("groupName"),
                                null,
                                new Citizen(rs.getInt("Zip"))
                        )
                );
            }

            return groups;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }

    @Override
    public void delete(int id)
    {
        String sql = "DELETE FROM [Group] WHERE GID = ?";

        Connection conn = DBConnectionPool.getInstance().checkOut();

        try {
            PreparedStatement psds = conn.prepareStatement(sql);

            psds.setInt(1, id);

            psds.executeUpdate();
            psds.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }
}
