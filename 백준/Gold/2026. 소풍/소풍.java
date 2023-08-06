import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    public int N, K, F;
    public ArrayList<Integer>[] graph;
    public int[][] relation;
    public boolean[] v;
    public boolean flag = false;

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());

        v = new boolean[N + 1];
        relation = new int[N + 1][N + 1];
        graph = new ArrayList[N + 1];
        for(int i = 1; i <= N; i ++) graph[i] = new ArrayList<>();
        
        for(int i = 0; i < F; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int f1 = Integer.parseInt(st.nextToken());
            int f2 = Integer.parseInt(st.nextToken());

            graph[f1].add(f2);
            graph[f2].add(f1);

            relation[f1][f2] = 1;
            relation[f2][f1] = 1;
        }

        for(int i = 1; i <= N; i ++){
            if(graph[i].size() < K - 1) continue;
            if(flag) break;
            v[i] = true;
            dfs(1, i, i);
            v[i] = false;
        }

        if(!flag){
            System.out.println(-1);
        }
    }

    public void dfs(int depth, int idx, int f){
        if(flag) return;
        if(depth == K){
            flag = true;
            for(int i = 1; i < v.length; i ++){
                if(!v[i]) continue;
                System.out.println(i);
            }
            return;
        }
        
        for(int next = idx; next <= N; next ++){
            if(v[next] || !check(next)) continue;
            v[next] = true;
            dfs(depth + 1, next + 1, next);
            v[next] = false;
        }
    }

    public boolean check(int f){
        for(int i = 1; i <= N; i ++){
            if(v[i] && relation[i][f] != 1){
                return false;
            }
        }
        return true;
    }
}