import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int N, K, mod = 1000000000;
    int[][] dp;

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[K + 1][N + 1];
        for(int i = 1; i <= K; i ++)
            dp[i][1] = i;

        for(int i = 1; i <= N; i ++)
            dp[1][i] = 1;

        for(int i = 2; i <= K; i ++){
            for(int j = 2; j <= N; j ++){
                dp[i][j] = (dp[i - 1][j] % mod + dp[i][j - 1] % mod) % mod;
            }
        }

        System.out.println(dp[K][N]);
    }
}