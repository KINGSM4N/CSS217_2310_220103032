public class ClassTemperatureAdapter extends FahrenheitSensor implements MetricTemperatureSensor {
    @Override
    public double getTemperatureCelsius() {
        return (readFahrenheit() - 32) * 5.0 / 9.0;
    }
}