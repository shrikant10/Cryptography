package Cryptography;
import java.util.*;

public class Rabin_Cryptosystem {

    static double pow(int a, int p, int m){
        double res = a;
        p--;
        while(p-->0){
            res=(res*a)%m;
        }
        return res;
    }

    static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++)
            if ((a * x) % m == 1)
                return x;
        return 1;
    }

    static double CRT(double a1, double a2, int m1, int m2){
        int M = m1*m2;
        int M1 = M/m1;
        int M2 = M/m2;
        int m1_ = modInverse(M1,m1);    //inverse
        int m2_ = modInverse(M2,m2);    //inverse
        double res = ((a1*M1*m1_)%M + (a2*M2*m2_)%M)%M;
        //System.out.println( "M: "+ M + " M1: "+ M1 + " M2: " + M2 +" m1_inv "+ m1_ +" m2_inv "+m2_);
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Key Generation Phase ");
        System.out.print("Enter p & q (4k+3):");
        int p = in.nextInt();
        int q = in.nextInt();
        int n = p*q;
        System.out.println("Public Key: n = p * q = " + n);
        System.out.println("Private Key: p = " + p + " q = " + q);
        System.out.println("Encryption :");
        System.out.print("Enter plain text: ");
        int P = in.nextInt();
        int c = (P*P)%n;
        System.out.println("C :"+c);

        System.out.println("Decryption :");
        double a1 = pow(c,(p+1)/4, p);
        double a2 = p - a1;
        double b1 = pow(c,(q+1)/4, q);
        double b2 = q - b1;

        System.out.println("a1: "+a1+" a2: "+a2+" b1: "+b1+" b2: "+b2);
        double p1 = CRT(a1,b1,p,q);
        double p2 = CRT(a1,b2,p,q);
        double p3 = CRT(a2,b1,p,q);
        double p4 = CRT(a2,b2,p,q);

        System.out.println("Plain text: ");
        System.out.println("p1 :" + p1);
        System.out.println("p2 :" + p2);
        System.out.println("p3 :" + p3);
        System.out.println("p4 :" + p4);

        // p = 23 q = 7 P = 24
    }
}
