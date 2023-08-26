import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int N, M;
    boolean[][][] v;
    int[][] map, dirs = {{ -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }, { 0, 0 }};

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        int max_height = 0;
        for(int i = 0; i < N; i ++){
            String str = br.readLine();
            for(int j = 0; j < M; j ++){
                map[i][j] = str.charAt(j) - '0';
                max_height = Math.max(max_height, map[i][j]);
            }
        }

        int answer = 0;
        v = new boolean[max_height + 1][N][M];
        for(int h = 1; h <= max_height; h ++){
            for(int r = 1; r < N; r ++){
                for(int c = 1; c < M; c ++){
                    if(map[r][c] >= h || v[h][r][c]) continue;
                    answer += bfs(r, c, h);
                }
            }
        }

        System.out.println(answer);
    }

    public int bfs(int r, int c, int height){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { r, c });
        v[height][r][c] = true;

        int cnt = 1;
        boolean flag = true;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            for(int d = 0; d < 4; d ++){
                int nr = cur[0] + dirs[d][0];
                int nc = cur[1] + dirs[d][1];
                if(nr < 0 || nc < 0 || nr >= N || nc >= M) {
                    flag = false;
                    continue;
                }
                if(map[nr][nc] >= height) continue;
                if(v[height][nr][nc]) continue;
                v[height][nr][nc] = true;

                cnt ++;
                q.offer(new int[] { nr, nc });
            }
        }

        if(flag)
            return cnt;

        return 0;
    }
}

