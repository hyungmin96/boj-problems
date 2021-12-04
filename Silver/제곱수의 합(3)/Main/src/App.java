import java.io.*;
public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        System.out.println(solution(n, dp));
    }

    public static int solution(int n, int[] dp){
        int answer = 0;

        for(int i = 1; i <= n; i ++){
            dp[i] = i;
            for(int j = 1; j * j <= i; j ++){
                if(dp[i] > dp[i - j * j] + 1){
                    dp[i] = dp[i - j * j] + 1;
                }
            }
        }

        return answer;
    }
}
