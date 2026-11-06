import java.util.Scanner;

class MonoAlphaSub {
    public static void main(String[] args) {

        String preDefined = "qazwsxcderfvtgbnhyujmkiopl";

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the substituition: ");
        String userDefined = sc.nextLine();

        System.out.print("Enter the plaintext: ");
        String plaintext = sc.nextLine().toLowerCase();

        System.out.println();

        String ctByPreDefined = "";
        String ctByUserDefined = "";

        for (char c : plaintext.toCharArray()) {
            ctByUserDefined += userDefined.charAt(c - 'a');
            ctByPreDefined += preDefined.charAt(c - 'a');
        }

        System.out.println("Encrypted Text By Predefined Key: " + ctByPreDefined);
        System.out.println("Encrypted Text By User defined Key: " + ctByUserDefined);

        System.out.println();

        sc.close();

        String ptByPreDefined = "";
        String ptByUserDefined = "";

        for (char c : ctByPreDefined.toCharArray()) {
            ptByPreDefined += (char) (preDefined.indexOf(c) + 'a');
        }

        for (char c : ctByUserDefined.toCharArray()) {
            ptByUserDefined += (char) (userDefined.indexOf(c) + 'a');
        }

        System.out.println("Decrypted Text By Predefined Key: " + ptByPreDefined);
        System.out.println("Decrypted Text By User defined Key: " + ptByUserDefined);
    }
}