import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        System.out.println(solution(str));
    }

    public static int solution(String str) {
        int left = 0;
        int right = 0;
        int total = 0;

        for(int i = 0; i < str.length(); i ++){
            if(str.charAt(i) == '('){
                left ++;
                total ++;
            }else{
                right ++;
                total --;
            }
            if(total < 0) return right;
            if(total <= 1)
                left = 0;
        }
        if(total > 0) 
            return left;
        else
            return 0;
    }
}
