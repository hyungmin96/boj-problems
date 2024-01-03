import java.util.*;
class Solution {
    int[][] dp = new int[53][1001];
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int answer = 987654321;
        for(int i = 0; i < 53; i ++){
            Arrays.fill(dp[i], 987654321);
        }
        
        temperature += 11;
        t1 += 11;
        t2 += 11;
        
        dp[temperature][0] = 0;    
        for(int i = 0; i < onboard.length - 1; i ++){
            for(int j = 1; j < 52; j ++){
                if(dp[j][i] == 987654321) continue;
                if(onboard[i] == 1 && (j < t1 || j > t2)) continue;
                
                dp[j + 1][i + 1] = Math.min(dp[j + 1][i + 1], dp[j][i] + a);
                dp[j - 1][i + 1] = Math.min(dp[j - 1][i + 1], dp[j][i] + a);
                dp[j][i + 1] = Math.min(dp[j][i + 1], dp[j][i] + b);
                
                int next_tmp = j;
                if(j < temperature){
                    next_tmp ++;
                }

                if(j > temperature){
                    next_tmp --;
                }

                dp[next_tmp][i + 1] = Math.min(dp[next_tmp][i + 1], dp[j][i]);
            }
        }
        
        for(int i = onboard.length - 1; i >= 0; i --){
            if(onboard[i] == 1){
                for(int j = 0; j < 52; j ++){
                    if(j >= t1 && j <= t2)
                        answer = Math.min(answer, dp[j][i]);
                }
                break;
            }
        }
        return answer;
    }
}