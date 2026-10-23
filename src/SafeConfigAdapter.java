import java.io.IOException;
import java.sql.SQLException;

public class SafeConfigAdapter implements ConfigService{
    private final FlakyConfig config;

    public SafeConfigAdapter(FlakyConfig config){
        this.config = config;
    }

    @Override
    public String readKey(String key) {
        try {
            return config.getRaw(key);
        } catch (IOException | SQLException e) {
            throw new ConfigNotFoundException("Cannot read config key: " + key, e);
        }
    }

    public static void main(String[] args) {
        final String STUDENT_ID = "220103032";
        System.out.println("Student ID: " + STUDENT_ID);

        ConfigService service = new SafeConfigAdapter(new FlakyConfig());

        System.out.println("app.name = " + service.readKey("app.name"));

        try {
            service.readKey("io.fail");
        } catch (ConfigNotFoundException e) {
            System.out.println("Caught: " + e.getMessage());
            System.out.println("Underlying cause: " + e.getCause().getClass().getName());
        }

        try {
            service.readKey("db.fail");
        } catch (ConfigNotFoundException e) {
            System.out.println("Caught: " + e.getMessage());
            System.out.println("Underlying cause: " + e.getCause().getClass().getName());
        }
    }

}
