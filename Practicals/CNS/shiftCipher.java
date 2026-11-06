import java.util.Scanner;

class ShiftCipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String plaintext = sc.nextLine();
        int key = sc.nextInt();
        char[] arr = plaintext.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (char) ((arr[i] - 'A' + key) % 26 + 'A');
        }
        String encrypted = new String(arr);
        System.out.println("Encrypted: " + encrypted);

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (char) ((arr[i] - 'A' - key + 26) % 26 + 'A');
        }
        String decrypted = new String(arr);
        System.out.println("Decrypted: " + decrypted);
        sc.close();
    }
}