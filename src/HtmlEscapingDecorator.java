public class HtmlEscapingDecorator extends TextMessageDecorator {
    public HtmlEscapingDecorator(TextMessage wrapped) {
        super(wrapped);
    }

    @Override
    public String getContent() {
        return super.getContent()
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }
}