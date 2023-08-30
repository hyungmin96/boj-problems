import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int N;
    int[][] dp, val;

    public int solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N][3];
        val = new int[N][3];

        for(int i = 0; i < N; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int red = Integer.parseInt(st.nextToken());
            int blue = Integer.parseInt(st.nextToken());
            int green = Integer.parseInt(st.nextToken());

            val[i][0] = red;
            val[i][1] = blue;
            val[i][2] = green;
        }

        int INF = 987654321;
        int answer = INF;
        for(int k = 0; k < 3; k ++){
            for(int i = 0; i < N; i ++)
                Arrays.fill(dp[i], INF);
            dp[0][k] = val[0][k];

            int tmp = val[N - 1][k];
            val[N - 1][k] = INF;
            for(int i = 1; i < N; i ++){
                for(int j = 0; j < 3; j ++){
                    for(int l = 0; l < 3; l ++){
                        if(j == l) continue;
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][l] + val[i][j]);
                    }
                }
            }
            for(int i = 0; i < 3; i ++){
                answer = Math.min(answer, dp[N - 1][i]);
            }
            val[N - 1][k] = tmp;
        }
        System.out.println(answer);
        return 0;
    }
}