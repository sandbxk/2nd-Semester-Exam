package Application.DAL;

import Application.BE.Case;
import Application.DAL.DBConnector.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CasesDAO extends TemplatePatternDAO{

    @Override
    public Case create(Object input)
    {
        return null;
    }

    @Override
    public void update(Object input)
    {

    }

    @Override
    public Case read(int id)
    {
        return null;
    }

    @Override
    public List<Case> readAll()
    {
        return null;
    }

    @Override
    public void delete(int caseId)
    {
        String sql = """
                    DELETE FROM cases
                    WHERE id = ?
                    """;
        try {
            Connection conn = DBConnectionPool.getInstance().checkOut().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, caseId);

            pstmt.executeUpdate();

            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
