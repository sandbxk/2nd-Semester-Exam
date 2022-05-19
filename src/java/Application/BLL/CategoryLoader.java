package Application.BLL;

import Application.DAL.CategoryDAO;

public class CategoryLoader
{
    CategoryDAO categoryDAO = new CategoryDAO();

    public void load()
    {
        var all =  categoryDAO.readAll();
    }
}
