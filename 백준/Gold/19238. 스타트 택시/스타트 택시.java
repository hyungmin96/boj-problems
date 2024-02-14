import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    static final int INF = 987654321;

    int N, M, K;

    int[] taxi = new int[2];

    int[][] destination;

    int[][] map;

    int[][] dirs = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        destination = new int[M + 1][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    map[i][j] = -1;
                }
            }
        }

        st = new StringTokenizer(br.readLine(), " ");

        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;

        taxi = new int[] { r, c };

        for (int idx = 0; idx < M; idx++) {
            st = new StringTokenizer(br.readLine(), " ");

            int sr = Integer.parseInt(st.nextToken()) - 1;
            int sc = Integer.parseInt(st.nextToken()) - 1;
            int dr = Integer.parseInt(st.nextToken()) - 1;
            int dc = Integer.parseInt(st.nextToken()) - 1;

            map[sr][sc] = (idx + 1);
            destination[idx + 1] = new int[] { dr, dc };
        }

        solve();
    }

    public void solve() {
        for(int i = 0; i < M && K > 0; i ++) {
            int idx = findPerson();
            if(K < 0 || idx == -1){
                K = -1;
                break;
            }

            int[] dest = destination[idx];
            moveDestination(dest);
        }
        System.out.println(K);
    }

    public void moveDestination(int[] dest){
        boolean[][] v = new boolean[N][N];
        // [r, c, 소비한 연료]
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { taxi[0], taxi[1], 0 });
        v[taxi[0]][taxi[1]] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            if(cur[0] == dest[0] && cur[1] == dest[1]){
                taxi = cur;
                K -= cur[2];
                if(K < 0){
                    K = -1;
                }else{
                    K += (cur[2] * 2);
                }
                return;
            }
            
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dirs[d][0];
                int nc = cur[1] + dirs[d][1];
                if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                    continue;
                }

                if (v[nr][nc]) {
                    continue;
                }

                if(cur[2] + 1 > K){
                    continue;
                }

                if (map[nr][nc] == -1) {
                    continue;
                }

                v[nr][nc] = true;
                q.offer(new int[] { nr, nc, cur[2] + 1 });
            }
        }
        K = -1;
    }

    public int findPerson() {
        boolean[][] v = new boolean[N][N];
        // [r, c, 소비한 연료]
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { taxi[0], taxi[1], 0 });
        v[taxi[0]][taxi[1]] = true;
        int idx = -1, fuel = 0;
        int dist = INF, row = INF, col = INF;
        boolean flag = false;
        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if (map[cur[0]][cur[1]] > 0) {
                // 사람 발견
                flag = true;
                if (dist >= cur[2]) {
                    if (dist > cur[2]) {
                        row = cur[0];
                        col = cur[1];
                    } else {
                        if (row > cur[0]) {
                            row = cur[0];
                            col = cur[1];
                        } else if (row == cur[0]) {
                            col = Math.min(col, cur[1]);
                        }
                    }
                    dist = cur[2];
                    fuel = dist;
                    idx = map[row][col];
                }
                continue;
            }

            if (K <= cur[2]) {
                return -1;
            }

            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dirs[d][0];
                int nc = cur[1] + dirs[d][1];
                if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                    continue;
                }

                if (v[nr][nc]) {
                    continue;
                }

                if (map[nr][nc] == -1) {
                    continue;
                }

                if(dist <= cur[2]){
                    continue;
                }

                v[nr][nc] = true;
                q.offer(new int[] { nr, nc, cur[2] + 1 });
            }
        }

        if(K <= fuel){
            return -1;
        }

        if(flag){

            taxi[0] = row;
            taxi[1] = col;
            
            K -= fuel;
            map[row][col] = 0;
        }else{
            K = -1;
        }
        return idx;
    }
}
