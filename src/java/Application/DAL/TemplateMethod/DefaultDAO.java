package Application.DAL.TemplateMethod;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DefaultDAO
{
    private String CreateCSV(AnnotatedElement[] array)
    {
        return null;
    }

    public <T> T create(T data)
    {
        var fields = data.getClass().getFields();

        if (fields.length == 0)
        {
            // no fields or they are private / protected
            System.out.println("no field access");
        }

        var methods = data.getClass().getMethods();

        if (methods.length == 0)
        {
            // no methods or they are private / protected
            System.out.println("no method access");
        }




        var f = data.getClass().getAnnotations();
/*
        var exec = new AbstractDAO_TP<T, T>() {

            @Override
            protected T execute(PreparedStatement statement, T input) throws SQLException {
                return null;
            }

            @Override
            protected String getSQLStatement() {
                // "INSERT INTO <table> (<column1>, <column1>) VALUES (?, ?)";
                return null;
            }
        };

        exec.run(data);

        return exec.getResult();

 */
        return null;
    }
}
