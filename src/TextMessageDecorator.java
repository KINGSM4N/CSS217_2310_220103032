public abstract class TextMessageDecorator implements TextMessage {
    protected final TextMessage wrapped;

    protected TextMessageDecorator(TextMessage wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public String getContent() {
        return wrapped.getContent();
    }
}