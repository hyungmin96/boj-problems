import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<int[]>[] vertex;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        vertex = new ArrayList[n + 1];
        int[] values = new int[n + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i <= n; i ++){
            vertex[i] = new ArrayList<>();
            values[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < r; i ++){
            st = new StringTokenizer(br.readLine());

            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            vertex[to].add(new int[] { from, cost });
            vertex[from].add(new int[] { to, cost });
        }

        long answer = 0;
        for(int i = 1; i <= n; i ++){
            answer = Math.max(answer, bfs(n, m, i, values));
        }

        System.out.println(answer);
    }

    public static int bfs(int n, int m, int node, int[] values){
        int[] dist = new int[n + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[1] - o2[1];
            }
        });

        Arrays.fill(dist, 987654321);
        pq.offer(new int[] { node, 0 }); // 현재 노드, 거리, 아이템 수
        dist[node] = 0;

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            for(int[] next : vertex[cur[0]]){
               if(cur[1] + next[1] <= m && dist[next[0]] > next[1] + cur[1]){
                    dist[next[0]] = next[1] + cur[1];
                    pq.offer(new int[] { next[0], cur[1] + next[1]});
               }
            }
        }

        int max = 0;
        for(int i = 1; i <= n; i ++){
            if(dist[i] <= m){
                max += values[i];
            }
        }
        return max;
    }
}