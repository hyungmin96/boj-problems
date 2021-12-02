import java.io.*;
import java.util.StringTokenizer;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(stz.nextToken());
        int k = Integer.parseInt(stz.nextToken());

        int[] coin = new int[n];
        int[] dp = new int[k + 1];

        for(int i = 0; i < n; i ++){
            coin[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(solution(n, k, coin, dp));
    }

    public static int solution(int n, int k, int[] coin, int[] dp){
        dp[0] = 1;
        for(int i = 0; i < n; i ++){
            for(int j = coin[i]; j <= k; j ++){
                dp[j] = dp[j] + dp[j - coin[i]];
            }            
        }
        return dp[k];
    }
}
