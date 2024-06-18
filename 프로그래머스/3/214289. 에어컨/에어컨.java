import java.util.*;
class Solution {
    int[][] dp = new int[51][1001]; // 온도|시간
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int answer = 987654321;
        
        temperature += 10;
        t1 += 10;
        t2 += 10;
        for(int i = 0; i < 51; i ++){
            Arrays.fill(dp[i], 987654321);
        }
        
        dp[temperature][0] = 0;
        for(int i = 0; i < onboard.length - 1; i ++){
            for(int j = 0; j < 51; j ++){
                if(onboard[i] == 1 && (j < t1 || j > t2)){
                    continue;
                }
                // 온도 1도 낮추기
                if(j - 1 >= 0){
                    dp[j - 1][i + 1] = Math.min(dp[j - 1][i + 1], dp[j][i] + a);
                }
                // 온도 1도 올리기
                if(j + 1 < 51){
                    dp[j + 1][i + 1] = Math.min(dp[j + 1][i + 1], dp[j][i] + a);
                }
                // 온도 유지하기
                dp[j][i + 1] = Math.min(dp[j][i + 1], dp[j][i] + b);
                
                // 에어컨 종료한 경우
                int next = j;
                if(next > temperature){
                    next --;
                }else if(next < temperature){
                    next ++;
                }
                dp[next][i + 1] = Math.min(dp[next][i + 1], dp[j][i]);
            }
        }
        
        for(int j = onboard.length - 1; j >= 0; j --){
            if(onboard[j] == 1){
                for(int i = t1; i <= t2; i ++){
                    answer = Math.min(answer, dp[i][j]);
                }      
                break;
            }
        }
        
        return answer;
    }
}

// 실내온도와 실외온도는 (0분) 일때 동일함
// 에어컨을 켜서 희망온도에 가까워지게 1도 상승 하강 유지 가능
// 에어컨 종료 시 실내온도가 실외온도와 가까워지는 쪽으로 1도 상승 또는 하강
