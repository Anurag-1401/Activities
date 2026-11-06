import java.util.Scanner;
public class ShiftCipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter message: ");
        String msg = sc.nextLine().toUpperCase();
        System.out.print("Enter key (0-25): ");
        int key = sc.nextInt();
        String enc = "", dec = "";
        for (char c : msg.toCharArray())
            enc += (c >= 'A' && c <= 'Z') ? (char)('A' + (c-'A'+key)%26) : c;
        for (char c : enc.toCharArray())
            dec += (c >= 'A' && c <= 'Z') ? (char)('A' + (c-'A'-key+26)%26) : c;
        System.out.println("Encrypted: " + enc);
        System.out.println("Decrypted: " + dec);
        sc.close();
    }
}
