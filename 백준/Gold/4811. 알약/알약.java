import java.util.*;

import javax.sql.rowset.spi.SyncResolver;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            int n = Integer.parseInt(br.readLine());
            if(n == 0) break;

            long[] dp = new long[31];
            dp[0] = 1;
            dp[1] = 1;

            if(n <= 1){
                sb.append("1\n");
                continue;
            }

            for(int i = 2; i <= n; i ++){
                for(int j = 1; j <= i; j ++){
                    dp[i] += dp[i - j] * dp[j - 1];
                }
            }

            sb.append(dp[n] + "\n");
        }
        System.out.println(sb.toString());
    }
}