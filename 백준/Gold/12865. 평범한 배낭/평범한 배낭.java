import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n + 1][k + 1];
        int[][] object = new int[n + 1][2];
        for(int i = 1; i <= n; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            object[i][0] = w;
            object[i][1] = v;
        }

        for(int i = 1; i <= n; i ++){
            for(int j = 1; j <= k; j ++){
                if(object[i][0] > j) 
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - object[i][0]] + object[i][1]);
            }
        }
        System.out.println(dp[n][k]);
    }
}
