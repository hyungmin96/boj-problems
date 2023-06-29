import java.util.*;
import java.io.*;

public class Main {

    static int max_node = -1;
    static long max_dis;
    static int N, temp_cur, temp_node, temp_dis;
    static long[] dist;
    static boolean[] v;
    static int[][] edge;
    static ArrayList<int[]>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        edge = new int[N][3];
        dist = new long[N];
        graph = new ArrayList[N];

        for(int i = 0; i < N; i ++) graph[i] = new ArrayList<>();
        for(int i = 0; i < N - 1; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            edge[i] = new int[] { from, to, cost };
            graph[from].add(new int[] { to, cost });
            graph[to].add(new int[] { from, cost });
        }

        long answer = 0;
        for(int i = 0; i < edge.length - 1; i ++){

            int t1 = edge[i][0];
            int t2 = edge[i][1];
            int cost = edge[i][2];

            // 입력으로 주어진 간선 중 하나를 잘라 별도의 트리로 만듦
            v = new boolean[N];
            int node1 = dfs(t1, edge[i]);

            v = new boolean[N];
            dfs(node1, edge[i]);

            v = new boolean[N];
            int node2 = dfs(t2, edge[i]);

            v = new boolean[N];
            dfs(node2, edge[i]);

            answer = Math.max(answer, dist[node1] + dist[node2] + cost);
        }
        System.out.println(answer);
    }

    // 현재 node에서 가장 멀리있는(가중치가 큰) 노드 탐색
    public static int dfs(int node, int[] edge){
        v[node] = true;
        int lastNode = node;
        dist[node] = 0;
        for(int[] next : graph[node]){
            if ((node == edge[0] && next[0] == edge[1]) || (node == edge[1] && next[0] == edge[0])) continue;
                if(v[next[0]]) continue;
                v[next[0]] = true;
                int cur = dfs(next[0], edge);
                if(dist[node] < dist[next[0]] + next[1]){
                    dist[node] = dist[next[0]] + next[1];
                    lastNode = cur;
                }
        }

        return lastNode;
    }
}