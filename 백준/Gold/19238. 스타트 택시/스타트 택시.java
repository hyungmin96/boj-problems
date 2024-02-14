import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException{
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int N, M, K;
    int[] taxi_pos = new int[2];
    int[][] map, peoples;
    int[][] dirs = {{ -1, 0 },{ 0, -1 },{ 0, 1 },{ 1, 0 }};

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        peoples = new int[M][2];

        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j ++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        taxi_pos = new int[] { r, c };

        for(int i = 0; i < M; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int sr = Integer.parseInt(st.nextToken()) - 1;
            int sc = Integer.parseInt(st.nextToken()) - 1;

            map[sr][sc] = i + 2;
            int dr = Integer.parseInt(st.nextToken()) - 1;
            int dc = Integer.parseInt(st.nextToken()) - 1;
            peoples[i] = new int[] { dr, dc };
        }

        for(int i = 0; i < M; i ++){
            if(!find()){
                K = -1;
                break;
            }
        }

        System.out.println(K);
    }

    public boolean find(){
        boolean[][] v = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                if(o1[2] != o2[2]) return o1[2] - o2[2];
                else if(o1[0] != o2[0]) return o1[0] - o2[0];
                return o1[1] - o2[1];
            }
        });

        q.offer(new int[] { taxi_pos[0], taxi_pos[1], 0 });
        v[taxi_pos[0]][taxi_pos[1]] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            if(map[cur[0]][cur[1]] > 1){
                pq.offer(new int[] { cur[0], cur[1], cur[2] });
                continue;
            }

            if(cur[2] == K) continue;
            for(int d = 0; d < 4; d ++){
                int nr = cur[0] + dirs[d][0];
                int nc = cur[1] + dirs[d][1];
                if(isOutRange(nr, nc) || v[nr][nc] || map[nr][nc] == 1) continue;
                v[nr][nc] = true;
                q.offer(new int[] { nr, nc, cur[2] + 1 });
            }
        }

        if(pq.size() == 0) return false;
        int pr = pq.peek()[0];
        int pc = pq.peek()[1];
        K -= pq.peek()[2];

        taxi_pos = new int[] { pr, pc };
        if(move(map[pr][pc], pr, pc)){
            map[pr][pc] = 0;
            return true;
        }

        return false;
    }

    public boolean move(int idx, int r, int c){
        boolean[][] v = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[] { r, c, 0, 0 });
        v[taxi_pos[0]][taxi_pos[1]] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            if(cur[0] == peoples[idx - 2][0] && cur[1] == peoples[idx - 2][1]){
                K -= cur[2];
                K += (cur[2] * 2);
                taxi_pos = new int[] { cur[0], cur[1] };
                return true;
            }
            if(cur[2] == K) continue;
            for(int d = 0; d < 4; d ++){
                int nr = cur[0] + dirs[d][0];
                int nc = cur[1] + dirs[d][1];
                if(isOutRange(nr, nc) || v[nr][nc] || map[nr][nc] == 1) continue;
                v[nr][nc] = true;
                q.offer(new int[] { nr, nc, cur[2] + 1 });
            }
        }

        return false;
    }

    public boolean isOutRange(int r, int c){
        return r < 0 || c < 0 || r >= N || c >= N;
    }
}

