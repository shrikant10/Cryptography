
import java.util.Scanner;

public class Affine_Cipher {

    static String txt = "abcdefghijklmnopqrstuvwxyz";

    static int gcd(int l, int h) {
        if (l == 0) {
            return h;
        }
        return gcd(h % l, l);
    }

    static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++)
            if ((a * x) % m == 1)
                return x;
        return 1;
    }

    static int extended_gcd(int a, int m) {
        int t1 = 0;
        int t2 = 1;
        int r1 = m;
        int r2 = a;
        int r, t;
        while (r2 > 0) {
            r = r1 % r2;
            r1 = r2;
            r2 = r;
            t = t1 - (r1 / r2) * r2;
            t1 = t2;
            t2 = t;
        }

        if(r1==1){
            return t1;
        }
        return -1;
    }

    static String plain_to_cipher(char plain[], int k1, int k2) {
        int pos, n_pos;
        char cipher[] = new char[plain.length];
        for (int i = 0; i < plain.length; i++) {
            pos = txt.indexOf(plain[i]);
            n_pos = (pos * k2 + k1) % 26;
            cipher[i] = Character.toUpperCase(txt.charAt(n_pos));
        }
        String out = new String(cipher);
        return out;
    }

    static String cipher_to_plain(char cipher[], int k1, int k2) {
        int pos, n_pos;
        char plain[] = new char[cipher.length];
        for (int i = 0; i < plain.length; i++) {
            pos = txt.indexOf(Character.toLowerCase(cipher[i]));
            n_pos = ((pos - k1) * k2) % 26;
            plain[i] = txt.charAt(n_pos);
        }
        String out = new String(plain);
        return out;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the addi key: ");
        int k1 = in.nextInt();
        System.out.print("Enter the multi key: ");
        int k2 = in.nextInt();

        int cf = gcd(k2, 26);
        if (cf != 1) {
            System.out.println("Key Not Valid!! Try other Key");
            System.exit(0);
        }
        String input, out;

        System.out.print("Enter the plain text: ");
        input = in.next();
        char plain[] = input.toCharArray();
        out = plain_to_cipher(plain, k1, k2);
        System.out.println("Cipher Code: " + out);


        int inverse = extended_gcd(k2, 26);
        System.out.println("Modulo Inverse: " + inverse);

        System.out.print("Enter the Cipher Code: ");
        input = in.next();
        char cipher[] = input.toCharArray();
        out = cipher_to_plain(cipher, k1, inverse);
        System.out.println("Plain text: " + out);
    }
}
