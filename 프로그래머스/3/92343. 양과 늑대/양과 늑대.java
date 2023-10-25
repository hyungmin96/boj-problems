import java.util.*;
class Solution {
    int answer = 0;
    ArrayList<Integer>[] vertex;
    boolean[][][] v;
    
    public int solution(int[] info, int[][] edges) {
        vertex = new ArrayList[info.length + 1];
        for(int i = 0; i <= info.length; i ++)
            vertex[i] = new ArrayList<>();
        
        for(int i = 0; i < edges.length; i ++){
            int s = edges[i][0];
            int e = edges[i][1];
            
            vertex[s].add(e);
            vertex[e].add(s);
        }
        
        v = new boolean[info.length + 1][18][18];
        dfs(info, 0, 0, 0);
        
        return answer;
    }
    
    public void dfs(int[] info, int node, int s, int w){
        answer = Math.max(answer, s);
        int tmp = info[node];
        if(tmp == 0) s ++; else if(tmp == 1) w ++;
        if(s <= w) return;
        
        if(v[node][s][w]) return;
        v[node][s][w] = true;
        info[node] = -1;
        
        for(int next : vertex[node]){
            dfs(info, next, s, w);
        }
        
        v[node][s][w] = false;
        info[node] = tmp;
        if(tmp == 0) s --; else if(tmp == 1) w --;
    }
}