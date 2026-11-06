import java.util.Scanner;
public class OneTimePad {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter message (A-Z): ");
        String msg = sc.next().toUpperCase();
        System.out.print("Enter key of same length: ");
        String key = sc.next().toUpperCase();
        if (msg.length() != key.length() || !msg.matches("[A-Z]+") || !key.matches("[A-Z]+")) {
            System.out.println("Enter A-Z text and a key of equal length.");
            sc.close();
            return;
        }
        String enc = "", dec = "";
        for (int i = 0; i < msg.length(); i++) {
            int p = msg.charAt(i) - 'A', k = key.charAt(i) - 'A';
            enc += (char)('A' + (p + k) % 26);
        }
        for (int i = 0; i < enc.length(); i++) {
            int c = enc.charAt(i) - 'A', k = key.charAt(i) - 'A';
            dec += (char)('A' + (c - k + 26) % 26);
        }
        System.out.println("Encrypted: " + enc);
        System.out.println("Decrypted: " + dec);
        sc.close();
    }
}
