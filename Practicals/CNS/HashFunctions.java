import java.util.Scanner;

class HashFunctions {

    static int hash1(String s) {
        int h = 0;
        for (char c : s.toCharArray())
            h = h + c;
        return h;
    }

    static int hash2(String s) {
        int h = 1;
        for (char c : s.toCharArray())
            h = (h * 31 + c) % 100000;
        return h;
    }

    static int hash3(String s) {
        int h = 7;
        for (char c : s.toCharArray())
            h = (h * 17 + c) % 100000;
        return h;
    }

    public static void main(String[] args) {
    
        Scanner sc = new Scanner(System.in);
    
        System.out.print("Enter message: ");
        String message = sc.nextLine();
    
        System.out.println("Hash 1: " + hash1(message));
        System.out.println("Hash 2: " + hash2(message));
        System.out.println("Hash 3: " + hash3(message));
    
        sc.close();
    }
}