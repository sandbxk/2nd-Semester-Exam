package Application.DAL.TemplateMethod;

import Application.BE.Location;
import Application.BE.School;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SchoolDAO2
{
    public final AbstractDAO_TP<Object, Object> read = new AbstractDAO_TP<>() {
        @Override
        protected Object execute(PreparedStatement statement, Object input) {
            return null;
        }

        @Override
        protected String getSQLStatement() {
            return null;
        }
    };

    public School create(School input){
        var query = new AbstractDAO_TP<School, School>() {
            @Override
            protected School execute(PreparedStatement statement, School input) throws SQLException {
                statement.setString(1,input.getSchoolName());
                statement.setInt(2, input.getLocation().getZipCode() );

                statement.execute();

                return null; // return value not used; only the id
            }

            @Override
            protected String getSQLStatement() {
                return "INSERT INTO schools (schoolName, schoolZipCode) VALUES (?, ?)";
            }
        };

        if (!query.run(input))
        {
            //query.getLastError().printStackTrace();
            return null;
        }

        return new School(
                (int) query.getResultID(),
                input.getSchoolName(),
                new Location(input.getLocation().getZipCode()) // location
        );
    }

}
