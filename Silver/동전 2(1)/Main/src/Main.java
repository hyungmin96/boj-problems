import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");
        
        int n = Integer.parseInt(stz.nextToken());
        int k = Integer.parseInt(stz.nextToken());

        int[] dp = new int[k + 1];
        int[] coin = new int[n];
        Arrays.fill(dp, 10001);

        for(int i = 0; i < n; i ++)
            coin[i] = Integer.parseInt(br.readLine());

        System.out.println(solution(n, k, dp, coin));
    }

    public static int solution(int n, int k, int[] dp, int[] coin){
        dp[0] = 0;

        for(int i = 0; i < n; i ++){
            for(int j = coin[i]; j <= k; j ++){
                dp[j] = Math.min(dp[j], dp[j - coin[i]] + 1);
            }
        }

        return (dp[k] == 10001) ? - 1: dp[k];
    }
}
