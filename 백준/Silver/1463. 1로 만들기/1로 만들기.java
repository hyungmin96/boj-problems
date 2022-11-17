import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if(n < 2){
            System.out.println(0);
            return;
        }else if(n < 4){
            System.out.println(1);
            return;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;

        for(int i = 4; i <= n; i ++){
            int min = Integer.MAX_VALUE;
            if(i % 2 == 0){
                min = Math.min(min, dp[i / 2] + 1);
            }
            if(i % 3 == 0){
                min = Math.min(min, dp[i / 3] + 1);
            }
            min = Math.min(min, dp[i - 1] + 1);
            dp[i] = min;
        }
        System.out.println(dp[n]);
    }
}
