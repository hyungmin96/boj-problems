import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[1000001];

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        solution(n, dp);
        System.out.println(dp[n]);
    }

    public static int solution(int n, int[] dp){
        if(n == 0) return dp[0] = 0;
        if(n <= 2) return dp[n] = 1;
        else{
            if(dp[n] != 0)
                return dp[n];
            else{
                return dp[n] = (solution(n - 1, dp) + solution(n - 2, dp)) % 1000000007 ;
            }
        }
    }
}
