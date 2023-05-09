import java.util.*;
class Solution {

    static int exit = 987654321, max = 987654321;
    static int[] dp;
    static ArrayList<int[]>[] graph;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        dp = new int[n + 1];
        graph = new ArrayList[n + 1];
        Arrays.fill(dp, 987654321);
        
        int[] node_info = new int[n + 1]; // 0 : shelter, 1 : exit, 2 : summits
        for(int i = 0; i < gates.length; i ++) node_info[gates[i]] = 1;
        for(int i = 0; i < summits.length; i ++) node_info[summits[i]] = 2;
        for(int i = 1; i <= n; i ++) graph[i] = new ArrayList<>();
        for(int i = 0; i < paths.length; i ++){
            int start = paths[i][0];
            int end = paths[i][1];
            int cost = paths[i][2];
            
            graph[start].add(new int[] { end, cost });
            
            if(node_info[start] != 1)
                graph[end].add(new int[] { start, cost });
        }
        
        for(int i = 0; i < gates.length; i ++){
            bfs(n, gates[i], node_info);
        }
        
        for(int i = 1; i <= n; i ++){
            //System.out.println(i + " : " + dp[i]);
        }
        return new int[] { exit, max };
    }
    
    public void bfs(int n, int node, int[] node_info){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { node, 0 }); // node, max intensity
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            if(node_info[cur[0]] == 2){ // 다음 방문노드가 산봉우리인 경우
                if(max >= cur[1]){
                    if(max == cur[1]){
                        exit = Math.min(exit, cur[0]);
                    }else{
                        max = cur[1];
                        exit = cur[0];
                    }
                }
                continue;
            }
            for(int[] next : graph[cur[0]]){
                int max_intensity = Math.max(cur[1], next[1]);
                if(dp[next[0]] > max_intensity){
                    dp[next[0]] = max_intensity;
                    q.offer(new int[] { next[0], max_intensity });
                }
            }
        }
    }
}