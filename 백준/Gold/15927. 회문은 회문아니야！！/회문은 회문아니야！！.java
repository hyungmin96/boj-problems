import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        boolean isEquals = true;
        char c = str.charAt(0);
        for(int i = 0; i < str.length(); i ++){
            if(c != str.charAt(i)){
                isEquals = false;
                break;
            }
        }

        if(isEquals){
            System.out.println(-1);
            return;
        }

        boolean isPelindrome = true;
        for(int i = 0; i <= str.length() / 2; i ++){
            if(str.charAt(i) != str.charAt(str.length() - i - 1)){
                isPelindrome = false;
                break;
            }
        }

        if(isPelindrome){
            System.out.println(str.length() - 1);
        }else{
            System.out.println(str.length());
        }
    }
}