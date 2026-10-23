import java.math.BigDecimal;
import java.math.RoundingMode;

class OldPayGateway {
    public void chargeInCents(int cents, String accountRef) {
        System.out.println("OldPayGateway: charged " + cents + " cents to account " + accountRef);
    }
}
public class PaymentAdapter implements PaymentProcessor {
    private final OldPayGateway gateway;

    public PaymentAdapter(OldPayGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void processPayment(String customerId, double amountInDollars) {
        int cents = BigDecimal.valueOf(amountInDollars)
                .movePointRight(2)
                .setScale(0, RoundingMode.HALF_UP)
                .intValueExact();
        gateway.chargeInCents(cents, customerId);
    }

    public static void main(String[] args) {
        final String STUDENT_ID = "220103032";
        System.out.println("Student ID: " + STUDENT_ID);

        PaymentProcessor processor = new PaymentAdapter(new OldPayGateway());
        processor.processPayment(STUDENT_ID, 142.75);
    }
}