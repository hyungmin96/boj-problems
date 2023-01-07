import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<int[]>[] vertex;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int node = Integer.parseInt(br.readLine());

        dp = new int[v + 1];
        vertex = new ArrayList[v + 1];
        for(int i = 1; i <= v; i ++) vertex[i] = new ArrayList<>();
        for(int i = 0; i < e; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            vertex[start].add(new int[] { end, cost });
            // vertex[end].add(new int[] { start, cost });
        }

        dijkstra(v, node);
    }

    public static void dijkstra(int v, int node){
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[1] - o2[1];
            }
        });

        Arrays.fill(dp, 987654321);
        dp[node] = 0;
        pq.offer(new int[] { node, 0 });

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            for(int[] next : vertex[cur[0]]){
                if(dp[next[0]] > next[1] + cur[1]){
                    dp[next[0]] = next[1] + cur[1];
                    pq.offer(new int[] { next[0], next[1] + cur[1] });
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= v; i ++){
            sb.append(dp[i] == 987654321 ? "INF\n" : dp[i] + "\n");
        }

        System.out.println(sb.toString());
    }
}