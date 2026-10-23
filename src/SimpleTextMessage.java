public class SimpleTextMessage implements TextMessage {
    private final String content;

    public SimpleTextMessage(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }
}