import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException{
        Solution sol = new Solution();
        sol.solution();
    }
}
    
class Solution{

    int N, M, S, cnt = 1;
    int[] order_node;
    boolean[] v;
    ArrayList<Integer>[] vertex;
    StringBuilder sb = new StringBuilder();

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        v = new boolean[N + 1];
        vertex = new ArrayList[N + 1];
        order_node = new int[N + 1];

        for(int i = 1; i <= N; i ++){
            vertex[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            vertex[s].add(e);
            vertex[e].add(s);
        }

        for(int i = 1; i <= N; i ++){
            Collections.sort(vertex[i]);
        }

        v[S] = true;
        dfs(S);

        for(int i = 1; i <= N; i ++){
            sb.append(order_node[i] + "\n");
        }
        System.out.println(sb.toString());
    }

    public void dfs(int node){
        order_node[node] = cnt ++;
        for(int next : vertex[node]){
            if(v[next]) continue;
            v[next] = true;
            dfs(next);
        }
    }
}