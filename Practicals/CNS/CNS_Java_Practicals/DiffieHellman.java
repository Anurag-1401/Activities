import java.math.BigInteger;
public class DiffieHellman {
    public static void main(String[] args) {
        BigInteger p = BigInteger.valueOf(23), g = BigInteger.valueOf(5);
        BigInteger a = BigInteger.valueOf(6), b = BigInteger.valueOf(15);
        BigInteger A = g.modPow(a, p), B = g.modPow(b, p);
        BigInteger secretA = B.modPow(a, p), secretB = A.modPow(b, p);
        System.out.println("Alice's public key: " + A);
        System.out.println("Bob's public key: " + B);
        System.out.println("Alice's shared secret: " + secretA);
        System.out.println("Bob's shared secret: " + secretB);
    }
}
