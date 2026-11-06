import java.util.Scanner;
import java.util.Random;

class OneTimePad {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter plain text: ");
        String plaintext = sc.nextLine().toUpperCase();

        String key = new OneTimePad().generateKey(plaintext.length());
        
        String ct = new OneTimePad().encrypt(plaintext,key);
        String pt = new OneTimePad().decrypt(ct,key);

        System.out.println("Key        : " + key);
        System.out.println("Ciphertext : " + ct);
        System.out.println("Plaintext  : " + pt);

        sc.close();
    }


    private String generateKey(int len){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < len; i++){
            sb.append((char) (random.nextInt(26) + 'A'));
        }
        return sb.toString();
    }

    private String encrypt(String pt, String key){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < pt.length(); i++){
            int ptVal = pt.charAt(i) - 'A';
            int keyVal = key.charAt(i) - 'A';
            int ctVal = (ptVal + keyVal) % 26;
            sb.append((char) (ctVal + 'A'));
        }
        return sb.toString();
    }

    private String decrypt(String ct,String key){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < ct.length(); i++){
            int ctVal = ct.charAt(i) - 'A';
            int keyVal = key.charAt(i) - 'A';
            int ptVal = (ctVal - keyVal) % 26;
            if(ptVal < 0){
                ptVal += 26;
            }
            sb.append((char) (ptVal + 'A'));
        }
        return sb.toString();
    }
}