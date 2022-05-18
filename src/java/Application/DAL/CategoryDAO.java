package Application.DAL;

import Application.BE.Category;
import Application.BE.Citizen;
import Application.BE.Group;
import Application.DAL.DBConnector.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends TemplatePatternDAO<Category> {
    @Override
    public Category create(Category input) {
        System.err.println("illegal");
        return null;
    }

    @Override
    public void update(Category input) {
        System.err.println("illegal");
    }

    @Override
    public Category read(int id) {
        String sql = "SELECT * FROM Categories WHERE CatID = ?";

        Connection conn = DBConnectionPool.getInstance().checkOut();

        try {
            PreparedStatement psas = conn.prepareStatement(sql);

            ResultSet rs = psas.executeQuery();
            rs.next();

            return new Category(rs.getInt("CatID"), rs.getString("catName"), null);
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
    public List<Category> readAll() {
        List<Category> categories = new ArrayList<>();

        return categories;
    }

    @Override
    public void delete(int id) {
        System.err.println("illegal");
    }

    public static void main(String[] args) {
        CategoryDAO dao = new CategoryDAO();

        System.out.println(dao.readAll());
    }
}
