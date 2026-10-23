public class DecoratorDemo {
    public static void main(String[] args) {
        final String STUDENT_ID = "220103032";
        System.out.println("Student ID: " + STUDENT_ID);

        TextMessage message = new SimpleTextMessage("Alert: 5 < 10 & 2 > 1");
        TextMessage decorated =
                new WatermarkDecorator(new HtmlEscapingDecorator(message), STUDENT_ID);

        System.out.println(decorated.getContent());
    }
}