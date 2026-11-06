class Q1_Encryption {
    public static void main(String[] args) {

        String plain = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String cipher = "QWERTYUIOPASDFGHJKLZXCVBNM";

        String plaintext = "COMPUTER";
        StringBuilder encrypted = new StringBuilder();

        for (char ch : plaintext.toCharArray()) {
            int index = plain.indexOf(ch);
            encrypted.append(cipher.charAt(index));
        }

        System.out.println("Plaintext  : " + plaintext);
        System.out.println("Ciphertext : " + encrypted);
    }
}

class Q2_Decryption {
    public static void main(String[] args) {

        String plain = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String cipher = "QWERTYUIOPASDFGHJKLZXCVBNM";

        String ciphertext = "EGDHXZTK";
        StringBuilder decrypted = new StringBuilder();

        for (char ch : ciphertext.toCharArray()) {
            int index = cipher.indexOf(ch);
            decrypted.append(plain.charAt(index));
        }

        System.out.println("Ciphertext : " + ciphertext);
        System.out.println("Plaintext  : " + decrypted);
    }
}

class Q3_DES_KeyShift {
    public static void main(String[] args) {

        String C0 = "1111000011001100101010101111";
        String D0 = "0101010101100110011110001111";

        String C1 = C0.substring(1) + C0.charAt(0);
        String D1 = D0.substring(1) + D0.charAt(0);

        System.out.println("C0 : " + C0);
        System.out.println("D0 : " + D0);

        System.out.println("After 1-bit Left Circular Shift");

        System.out.println("C1 : " + C1);
        System.out.println("D1 : " + D1);
    }
}

class Q4_LSB_Steganography {
    public static void main(String[] args) {

        String[] pixels = {
            "10110110",
            "11001101",
            "11100010",
            "10010111"
        };

        String message = "HI";

        StringBuilder binaryMessage = new StringBuilder();

        for (char ch : message.toCharArray()) {
            String binary = String.format("%8s",
                    Integer.toBinaryString(ch)).replace(' ', '0');

            binaryMessage.append(binary);
        }

        for (int i = 0; i < 4; i++) {
            char messageBit = binaryMessage.charAt(i);

            String pixel = pixels[i];

            pixels[i] = pixel.substring(0, 7) + messageBit;
        }

        System.out.println("Original Pixels");

        System.out.println("10110110");
        System.out.println("11001101");
        System.out.println("11100010");
        System.out.println("10010111");

        System.out.println();

        System.out.println("Modified Pixels");

        for (String pixel : pixels) {
            System.out.println(pixel);
        }
    }
}