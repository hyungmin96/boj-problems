import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] dp = new long[n + 1];

        System.out.println(solution(n, dp));
    }

    public static long solution(int n, long[] dp){
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2; i <= n; i ++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
