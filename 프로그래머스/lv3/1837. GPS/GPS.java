import java.util.*;
class Solution {
    
    static int[][] dp;
    static ArrayList<Integer>[] graph;
    
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        int answer = 0;
        
        graph = new ArrayList[n + 1];
        dp = new int[k][n + 1];
        
        for(int i = 0; i < k; i ++)
            Arrays.fill(dp[i], 1001);
        
        for(int i = 0; i <= n; i ++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < m; i ++){
            int s = edge_list[i][0];
            int e = edge_list[i][1];

            graph[s].add(s);
            graph[s].add(e);
            graph[e].add(s);
        }
        
        
        dp[0][gps_log[0]] = 0;
        for(int i = 0; i < k - 1; i ++){
            for(int j = 1; j <= n; j ++){
                if(dp[i][j] == 1001) continue;
                for(int next : graph[j]){
                    if(next != gps_log[i + 1]){
                        dp[i + 1][next] = Math.min(dp[i + 1][next], dp[i][j] + 1);    
                    }else{
                        dp[i + 1][next] = Math.min(dp[i + 1][next], dp[i][j]);    
                    }
                }
            }
        }
        
        answer = dp[k - 1][gps_log[gps_log.length - 1]];
        return answer == 1001 ? -1 : answer;
    }
}