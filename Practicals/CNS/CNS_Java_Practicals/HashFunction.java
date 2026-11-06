import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
public class HashFunction {
    public static void main(String[] args) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest("HELLO".getBytes(StandardCharsets.UTF_8));
        System.out.print("SHA-256: ");
        for (byte b : hash) System.out.printf("%02x", b);
        System.out.println();
    }
}
