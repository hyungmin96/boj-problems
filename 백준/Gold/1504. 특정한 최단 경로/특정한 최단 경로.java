import java.io.*;
import java.util.*;

public class Main {

    static int N, E;
    static ArrayList<int[]>[] vertex;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        vertex = new ArrayList[N + 1];
        for(int i = 1; i <= N; i ++) vertex[i] = new ArrayList<>();
        for(int i = 0; i < E; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            vertex[s].add(new int[] { e, cost });
            vertex[e].add(new int[] { s, cost });
        }

        st = new StringTokenizer(br.readLine(), " ");
        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());


        int dis = getDistance(n1, n2);
        int diff1 = getDistance(1, n1) + getDistance(n2, N);
        int diff2 = getDistance(1, n2) + getDistance(n1, N);
        if(dis >= 987654321 || diff1 >= 987654321 || diff2 >= 987654321){
            System.out.println(-1);
            return;
        }
        System.out.println(Math.min(diff1, diff2) + dis);
    }

    public static int getDistance(int start, int end){
        int dis = 987654321;
        int[] check = new int[N + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[1] - o2[1];
            }
        });

        Arrays.fill(check, 987654321);
        check[start] = 0;
        pq.offer(new int[] { start, 0 });
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            if(cur[0] == end){
                dis = Math.min(dis, cur[1]);
                continue;
            }
            for(int[] next : vertex[cur[0]]){
                if(check[next[0]] >= check[cur[0]] + next[1]){
                    check[next[0]] = check[cur[0]] + next[1];
                    pq.offer(new int[] { next[0], check[cur[0]] + next[1]});
                }
            }
        }
        return dis;
    }
}