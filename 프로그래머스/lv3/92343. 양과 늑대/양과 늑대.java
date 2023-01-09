import java.util.*;
class Solution {
    
    int answer = 0;
    ArrayList<Integer>[] vertex;
    boolean[][][] check;
    
    public int solution(int[] info, int[][] edges) {
        // 방문노드, 양, 늑대
        check = new boolean[20][20][20];
        vertex = new ArrayList[info.length];
        for(int i = 0; i < info.length; i ++) vertex[i] = new ArrayList<>();
        for(int[] e : edges){
            vertex[e[0]].add(e[1]);
            vertex[e[1]].add(e[0]);
        }
        dfs(0, 0, 0, info);
        return answer;
    }
    
    public void dfs(int node, int s, int w, int[] info){
        if(info[node] == 0) s ++;
        else if(info[node] == 1) w ++;

        if(w >= s) return;
        answer = Math.max(answer, s);

        for(int next : vertex[node]){
            int temp = info[node];
            if(!check[next][s][w]){
                info[node] = -1;
                check[node][s][w] = true;
                dfs(next, s, w, info);
                info[node] = temp;
                check[node][s][w] = false;
            }
        }
    }
}

