import java.util.*;
class Solution {
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        temperature += 11;
        t1 += 11;
        t2 += 11;
        
        int[][] dp = new int[53][1001];
        for(int i = 0; i < 53; i ++){
            Arrays.fill(dp[i], 987654321);
        }
        dp[temperature][0] = 0;
        
        
        // 에어컨이 켜졌을 경우 실내온도는 희망온도와 가까운쪽으로 온도가 바뀜
        // 실내온도와 희망온도가 같을 때 에어컨을 켜도 실내온도는 바뀌지 않음
        // 에어컨이 꺼졌을 경우 실내온도는 실외온도와 가까워지는쪽으로 온도가 바뀜
        for(int i = 0; i < onboard.length; i ++){
            for(int j = 1; j < 52; j ++){
                if(dp[j][i] != 987654321){
                    // i 시간에 탑승객이 있는 경우
                    if(onboard[i] == 1 && (j < t1 || j > t2)){
                        continue;
                    }
                    
                    // 에어컨을 킨 경우
                    dp[j + 1][i + 1] = Math.min(dp[j + 1][i + 1], dp[j][i] + a);
                    dp[j - 1][i + 1] = Math.min(dp[j - 1][i + 1], dp[j][i] + a);
                    dp[j][i + 1] = Math.min(dp[j][i + 1], dp[j][i] + b);
                    
                    int next_tmp = j;
                    if(temperature > j){
                        next_tmp ++;
                    }else if(temperature < j){
                        next_tmp --;
                    }
                    
                    dp[next_tmp][i + 1] = Math.min(dp[next_tmp][i + 1], dp[j][i]);    
                }
            }
        }
        
        int min = 987654321;
        for(int i = onboard.length - 1; i >= 0; i --){
            if(onboard[i] == 1){
                for(int j = t1; j <= t2; j ++){
                    min = Math.min(dp[j][i], min);
                }   
                break;
            }
        }
        
        return min;
    }
}