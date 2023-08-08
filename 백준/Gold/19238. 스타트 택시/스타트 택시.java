import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    public int N, M, K, tr, tc;
    public int[][] map, dirs = {
        { -1, 0 },
        { 0, -1 },
        { 0, 1 },
        { 1, 0}
    };

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j ++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");

        tr = Integer.parseInt(st.nextToken()) - 1;
        tc = Integer.parseInt(st.nextToken()) - 1;

        int[][] path = new int[M][4];
        for(int i = 0; i < M; i ++){
            st = new StringTokenizer(br.readLine(), " ");

            int pr = Integer.parseInt(st.nextToken()) - 1;
            int pc = Integer.parseInt(st.nextToken()) - 1;
            int gr = Integer.parseInt(st.nextToken()) - 1;
            int gc = Integer.parseInt(st.nextToken()) - 1;

            map[pr][pc] = 2 + i;
            path[i] = new int[] { pr, pc, gr, gc };
        }

        for(int i = 0; i < M; i ++){
            // people idx, remain fuel
            int[] info = findPeople();
            info[0] -= 2;
            if(info[0] <= -1){
                System.out.println(-1);
                return;
            }

            K -= info[1];
            int gain_fuel = destination(path[info[0]]);
            if(gain_fuel <= -1){
                System.out.println(-1);
                return;
            }
            K -= gain_fuel;
            K += 2 * gain_fuel;
        }

        System.out.println(K);
    }

    public int destination(int[] tmp){
        boolean[][] v = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>(); // [r, c, 연료 소모량]

        v[tmp[0]][tmp[1]] = true;
        q.offer(new int[] { tr, tc, 0 });   

        while(!q.isEmpty()){
            int[] cur = q.poll();
            if(cur[0] == tmp[2] && cur[1] == tmp[3]){
                tr = cur[0];
                tc = cur[1];
                return cur[2]; // 소비한 연료
            }
            if(cur[2] == K) continue;
            for(int d = 0; d < 4; d ++){
                int nr = cur[0] + dirs[d][0];
                int nc = cur[1] + dirs[d][1];
                if(nr < 0 || nc < 0 || nr >= N || nc >= N || v[nr][nc] || map[nr][nc] == 1) continue;
                v[nr][nc] = true;
                q.offer(new int[] { nr, nc, cur[2] + 1 });
            }
        }

        return -1;
    }

    public int[] findPeople(){
        boolean[][] v = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>(); // [r, c, 연료 소모량]
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<>(){
            @Override
            public int compare(int[] o1, int[] o2){
                if(o1[3] != o2[3]) return o1[3] - o2[3];
                else if(o1[0] != o2[0]) return o1[0] - o2[0];
                return o1[1] - o2[1];
            }
        });
        v[tr][tc] = true;
        q.offer(new int[] { tr, tc, 0 });   

        while(!q.isEmpty()){
            int[] cur = q.poll();
            if(map[cur[0]][cur[1]] >= 2){
                int idx = map[cur[0]][cur[1]];
                pq.offer(new int[] { cur[0], cur[1], idx, cur[2] });
                continue;
            }
            if(cur[2] == K) continue;
            for(int d = 0; d < 4; d ++){
                int nr = cur[0] + dirs[d][0];
                int nc = cur[1] + dirs[d][1];
                if(nr < 0 || nc < 0 || nr >= N || nc >= N || v[nr][nc] || map[nr][nc] == 1) continue;
                v[nr][nc] = true;
                q.offer(new int[] { nr, nc, cur[2] + 1 });
            }
        }

        if(pq.size() == 0)
            return new int[] { -1, -1 };
        else{
            tr = pq.peek()[0];
            tc = pq.peek()[1];
            map[pq.peek()[0]][pq.peek()[1]] = 0;
            return new int[] { pq.peek()[2],  pq.peek()[3] };
        }
    }
}