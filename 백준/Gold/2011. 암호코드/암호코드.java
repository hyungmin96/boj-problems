import java.util.*;
import java.io.*;

public class Main{

    static int MOD = 1000000;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        if(str.contains("00")){
            System.out.println(0);
            return;
        }

        long[] dp = new long[str.length() + 1];
        dp[0] = 1;
        if(str.length() == 1 && str.charAt(0) - '0' == 0){
            System.out.println(0);
            return;
        }
        
        for(int i = 1; i <= str.length(); i ++){
            int now = str.charAt(i - 1) - '0';
            if(now > 0 && now < 10){
                dp[i] = (dp[i] + dp[i - 1]) % MOD;
            }
            if(i == 1) continue;

            int pre = str.charAt(i - 2) - '0';
            if(pre == 0) continue;

            int v = pre * 10 + now;
            if(v >= 10 && v <= 26){
                dp[i] = (dp[i] + dp[i - 2]) % MOD;
            }
        }

        System.out.println((dp[str.length()]) % MOD);
    }
}