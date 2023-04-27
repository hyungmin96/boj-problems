import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();

        char cur = '\0';
        int red = 0, blue = 0;
        int rCnt = 0, bCnt = 0;
        for(int i = 0; i < n; i ++){
            char c = str.charAt(i);
            if(c == 'R') rCnt ++; else bCnt ++;
            if(i == 0 || c != cur){
                cur = c;
                if(c == 'R') red ++; else blue ++;
            }
        }

        int a1 = 987654321;
        if(red > blue){
            a1 = blue + 1;
        }else{
            if(red == blue){
                a1 = Math.min(red, blue) + 1;
            }else
                a1 = red + 1;
        }

        System.out.println(Math.min(a1, (red + blue)));
    }
}