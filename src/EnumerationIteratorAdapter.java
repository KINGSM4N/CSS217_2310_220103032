import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

public class EnumerationIteratorAdapter<T> implements Iterator<T> {
    private final Enumeration<T> enumeration;

    public EnumerationIteratorAdapter(Enumeration<T> enumeration) {
        this.enumeration = enumeration;
    }

    @Override
    public boolean hasNext() {
        return enumeration.hasMoreElements();
    }

    @Override
    public T next() {
        return enumeration.nextElement();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported");
    }

    public static void main(String[] args) {
        final String STUDENT_ID = "220103032";
        System.out.println("Student ID: " + STUDENT_ID);

        Vector<String> vector = new Vector<>();
        vector.add(STUDENT_ID);
        vector.add("Item 1");
        vector.add("Item 2");
        vector.add("Item 3");

        Iterator<String> iter = new EnumerationIteratorAdapter<>(vector.elements());
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}