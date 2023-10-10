import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException{
        Solution sol = new Solution();
        sol.solution();
    }
}
    
class Solution{

    int N, M;
    boolean[] holiday;

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        holiday = new boolean[N + 8];

        if(M > 0){
            st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < M; i ++){
                holiday[Integer.parseInt(st.nextToken())] = true;
            }
        }

        bottomUp();
    }

    public void bottomUp(){
        int[][] dp = new int[44][N + 7];

        for(int i = 0; i < 44; i ++)
            Arrays.fill(dp[i], 987654321);

        dp[0][0] = 0;
        for(int i = 0; i <= N; i ++){
            for(int j = 0; j <= 40; j ++){
                if(dp[j][i] == 987654321) continue;
                if(holiday[i + 1]){
                    dp[j][i + 1] = Math.min(dp[j][i + 1], dp[j][i]);
                }

                if(j >= 3){
                    dp[j - 3][i + 1] = Math.min(dp[j - 3][i + 1], dp[j][i]);
                }

                dp[j][i + 1] = Math.min(dp[j][i + 1], dp[j][i] + 10000);

                for(int k = 1; k < 4; k ++){
                    dp[j + 1][i + k] = Math.min(dp[j + 1][i + k], dp[j][i] + 25000);
                }

                for(int k = 1; k < 6; k ++){
                    dp[j + 2][i + k] = Math.min(dp[j + 2][i + k], dp[j][i] + 37000);
                }
            }
        }
        
        int answer = 987654321;
        for(int i = 0; i <= 40; i ++){
            answer = Math.min(answer, dp[i][N]);
        }

        System.out.println(answer);
    }
}