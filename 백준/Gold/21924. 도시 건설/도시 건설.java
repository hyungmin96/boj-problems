import java.util.*;
import java.io.*;

public class Main {

    static long sum = 0;
    static int N, M;
    static int[] c;
    static ArrayList<int[]>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        c = new int[N + 1];
        graph = new ArrayList[N + 1];
        for(int i = 1; i <= N; i ++) graph[i] = new ArrayList<>();

        for(int i = 0; i < M; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            sum += cost;
            graph[s].add(new int[] { e, cost });
            graph[e].add(new int[] { s, cost });
        }

        System.out.println(bfs());
    }

    public static long bfs(){
        boolean[] v = new boolean[N + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[1] - o2[1];
            }
        });

        int cnt = 0;
        long total = 0;
        pq.offer(new int[] { 1, 0 }); // node, cost, total, cnt
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            if(!v[cur[0]]){
                cnt ++;
                total += cur[1];
                v[cur[0]]= true;
            } 
            if(cnt == N) 
                return sum - total;
            for(int[] n : graph[cur[0]]){
                if(!v[n[0]]){
                    pq.offer(new int[] { n[0], n[1] });
                }
            }
        }
        return -1;
    }
}