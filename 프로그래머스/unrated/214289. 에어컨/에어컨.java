import java.util.*;
class Solution {
    int INF = 987654321;
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int answer = INF;
        int[][] dp = new int[onboard.length][55];
        for(int i = 0; i < onboard.length; i ++)
            Arrays.fill(dp[i], INF);

        temperature += 10;
        t1 += 10;
        t2 += 10;

        dp[0][temperature] = 0;
        for(int i = 0; i < onboard.length - 1; i ++){
            for(int j = 0; j < 52; j ++){
                if(dp[i][j] == INF) continue;
                if(onboard[i] == 1 && (j < t1 || j > t2)) continue;
                // 에어컨을 사용하여 온도 올리기
                dp[i + 1][j + 1] = Math.min(dp[i + 1][j + 1], dp[i][j] + a); 
                // 에어컨을 사용하여 온도 낮추기
                if(j - 1 >= 0)
                    dp[i + 1][j - 1] = Math.min(dp[i + 1][j - 1], dp[i][j] + a);
                // 에어컨을 사용하여 온도 유지하기
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + b); 

                // 에어컨 끄기
                int next_tmp = j;
                if(j < temperature){
                    next_tmp ++;
                }

                if(j > temperature){
                    next_tmp --;
                }

                if(next_tmp >= 0)
                    dp[i + 1][next_tmp] = Math.min(dp[i + 1][next_tmp], dp[i][j]);
            }
        }

        for(int i = 0; i < 52; i ++){
            if(onboard[onboard.length - 1] == 1 && (i < t1 || i > t2)) continue;
            answer = Math.min(answer, dp[onboard.length - 1][i]);
        }
        return answer;
    }
}