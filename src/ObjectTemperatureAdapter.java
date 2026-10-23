public class ObjectTemperatureAdapter implements MetricTemperatureSensor {
    private final FahrenheitSensor sensor;

    public ObjectTemperatureAdapter(FahrenheitSensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public double getTemperatureCelsius() {
        return (sensor.readFahrenheit() - 32) * 5.0 / 9.0;
    }

    public static void main(String[] args) {
        final String STUDENT_ID = "220103032";
        System.out.println("Student ID: " + STUDENT_ID);

        MetricTemperatureSensor classAdapter = new ClassTemperatureAdapter();
        MetricTemperatureSensor objectAdapter = new ObjectTemperatureAdapter(new FahrenheitSensor());

        double c1 = classAdapter.getTemperatureCelsius();
        double c2 = objectAdapter.getTemperatureCelsius();

        System.out.printf("Fahrenheit: %.1f%n", new FahrenheitSensor().readFahrenheit());
        System.out.printf("Class Adapter Celsius: %.2f%n", c1);
        System.out.printf("Object Adapter Celsius: %.2f%n", c2);
        System.out.println("Identical: " + (c1 == c2));
    }
}