import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
public class DES {
    public static void main(String[] args) throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance("DES");
        SecretKey key = kg.generateKey();
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        byte[] msg = "HELLO".getBytes(StandardCharsets.UTF_8);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(msg);
        System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(encrypted));
        cipher.init(Cipher.DECRYPT_MODE, key);
        System.out.println("Decrypted: " + new String(cipher.doFinal(encrypted), StandardCharsets.UTF_8));
    }
}
