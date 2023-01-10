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
                info[0] = -1;

        dfs(0, 1, 0, info);
        return answer;
    }
    
    public void dfs(int node, int s, int w, int[] info){
        if(w >= s) return;
        answer = Math.max(answer, s);
        for(int next : vertex[node]){
            int temp = info[next];
            if(info[next] == 0) s ++;
            else if(info[next] == 1) w ++;
            if(!check[next][s][w]){
                info[next] = -1;
                check[next][s][w] = true;
                dfs(next, s, w, info);
                check[next][s][w] = false;
                info[next] = temp;
                if(temp == 0) s --;
                else if(temp == 1) w --;
            }
        }
    }
}

