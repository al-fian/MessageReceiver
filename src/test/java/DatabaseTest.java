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
                "comedian", AgeCategory.ADULT, EmploymentCategory.SELF_EMPLOYED,
                "1771", true, Gender.MALE));

        _db.addPerson(new Person("McDuck",
                "banker", AgeCategory.SENIOR, EmploymentCategory.SELF_EMPLOYED,
                "03421", true, Gender.MALE));

        try {
            _db.save();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        _db.disconnect();
    }
}
