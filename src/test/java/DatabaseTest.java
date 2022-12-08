import org.message_receiver.models.*;

import java.sql.SQLException;

public class DatabaseTest {

    public static void main(String[] args) {
        System.out.println("Running database test ...");

        Database _db = new Database();
        try {
            _db.connect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        _db.addPerson(new Person("Donald",
                "sailor", AgeCategory.ADULT, EmploymentCategory.UNEMPLOYED,
                "1771", true, Gender.MALE));

        _db.addPerson(new Person("McDuck",
                "treasure hunter", AgeCategory.ADULT, EmploymentCategory.SELF_EMPLOYED,
                null, false, Gender.MALE));

        try {
            _db.save();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        _db.disconnect();
    }
}
