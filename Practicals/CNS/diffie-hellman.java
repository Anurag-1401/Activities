import java.util.Scanner;

class DiffieHellman {

    static long powerMod(long base, long exponent, long modulus) {
        long result = 1;

        for (int i = 0; i < exponent; i++) {
            result = (result * base) % modulus;
        }

        return result;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter prime number q: ");
        long q = sc.nextLong();

        System.out.print("Enter primitive root alpha: ");
        long alpha = sc.nextLong();

        System.out.print("Enter private key XA: ");
        long XA = sc.nextLong();

        System.out.print("Enter private key XB: ");
        long XB = sc.nextLong();

        long YA = powerMod(alpha, XA, q);
        long YB = powerMod(alpha, XB, q);

        System.out.println("\nPublic Key of A (YA) = " + YA);
        System.out.println("Public Key of B (YB) = " + YB);

        long KA = powerMod(YB, XA, q);
        long KB = powerMod(YA, XB, q);

        System.out.println("\nShared Secret Key calculated by A (KA) = " + KA);
        System.out.println("Shared Secret Key calculated by B (KB) = " + KB);

        if (KA == KB) {
            System.out.println("\nShared Secret Key = " + KA);
            System.out.println("Key Exchange Successful!");
        } else {
            System.out.println("\nKey Exchange Failed!");
        }

        sc.close();
    }
}