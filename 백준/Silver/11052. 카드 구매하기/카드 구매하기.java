import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[][] dp = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i ++){
            int num = Integer.parseInt(st.nextToken());
            for(int j = 1; j <= n; j ++){
                if(j - i < 0){
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }else{
                    dp[i][j] = Math.max(dp[i][j - i] + dp[i][i], Math.max(dp[i - 1][j], dp[i][j - i] + num));
                }
            }
        }

        System.out.println(dp[n][n]);
    }
}
