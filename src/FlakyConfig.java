import java.io.IOException;
import java.sql.SQLException;

public class FlakyConfig {
    public String getRaw(String k) throws IOException, SQLException {
        switch (k) {
            case "app.name":
                return "MyApp";
            case "io.fail":
                throw new IOException("Disk read failed for key: " + k);
            case "db.fail":
                throw new SQLException("Database unavailable for key: " + k);
            default:
                return "value-of-" + k;
        }
    }
}