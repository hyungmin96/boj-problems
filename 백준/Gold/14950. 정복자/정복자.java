import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<int[]>[] vertex;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        check = new boolean[n + 1];
        vertex = new ArrayList[n + 1];
        for(int i = 1; i <= n; i ++) vertex[i] = new ArrayList<>();
        for(int i = 0; i < m; i ++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            vertex[s].add(new int[] { e, cost });
            vertex[e].add(new int[] { s, cost });
        }

        System.out.println(bfs(1, n, t));
    }

    public static long bfs(int start, int n, int t){
        int cnt = 0;
        long answer = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[1] - o2[1];
            }
        });
        pq.offer(new int[] { start, 0 });

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            if(check[cur[0]]) continue;
            answer += cur[1];
            check[cur[0]] = true;
            cnt ++;
            for(int[] next : vertex[cur[0]]){
                if(check[next[0]]) continue;
                pq.offer(new int[] { next[0], next[1] });
            }
        }

        long temp = 0;
        for(int i = n - 2; i >= 0; i --){
            temp += t * i;
        }
        return answer + temp;
    }
}