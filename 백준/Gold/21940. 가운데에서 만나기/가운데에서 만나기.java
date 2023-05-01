import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[][] dp;
    static ArrayList<int[]>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N + 1][N + 1];
        graph = new ArrayList[N + 1];

        for(int i = 1; i <= N; i ++){
            Arrays.fill(dp[i], 987654321);
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[start].add(new int[] { end, cost });
        }
        
        K = Integer.parseInt(br.readLine());
        int[] friends = new int[K];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < K; i ++){
            friends[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= N; i ++){
            dijstra(i);            
        }

        int[][] dist = new int[N + 1][N + 1];
        for(int i = 0; i <= N; i ++){
            for(int j = 1; j <= N; j ++){
                if(i == j) continue;
                dist[i][j] = dp[i][j] + dp[j][i];
            }
        }

        int answer = 987654321;
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> list = new ArrayList<>();
        for(int j = 1; j <= N; j ++){
            int temp = 0;
            for(int i : friends){
                if(i == j) continue;
                temp = Math.max(temp, dist[i][j]);
            }
            if(temp <= answer){
                if(temp < answer){
                    list.clear();
                }
                answer = temp;
                list.add(j);
            }
        }

        Collections.sort(list);
        for(int i : list) sb.append(i + " ");
        System.out.println(sb.toString());
    }

    public static void dijstra(int node){
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[1] - o2[1];
            }
        });

        pq.offer(new int[] { node, 0 });
        dp[node][node] = 0;

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            for(int[] next : graph[cur[0]]){
                if(dp[node][next[0]] > cur[1] + next[1]){
                    dp[node][next[0]] = cur[1] + next[1];
                    pq.offer(new int[] { next[0], dp[node][next[0]] });
                }
            }
        }
    }
}