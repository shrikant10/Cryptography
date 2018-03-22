package Cryptography;
import java.util.Scanner;

public class RSA {

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

    static double pow(int a, int p, int m){
        double res = a;
        p--;
        while(p-->0){
            res=(res*a)%m;
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter two prime num:");

        int p = in.nextInt();
        int q = in.nextInt();
        int n = p*q;
        int phi = (p-1)*(q-1);
        int e = 2;

        while(e<phi){
            if(gcd(e,phi)==1){
                break;
            }
            e++;
        }

        int d = modInverse(e,phi);
        System.out.println("d : "+d+" e : "+e);
        /*int k = 2;
        double d = ((1+(double)phi*k)/(double)e);
        System.out.println("d : "+d);
        */
        System.out.print("Enter message to be encrypted: ");
        int m = in.nextInt();
        double c = pow(m,e,n);
        System.out.println("Encrypted Message: " + c);
        double dec = pow((int)c,d,n);
        System.out.println("Decrypted Message: " + dec);
    }

}
