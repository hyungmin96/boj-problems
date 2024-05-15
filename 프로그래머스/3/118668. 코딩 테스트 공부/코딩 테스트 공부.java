import java.io.*;
import java.util.*;
class Solution {
    int[][] dp = new int[301][301]; // 알고력, 코딩력
    
    public int solution(int alp, int cop, int[][] problems) {
        int answer = 987654321;
        for(int i = 0; i < dp.length; i ++){
            Arrays.fill(dp[i], 987654321);
        }
        dp[alp][cop] = 0;
        
        int max_alp = alp;
        int max_cop = cop;
        for(int[] p : problems){
            max_alp = Math.max(max_alp, p[0]);
            max_cop = Math.max(max_cop, p[1]);
        }
        
        for(int i = alp; i <= 150; i ++){
            for(int j = cop; j <= 150; j ++){
                for(int k = 0; k < problems.length; k ++){
                    int[] p = problems[k];
                    // 현재 문제를 풀 수 없어서 기다려야하는 경우.
                    dp[i][j + 1] = Math.min(dp[i][j] + 1, dp[i][j + 1]);
                    dp[i + 1][j] = Math.min(dp[i][j] + 1, dp[i + 1][j]);
                    
                    if(i >= p[0] && j >= p[1]){
                        int next_alp = Math.min(i + p[2], max_alp);
                        int next_cop = Math.min(j + p[3], max_cop);
                        dp[next_alp][next_cop] = Math.min(dp[next_alp][next_cop], dp[i][j] + p[4]);
                    }
                }
            }
        }
        
        return dp[max_alp][max_cop];
    }
}