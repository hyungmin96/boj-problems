import java.io.*;
import java.util.*;

public class Main {

    static int N, S, D;
    static int[] childs;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        childs = new int[N + 1];
        for(int i = 1; i <= N; i ++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 1; i <= N - 1; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            graph[to].add(from);
        }

        dfs(S, -1);
        int answer = 0;
        for(int i = 1; i <= N; i ++){
            if(i != S && childs[i] >= D) answer ++;
        }

        System.out.println(answer * 2);
    }

    public static int dfs(int node, int p){
        if(node != S && graph[node].size() == 1){
            return 0;
        }

        for(int next : graph[node]){
            if(next == p) continue;
            childs[node] = Math.max(childs[node], dfs(next, node) + 1);
        }
        return childs[node];
    }
}