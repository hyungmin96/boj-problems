import java.io.*;
public class Main {

    private static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        
        System.out.println(solution(n, dp));
    }

    public static int solution(int n, int[] dp){
        for(int i = 2; i < dp.length; i ++){
            dp[i] = dp[i - 1] + 1;
            if(i % 3 == 0){
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            }
            if(i % 2 == 0){
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            }
        }
        
        return dp[dp.length - 1];
    }
}
