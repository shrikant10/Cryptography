import java.util.*;

public class Keyless_Cipher {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        int col = 4;
        int k=0;
        char a[] = new char[s.length()];
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!=' '){
                a[k++]=s.charAt(i);;
            }
        }
        int row = (int)Math.ceil((double) k/col);

        for(int i=0;i<col;i++){
            for(int j=0;j<row;j++){
                System.out.print(a[col*j+i]);
            }
        }
        System.out.println();
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                System.out.print(a[col*i+j]);
            }
        }


        System.out.println();
    }
}