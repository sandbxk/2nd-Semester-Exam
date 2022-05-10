package Application.DAL.TemplateMethod;

import Application.BE.Citizen;
import Application.DAL.TemplateMethod.AbstractDAO_TP;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CitizenDAO
{

    public final Citizen create(Citizen citizen) {
        var action = new AbstractDAO_TP<Citizen, Citizen>() {
            @Override
            protected Citizen execute(PreparedStatement statement, Citizen input) throws SQLException {

                statement.setString(1, input.getFirstname());
                statement.setString(2, input.getLastname());
                statement.setInt(3, input.getAge());
                statement.setInt(4, input.getStreetID());
                statement.setInt(5, input.getStreetNumber());
                statement.setInt(6, input.getZipCode());
                statement.setInt(7, input.getSchoolID());

                statement.executeUpdate();

                return input;
            }

            @Override
            protected String getSQLStatement() {
                return "INSERT INTO citizen (cFirstName, cSurname, cAge, cStreet, cStreetNumber, cZipCode, cSchool)" +
                        " VALUES (?, ?, ?, ?, ?, ?, ?)";
            }
        };

        action.run(citizen);

        var updated = action.getResult();
        updated.setId((int) action.getResultID());

        return updated;
    }

    public final List<Citizen> readAll(int schoolID)
    {
       var action = new AbstractDAO_TP<List<Citizen>, Integer>() {
            @Override
            protected List<Citizen> execute(PreparedStatement statement, Integer input) throws SQLException {
                statement.setInt(1, input);

                List<Citizen> array = new ArrayList<>();

                var result = statement.executeQuery();

                while (result.next())
                {
                    Citizen citizen = new Citizen(
                            result.getInt("citizenId"),
                            result.getString("cFirstName"),
                            result.getString("cSurname"),
                            result.getInt("cAge"),
                            result.getInt("cStreet"),
                            result.getInt("cStreetNumber"),
                            result.getInt("cZipCode"),
                            result.getInt("cSchool")
                    );

                    array.add(citizen);
                }

                return array;
            }

            @Override
            protected String getSQLStatement() {
                return "SELECT * FROM citizen where cSchool = ?";
            }
       };

       action.run(schoolID);

       return action.getResult();
    }

}
