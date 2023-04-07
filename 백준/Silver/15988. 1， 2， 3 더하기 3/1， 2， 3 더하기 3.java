import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static final int MOD = 1_000_000_009;
    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        dp = new long[1_000_001];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        while(T-- > 0){
            int n = Integer.parseInt(br.readLine());
            sb.append(recur(n)).append("\n");
        }
        System.out.println(sb);
    }
    public static long recur(int n){
        if(dp[n] == 0){
            dp[n] = recur(n - 1) + recur(n - 2) + recur(n - 3);
        }
        return dp[n] % MOD;
    }
}