import java.util.Scanner;


public class messageAuthenicationCode {

    static String xor(String a, String b) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < a.length(); i++) {
            char ch = (char) (a.charAt(i) ^ b.charAt(i));
            result.append(ch);
        }

        return result.toString();
    }

    static String generateMAC(String message, String key) {

        while (message.length() % key.length() != 0) {
            message += " ";
        }

        String previous = "";

        for (int i = 0; i < key.length(); i++) {
            previous += (char) 0;
        }

        for (int i = 0; i < message.length(); i += key.length()) {

            String block = message.substring(i, i + key.length());

            String xored = xor(block, previous);

            previous = xor(xored, key);
        }

        return previous;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Message: ");
        String message = sc.nextLine();

        System.out.print("Enter Secret Key: ");
        String key = sc.nextLine();

        String mac = generateMAC(message, key);

        System.out.println("\nMessage: " + message);
        System.out.println("Secret Key: " + key);

        String cbc_mac = "";

        for (int i = 0; i < mac.length(); i++) {
            cbc_mac += String.format("%02X", (int) mac.charAt(i));
        }
        
        System.out.print("CBC-MAC: " + cbc_mac);

        System.out.print("Enter received message: ");
        String receivedMsg = sc.nextLine();
        
        String receivedMac = generateMAC(receivedMsg, key);

        String receivedMacHex = "";
        for (int i = 0; i < receivedMac.length(); i++) {
            receivedMacHex += String.format("%02X", (int) receivedMac.charAt(i));
        }

        System.out.print(cbc_mac.equals(receivedMacHex) ? "Verification Success!" : "Verification failed!");

        System.out.println();

        sc.close();
    }
}