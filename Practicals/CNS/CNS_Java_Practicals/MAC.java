import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
public class MAC {
    public static void main(String[] args) throws Exception {
        String msg = "HELLO", key = "secret123";
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        byte[] result = mac.doFinal(msg.getBytes(StandardCharsets.UTF_8));
        System.out.print("HMAC-SHA256: ");
        for (byte b : result) System.out.printf("%02x", b);
        System.out.println();
    }
}
