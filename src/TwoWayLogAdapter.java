import java.util.regex.Matcher;
import java.util.regex.Pattern;
class SimpleLegacyPrinter implements LegacyPrinter {
    @Override
    public void printLine(String combinedText) {
        System.out.println(combinedText);
    }
}
class SimpleModernConsole implements ModernConsole {
    @Override
    public void logMessage(String tag, String body) {
        System.out.println("[" + tag + "] " + body);
    }
}

public class TwoWayLogAdapter implements ModernConsole, LegacyPrinter {
    private static final Pattern TAGGED = Pattern.compile("^\\[(.*?)]\\s*(.*)$", Pattern.DOTALL);

    private final ModernConsole modern;
    private final LegacyPrinter legacy;

    public TwoWayLogAdapter(ModernConsole modern, LegacyPrinter legacy) {
        this.modern = modern;
        this.legacy = legacy;
    }

    @Override
    public void logMessage(String tag, String body) {
        legacy.printLine("[" + tag + "] " + body);
    }

    @Override
    public void printLine(String combinedText) {
        Matcher m = TAGGED.matcher(combinedText);
        if (m.matches()) {
            modern.logMessage(m.group(1), m.group(2));
        } else {
            modern.logMessage("LEGACY", combinedText);
        }
    }

    public static void main(String[] args) {
        final String STUDENT_ID = "220103032";
        System.out.println("Student ID: " + STUDENT_ID);

        TwoWayLogAdapter adapter =
                new TwoWayLogAdapter(new SimpleModernConsole(), new SimpleLegacyPrinter());

        System.out.println("--- Modern caller -> Legacy printer ---");
        adapter.logMessage(STUDENT_ID, "Hello from modern caller");

        System.out.println("--- Legacy caller -> Modern console ---");
        adapter.printLine("[" + STUDENT_ID + "] Hello from legacy caller");
    }
}
