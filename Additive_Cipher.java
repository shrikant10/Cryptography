
// Assuming  that plaintext consists of lower cases letter and the ciphertext consists of upper case letter.

import java.util.Scanner;

public class Additive_Cipher {

    static String txt = "abcdefghijklmnopqrstuvwxyz";

    static String plain_to_cipher(char plain[], int k){
        int pos, n_pos;
        char cipher[] = new char[plain.length];
        for(int i =0; i<plain.length; i++){
            pos = txt.indexOf(plain[i]);
            n_pos = (pos+k)%26;
            cipher[i] = Character.toUpperCase(txt.charAt(n_pos));
        }
        String out = new String(cipher);
        return out;
    }

    static String cipher_to_plain(char cipher[], int k){
        int pos, n_pos;
        char plain[] = new char[cipher.length];
        for(int i =0; i<plain.length; i++){
            pos = txt.indexOf(Character.toLowerCase(cipher[i]));
            n_pos = (pos-k)%26;
            plain[i] = txt.charAt(n_pos);
        }
        String out = new String(plain);
        return out;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the key: ");
        int k = in.nextInt();

        String input,out;

        System.out.print("Enter the plain text: ");
        input = in.next();
        char plain[] = input.toCharArray();
        out = plain_to_cipher(plain, k);
        System.out.println("Cipher Code: "+out);


        System.out.print("Enter the Cipher Code: ");
        input = in.next();
        char cipher[] = input.toCharArray();
        out = cipher_to_plain(cipher, k);
        System.out.println("Plain text: "+out);
    }
}
