import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
public class AES {
    public static void main(String[] args) throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(128);
        SecretKey key = kg.generateKey();
        byte[] ivBytes = new byte[16];
        new SecureRandom().nextBytes(ivBytes);
        IvParameterSpec iv = new IvParameterSpec(ivBytes);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] msg = "HELLO".getBytes(StandardCharsets.UTF_8);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] encrypted = cipher.doFinal(msg);
        System.out.println("IV: " + Base64.getEncoder().encodeToString(ivBytes));
        System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(encrypted));
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        System.out.println("Decrypted: " + new String(cipher.doFinal(encrypted), StandardCharsets.UTF_8));
    }
}
