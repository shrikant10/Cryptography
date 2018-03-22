package Cryptography;

import java.util.Arrays;

public class DES {

    static int key_permutation[] = {
            57, 49, 41, 33, 25, 17, 9,
            1, 58, 50, 42, 34, 26, 18,
            10, 2, 59, 51, 43, 35, 27,
            19, 11, 3, 60, 52, 44, 36,
            63, 55, 47, 39, 31, 23, 15,
            7, 62, 54, 46, 38, 30, 22,
            14, 6, 61, 53, 45, 37, 29,
            21, 13, 5, 28, 20, 12, 4 };


    static int commpression_p[] = {
            14, 17, 11, 24, 1, 5,
            3, 28, 15, 6, 21, 10,
            23, 19, 12,  4, 26, 8,
            16, 7, 27, 20, 13, 2,
            41, 52, 31, 37, 47, 55,
            30, 40, 51, 45, 33, 48,
            44, 49, 39, 56, 34, 53,
            46, 42, 50, 36, 29, 32 };


    static int[][] Create_Box(int in[]) {
        int input[][] = new int[8][4];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                input[i][j] = in[4 * i + j];
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(input[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("After P BOX : ");

        int pbox[][] = new int[8][6];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 6; j++) {
                if (j > 0 && j < 5) {
                    pbox[i][j] = input[i][j - 1];
                } else if (j == 0) {
                    int prev = ((i - 1) % 8 + 8) % 8;
                    pbox[i][j] = input[prev][3];
                } else {
                    int next = (i + 1) % 8;
                    pbox[i][j] = input[next][0];
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(pbox[i][j] + " ");
            }
            System.out.println();
        }

        return pbox;
    }

    static int[] init(String s) {
        int in[] = new int[64];

        for (int i = 0; i < 16; i++) {
            char c = s.charAt(i);
            String a = Integer.toBinaryString((int) c);
            for (int j = 0; j < 4; j++) {
                in[4 * i + j] = Integer.parseInt("" + a.charAt(j + 2));
            }
        }

        System.out.println("64 bit Number");
        for (int i = 0; i < 64; i++) {
            System.out.print(in[i]);
            if (i % 4 == 3) {
                System.out.print(" ");
            }
        }
        System.out.println();
        return in;

    }

    static int round_key[][] = new int[16][48];

    static void generate_keys(int k[]){
        System.out.println("Permutaion 64bit to 56 bit");
        int n_k[]= new int[56];
        for(int i =0; i<56;i++){
            n_k[i]=k[key_permutation[i]];
        }
        print(n_k, 7);
        System.out.println();
        int l[] = new int[28];
        int r[] = new int[28];
        System.arraycopy(n_k, 0, l, 0, 28);
        System.arraycopy(n_k, 28, r, 0, 28);

        int key[] = new int[48];

        for(int i=0; i<16;i++){
            int shift=2;
            if(i<2 || i==8 ||i==15){
                shift=1;
            }
            System.out.println("After Shift : ");
            shrift_array(l,shift);
            shrift_array(r,shift);
            for(int j=0;j<48;j++){
                int pos = commpression_p[j]-1;
                if(pos>=28){
                    round_key[i][j]=r[pos-28];
                }else{
                    round_key[i][j]=l[pos];
                }
            }
            System.out.println("Key "+(i+1)+" :");
            print(round_key[i],7);
        }
    }

    public static void main(String[] args) {

        String key = "133457799BBCDFF1";
        System.out.println("Key: " + key);

        int k[] = init(key);

        generate_keys(k);

        String plain_txt = "0123456789ABCDEF";
        System.out.println("Plain Text: " + plain_txt);

        int in[] = init(plain_txt);
        int in_L[] = new int[32];
        int in_R[] = new int[32];

        System.arraycopy(in, 0, in_L, 0, 32);
        System.arraycopy(in, 32, in_R, 0, 32);

        System.out.println("For left part: ");
        int p_l[][] = Create_Box(in_L);

        System.out.println("For right part: ");
        int p_r[][] = Create_Box(in_R);

        System.out.println("XOR with key : ");

        for(int i=0; i<16;i++){
            System.out.println("After Round " +(i+1)+": ");
            int aff[] = new int[48];
            for(int j=0;j<48;j++){
                aff[j] = round_key[i][j]^p_r[j/6][j%6];
            }
            print(aff, 6);
        }
    }

    static void shrift_array(int a[], int shift){
        for(int i=0;i<a.length;i++){
            a[i]=a[(i+shift)%a.length];
        }
        print(a, 7);
    }

    static void print(int k[], int s){
        for(int i=0;i<k.length;i++){
            System.out.print(k[i]);
            if(i%s==s-1){
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}
