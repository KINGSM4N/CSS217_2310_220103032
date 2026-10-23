public class WatermarkDecorator extends TextMessageDecorator {
    private final String studentId;

    public WatermarkDecorator(TextMessage wrapped, String studentId) {
        super(wrapped);
        this.studentId = studentId;
    }

    @Override
    public String getContent() {
        return super.getContent() + " [Author: " + studentId + "]";
    }
}