import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        Solution sol = new Solution();
        sol.solution();
    }
}
    
class Solution{
    int N;
    long answer = 987654321;
    boolean[] v;
    ArrayList<int[]>[] vertex;

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        v = new boolean[N + 1];
        vertex = new ArrayList[N + 1];
        
        for(int i = 0; i <= N; i ++){
            vertex[i] = new ArrayList<>();
        }

        for(int i = 0; i < N; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j ++){
                int c = Integer.parseInt(st.nextToken());
                if(c == 0) continue;
                vertex[i + 1].add(new int[] { j + 1, c });
            }
        }

        for(int i = 1; i <= N; i ++){
            v[i] = true;
            dfs(0, i, i, 0);
            v[i] = false;
        }

        System.out.println(answer);
    }

    public void dfs(int depth, int start, int node, long cur){
        if(answer <= cur) return;
        if(depth == N && node == start){
            answer = Math.min(answer, cur);
            return;
        }

        for(int[] next : vertex[node]){
            if(depth == N - 1 && next[0] == start){
                dfs(depth + 1, start, next[0], cur + next[1]);    
            }
            if(v[next[0]]) continue;
            v[next[0]] = true;
            dfs(depth + 1, start, next[0], cur + next[1]);
            v[next[0]] = false;
        }
    }
}
