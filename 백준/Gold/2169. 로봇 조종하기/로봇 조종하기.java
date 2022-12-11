import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n + 1][m + 1];

        for(int i = 1; i <= n; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 1; j <= m; j ++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 0 : 위, 1 : 왼쪽, 2 : 오른쪽
        int[][][] dp = new int[n + 1][m + 1][3];
        for(int i = 0; i <= n; i ++) 
            for(int j = 0; j <= m; j ++)
                Arrays.fill(dp[i][j], -987654321);

        dp[1][1][0] = dp[1][1][1] = dp[1][1][2] = map[1][1];
        for(int i = 1; i <= n; i ++){
            for(int j = 1; j <= m; j ++){
                if(i > 1)
                    dp[i][j][1] = Math.max(dp[i - 1][j][0], Math.max(dp[i - 1][j][1], dp[i - 1][j][2])) + map[i][j];

                if(j > 1)
                    dp[i][j][0] = Math.max(dp[i][j - 1][0], dp[i][j - 1][1]) + map[i][j];
            }

            for(int j = m - 1; j >= 1; j --){
                dp[i][j][2] = Math.max(dp[i][j + 1][1], dp[i][j + 1][2]) + map[i][j];
            }
        }
        System.out.println(Math.max(dp[n][m][0], dp[n][m][1]));
    }
}