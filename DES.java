
import java.util.Random;

public class DES {

    static void Create_Box(int in[]) {
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

    static void key_create(String s){

    }
    public static void main(String[] args) {

        String plain_txt = "0123456789ABCDEF";
        System.out.println("Plain Text: " + plain_txt);

        int in[] = init(plain_txt);
        int in_L[] = new int[32];
        int in_R[] = new int[32];

        System.arraycopy(in, 0, in_L, 0, 32);
        System.arraycopy(in, 32, in_R, 0, 32);

        System.out.println("For left part: ");
        Create_Box(in_L);

        System.out.println("For right part: ");
        Create_Box(in_R);

        //System.out.println("After S BOX : ");

        String key = "133457799BBCDFF1";
        System.out.println("Key: " + key);

        int k[] = init(key);

        int permutaion[][] = {{57, 49, 41, 33, 25, 17, 9},
                {1, 58, 50, 42, 34, 26, 18},
                {10, 2, 59, 51, 43, 35, 27},
                {19, 11, 3, 60, 52, 44, 36},
                {63, 55, 47, 39, 31, 23, 15},
                {7, 62, 54, 46, 38, 30, 22},
                {14, 6, 61, 53, 45, 37, 29},
                {21, 13, 5, 28, 20, 12, 4}};


    }

}
