import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    public int N, M, dist = 987654321;
    public int[] v;
    public ArrayList<int[]>[] vertex;

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        v = new int[N + 1];
        vertex = new ArrayList[N + 1];

        for(int i = 1; i <= N; i ++) vertex[i] = new ArrayList<>();
        for(int i = 0; i < M; i ++){
            st = new StringTokenizer(br.readLine(), " ");

            int to = Integer.parseInt(st.nextToken());    
            int from = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            vertex[to].add(new int[] { from, cost });
            vertex[from].add(new int[] { to, cost });
        }

        int max = -1;
        int[] path = getPath();
        for(int i = 0; i < path.length - 1; i ++){
            int n1 = path[i];
            int n2 = path[i + 1];

            int cur_dist = bfs(n1, n2);
            if(cur_dist == -1){
                System.out.println(-1);
                return;
            }else{
                max = Math.max(cur_dist - dist, max);
            }
        }

        System.out.println(max);
    }

    public int bfs(int n1, int n2){
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[1] - o2[1];
            }
        });

        Arrays.fill(v, Integer.MAX_VALUE);
        pq.offer(new int[] { 1, 0 });
        v[1] = 0;

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            if(cur[0] == N){
                return cur[1];
            }
            for(int[] next : vertex[cur[0]]){
                if((next[0] == n1 && cur[0] == n2) || (next[0] == n2 && cur[0] == n1)) continue;
                if(v[next[0]] > cur[1] + next[1]){
                    v[next[0]] = cur[1] + next[1];
                    pq.offer(new int[] { next[0], v[next[0]] });
                }
            }
        }
        return -1;
    }

    public int[] getPath(){
        // from, cost, depth
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[1] - o2[1];
            }
        });

        int[] path = new int[5001];
        Arrays.fill(v, Integer.MAX_VALUE);
        pq.offer(new int[] { 1, 0, 0 });
        v[1] = 0;

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            if(cur[0] == N){
                dist = cur[1];
                break;
            }
            for(int[] next : vertex[cur[0]]){
                if(v[next[0]] > cur[1] + next[1]){
                    v[next[0]] = cur[1] + next[1];
                    path[next[0]] = cur[0];
                    pq.offer(new int[] { next[0], v[next[0]], cur[2] + 1 });
                }
            }
        }

        int idx = N;
        ArrayList<Integer> edges = new ArrayList<>();
        while(true){
            edges.add(idx);
            if(path[idx] == 0) break;
            idx = path[idx];
        }

        int[] temp = new int[edges.size()];
        for(int i = 0; i < edges.size(); i ++) temp[i] = edges.get(i);
        return temp;
    }
}