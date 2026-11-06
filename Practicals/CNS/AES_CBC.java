import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

public class AES_CBC {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String plaintext = sc.nextLine();

        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(128);
        SecretKey key = kg.generateKey();

        byte[] ivBytes = new byte[16];
        new SecureRandom().nextBytes(ivBytes);
        IvParameterSpec iv = new IvParameterSpec(ivBytes);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] encrypted = cipher.doFinal(plaintext.getBytes());

        String ciphertext = Base64.getEncoder().encodeToString(encrypted);

        System.out.println("Encrypted: " + ciphertext);

        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] decrypted = cipher.doFinal(encrypted);

        System.out.println("Decrypted: " +
                new String(decrypted));

        sc.close();
    }
}