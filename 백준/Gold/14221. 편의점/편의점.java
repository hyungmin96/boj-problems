import java.util.*;
import java.io.*;

public class Main{

    static long min = 9876543210L;
    static int N, M, node = 5001;
    static int[] dp;
    static int[] house, cv, hIdx = new int[5001];
    static ArrayList<int[]>[] graph;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N + 1];
        graph = new ArrayList[N + 1];

        for(int i = 1; i <= N; i ++) graph[i] = new ArrayList<>();
        for(int i = 0; i < M; i ++){
            st = new StringTokenizer(br.readLine(), " ");

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[start].add(new int[] { end, cost });
            graph[end].add(new int[] { start, cost });
        }

        st = new StringTokenizer(br.readLine(), " ");
        int hCnt = Integer.parseInt(st.nextToken());
        int cCnt = Integer.parseInt(st.nextToken()); // convenience store

        cv = new int[cCnt];
        house = new int[hCnt];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < hCnt; i ++){
            int num = Integer.parseInt(st.nextToken());
            house[i] = num;
            hIdx[num] = 1;
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < cCnt; i ++){
            int num = Integer.parseInt(st.nextToken());
            cv[i] = num;
        }

        Arrays.fill(dp, 987654321);
        bfs(cv);
        System.out.println(node);
    }

    public static void bfs(int[] cv){
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[1] - o2[1];
            }
        });

        for(int c : cv){
            pq.offer(new int[] { c, 0 });
            dp[c] = 0;
        }

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            if(min < cur[1]) continue;
            if(hIdx[cur[0]] == 1){
                if(cur[1] <= min){
                    if(cur[1] == min){
                        node = Math.min(node, cur[0]);
                    }else{
                        node = cur[0];
                    }
                    min = cur[1];
                }
                continue;
            }

            for(int[] n : graph[cur[0]]){
                if(dp[n[0]] > cur[1] + n[1]){
                    dp[n[0]] = cur[1] + n[1];
                    pq.offer(new int[] { n[0], cur[1] + n[1] });
                }
            }
        }
    }
}