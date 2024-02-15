import java.util.*;
class Solution {
    int answer = 0;
    boolean[][][] v = new boolean[17][17][17];
    ArrayList<Integer>[] nodes = new ArrayList[17];
    public int solution(int[] info, int[][] edges) {
        for(int i = 0; i < 17; i ++){
            nodes[i] = new ArrayList<>();
        }
        
        for(int[] e : edges){
            int from = e[0];
            int to = e[1];
            nodes[to].add(from);
            nodes[from].add(to);
        }
        
        info[0] = -1;
        v[0][0][1] = true;
        dfs(0, 0, 1, info);
        
        return answer;
    }
    
    public void dfs(int node, int w, int s, int[] info){
        answer = Math.max(answer, s);
        for(int next : nodes[node]){
            if(w >= s) continue;
            if(v[next][w][s]) continue;
            int tmp = info[next];
            info[next] = -1;
            if(tmp == 0){
                s ++;
            }else if(tmp == 1){
                w ++;
            }
            v[next][w][s] = true;
            dfs(next, w, s, info);
            v[next][w][s] = false;
            if(tmp == 0){
                s --;
            }else if(tmp == 1){
                w --;
            }
            info[next] = tmp;
        }
    }
}