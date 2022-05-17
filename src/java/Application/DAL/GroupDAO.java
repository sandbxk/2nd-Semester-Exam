package Application.DAL;

import Application.BE.Account;
import Application.BE.Group;
import Application.DAL.DBConnector.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GroupDAO extends TemplatePatternDAO<Group>{

    private long return_id = -1;

    @Override
    public Group create(Group input) {
        String sqlCreateGroup = """
                INSERT INTO [Group] 
                (groupName, FK_Citizen)
                VALUES
                (?, ?)
                """;


        String sqlAccountGroup = """
                INSERT INTO AccountGroup 
                (FK_GroupID, FK_MemberID)
                VALUES
                (?, ?)
                """;
        Connection conn = DBConnectionPool.getInstance().checkOut();

        try {
            PreparedStatement pscg = conn.prepareStatement(sqlCreateGroup, PreparedStatement.RETURN_GENERATED_KEYS);
            PreparedStatement psag = conn.prepareStatement(sqlCreateGroup);

            pscg.setString(1,input.getGroupName());
            pscg.setInt(2,input.getCitizen().getId());

            ResultSet rs = pscg.getGeneratedKeys();
            if (rs.next())
                this.return_id = rs.getInt(1);

            for (Account account: input.getGroupMembers())
                psag.setLong(1,return_id);
                psag.setInt(2,input.getCitizen().getId());
                psag.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Group input) {

    }

    @Override
    public Group read(int id) {
        return null;
    }

    @Override
    public List<Group> readAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
