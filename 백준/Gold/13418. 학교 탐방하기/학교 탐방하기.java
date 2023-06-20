import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static ArrayList<int[]>[] graph;
    static PriorityQueue<int[]> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
 
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for(int i = 0; i <= N; i ++) graph[i] = new ArrayList<>();

        for(int i = 0; i <= M; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int type = Integer.parseInt(st.nextToken()); // 0 : 오르막길, 1 : 내리막길

            graph[from].add(new int[] { to, type });
            graph[to].add(new int[] { from, type });
        }

        pq = new PriorityQueue<>(new Comparator<>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[1] - o2[1];
            }
        });
        long t1 = solution();

        pq = new PriorityQueue<>(new Comparator<>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o2[1] - o1[1];
            }
        });
        long t2 = solution();
        System.out.println(t1 * t1 - t2 * t2);
    }

    public static long solution(){
        boolean[] v = new boolean[N + 1];
        int cnt = 0;
        pq.offer(new int[] { 0, -1 }); // node, type, type count, count
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            if(v[cur[0]]) continue;
            v[cur[0]] = true;
            if(cur[1] == 0) cnt ++;
            for(int[] next : graph[cur[0]]){
                pq.offer(next);
            }
        }

        return cnt;
    }
}