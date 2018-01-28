
import java.util.Scanner;

public class Multiplicative_Cipher {

    static String txt = "abcdefghijklmnopqrstuvwxyz";

    static int gcd(int l, int h) {
        if (l == 0) {
            return h;
        }
        return gcd(h % l, l);
    }

    /*
        static int modInverse(int a, int m) {
            a = a % m;
            for (int x = 1; x < m; x++)
                if ((a * x) % m == 1)
                    return x;
            return 1;
        }
    */
    static int extended_gcd(int a, int m) {
        int t1 = 0;
        int t2 = 1;
        int r1 = m;
        int r2 = a;
        int r, t, q;
        while (r2 > 0) {
            q = r1 / r2;
            r = r1 - q * r2;
            r1 = r2;
            r2 = r;
            t = t1 - q * r2;
            t1 = t2;
            t2 = t;
            System.out.println(q + " " + r1 + " " + r2 + " " + r + " " + t1 + " " + t2 + " " + t);
        }

        if (r1 == 1) {
            return t1;
        }
        return -1;
    }

    /*
        static String plain_to_cipher(char plain[], int k) {
            int pos, n_pos;
            char cipher[] = new char[plain.length];
            for (int i = 0; i < plain.length; i++) {
                pos = txt.indexOf(plain[i]);
                n_pos = (pos * k) % 26;
                cipher[i] = Character.toUpperCase(txt.charAt(n_pos));
            }
            String out = new String(cipher);
            return out;
        }

        static String cipher_to_plain(char cipher[], int k) {
            int pos, n_pos;
            char plain[] = new char[cipher.length];
            for (int i = 0; i < plain.length; i++) {
                pos = txt.indexOf(Character.toLowerCase(cipher[i]));
                n_pos = (pos * k) % 26;
                plain[i] = txt.charAt(n_pos);
            }
            String out = new String(plain);
            return out;
        }

    */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the key: ");
        int k = in.nextInt();

        int inverse = extended_gcd(k, 26);
        System.out.println("Modulo Inverse: " + inverse);

        /*
        int cf = gcd(k, 26);

         if (cf != 1) {
            System.out.println("Key Not Valid!! Try other Key");
            System.exit(0);
        }
        String input, out;

        System.out.print("Enter the plain text: ");
        input = in.next();
        char plain[] = input.toCharArray();
        out = plain_to_cipher(plain, k);
        System.out.println("Cipher Code: " + out);



        System.out.print("Enter the Cipher Code: ");
        input = in.next();
        char cipher[] = input.toCharArray();
        out = cipher_to_plain(cipher, inverse);
        System.out.println("Plain text: " + out);
*/
    }
}
