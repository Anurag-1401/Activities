import java.util.Scanner;
public class MonoCipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String plain = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String key = "QWERTYUIOPASDFGHJKLZXCVBNM";
        System.out.print("Enter message: ");
        String msg = sc.nextLine().toUpperCase();
        String enc = "", dec = "";
        for (char c : msg.toCharArray()) {
            int i = plain.indexOf(c);
            enc += (i >= 0) ? key.charAt(i) : c;
        }
        for (char c : enc.toCharArray()) {
            int i = key.indexOf(c);
            dec += (i >= 0) ? plain.charAt(i) : c;
        }
        System.out.println("Encrypted: " + enc);
        System.out.println("Decrypted: " + dec);
        sc.close();
    }
}
