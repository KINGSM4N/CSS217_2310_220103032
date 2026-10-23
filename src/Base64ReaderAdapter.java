import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64ReaderAdapter extends Reader {
    private final Reader decoded;

    public Base64ReaderAdapter(InputStream base64Stream) {
        InputStream decodingStream = Base64.getDecoder().wrap(base64Stream);
        this.decoded = new InputStreamReader(decodingStream, StandardCharsets.UTF_8);
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return decoded.read(cbuf, off, len);
    }

    @Override
    public void close() throws IOException {
        decoded.close();
    }

    public static void main(String[] args) throws IOException {
        final String STUDENT_ID = "220103032";
        System.out.println("Student ID: " + STUDENT_ID);

        String encoded = Base64.getEncoder()
                .encodeToString(STUDENT_ID.getBytes(StandardCharsets.UTF_8));
        System.out.println("Encoded: " + encoded);

        ByteArrayInputStream in =
                new ByteArrayInputStream(encoded.getBytes(StandardCharsets.UTF_8));

        String result;
        try (BufferedReader reader = new BufferedReader(new Base64ReaderAdapter(in))) {
            result = reader.readLine();
        }
        System.out.println("Decoded: " + result);

        if (!STUDENT_ID.equals(result)) {
            throw new AssertionError("Decoded string does not match Student ID");
        }
        System.out.println("Assertion passed: decoded == Student ID");
    }
}