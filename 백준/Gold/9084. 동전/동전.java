import java.io.*;
import java.util.*;
public class Main {

    static int t;
    public static void main(String[] args) throws IOException{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        while(t -- > 0){
            int nums = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] coins = new int[nums + 1];
            int[][] dp = new int[nums + 1][10001];
            for(int i = 1; i <= nums; i ++){
                coins[i] = Integer.parseInt(st.nextToken());
                dp[i][0] = 1;
            }

            for(int i = 1; i <= nums; i ++){
                for(int j = 1; j < 10001; j ++){
                    if(coins[i] > j){
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                        continue;
                    }
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j] + dp[i][j - coins[i]]);
                }
            }

            int target = Integer.parseInt(br.readLine());
            sb.append(dp[nums][target] + "\n");
        }

        System.out.println(sb.toString());
    }
}