import org.message_receiver.models.Database;

public class DatabaseTest {

    public static void main(String[] args) {
        System.out.println("Running database test ...");

        Database _db = new Database();
        try {
            _db.connect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        _db.disconnect();
    }
}
