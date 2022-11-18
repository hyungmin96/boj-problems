import java.io.*;
import java.util.*;

public class Main {

    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coin = new int[n];
        for(int i = 0; i < n; i ++){
            coin[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(coin);
        int[][] dp = new int[n + 1][k + 1];
        for(int i = 1; i <= n; i ++) dp[i][0] = 1;

        for(int i = 1; i <= n; i ++){
            for(int j = 1; j <= k; j ++){
                dp[i][j] = (j >= coin[i - 1] ? dp[i][j - coin[i - 1]] : 0) + dp[i - 1][j] ;
            }
        }
        System.out.println(dp[n][k]);
    }
}