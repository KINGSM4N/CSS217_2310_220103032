
abstract class Notification {
    protected Sender sender;

    Notification(Sender sender) {
        this.sender = sender;
    }

    public abstract void deliver(String text);
}

class StandardAlert extends Notification {
    StandardAlert(Sender sender) {
        super(sender);
    }

    @Override
    public void deliver(String text) {
        sender.send("INFO: " + text);
    }
}

class UrgentAlert extends Notification {
    UrgentAlert(Sender sender) {
        super(sender);
    }

    @Override
    public void deliver(String text) {
        sender.send("CRITICAL: " + text + " [ACK REQUIRED]");
    }
}

class SenderLoggingProxy implements Sender {
    private final Sender realSender;

    SenderLoggingProxy(Sender realSender) {
        this.realSender = realSender;
    }

    private int sendCount = 0;

    @Override
    public void send(String message) {
        sendCount++;
        System.out.println("[AUDIT] Preparing transmission #" + sendCount + "...");
        realSender.send(message);
        System.out.println("[AUDIT] Transmission complete.");
    }
}
public class LabTask {
    public static void main(String[] args) {
// 1. Direct Bridge usage (no proxy)
        System.out.println("--- Test 1: Standard Alert via SMS (No Proxy) ---");
        Sender directSms = new SmsSender();
        Notification alert1 = new StandardAlert(directSms);
        alert1.deliver("Lab session starts in 10 minutes.");

        System.out.println();

        // 2. Bridge combined with Proxy (Logging Proxy wrapping Email)
        System.out.println("--- Test 2: Urgent Alert via Proxied Email ---");
        Sender realEmail = new EmailSender();
        Sender proxiedEmail = new SenderLoggingProxy(realEmail);

        Notification alert2 = new UrgentAlert(proxiedEmail);
        alert2.deliver("Database connection failed!");

        System.out.println();

        // 3. Second call through same proxy (verifies counter increment)
        System.out.println("--- Test 3: Second Alert through Same Proxy ---");
        alert2.deliver("Disk space low on /dev/sda1");
    }
}