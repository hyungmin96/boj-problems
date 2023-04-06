import java.io.*;
import java.util.*;

public class Main {

    static int N, min = 987654321;
    static boolean[][] check;
    static int[][] map, idx;
    static int[][] dirs = {{ -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        idx = new int[N][N];
        check = new boolean[N][N];

        for(int i = 0; i < N; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j ++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        for(int r = 0; r < N; r ++){
            for(int c = 0; c < N; c ++){
                if(!check[r][c] && map[r][c] != 0){
                    dfs(r, c, cnt);
                    cnt ++;
                }
            }
        }

        for(int r = 0; r < N; r ++){
            for(int c = 0; c < N; c ++){
                if(map[r][c] == 1){
                    dijkstra(idx[r][c], r, c);
                }
            }
        }
        System.out.println(min);
    }

    public static void dijkstra(int land, int r, int c){
        boolean[][] v2 = new boolean[N][N];
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] { r, c, 0 });
        v2[r][c] = true;

        while(!que.isEmpty()){
            int[] cur = que.poll();
            for(int[] d : dirs){
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                if(isOutRange(nr, nc) || v2[nr][nc]) continue;
                v2[nr][nc] = true;
                if(map[nr][nc] == 1 && idx[nr][nc] != land){
                    min = Math.min(cur[2], min);
                    continue;
                }
                que.offer(new int[] { nr, nc, cur[2] + 1 });
            }
        }
    }

    public static void dfs(int r, int c, int cnt){
        idx[r][c] = cnt;
        for(int[] d : dirs){
            int nr = r + d[0];
            int nc = c + d[1];
            if(isOutRange(nr, nc) || map[nr][nc] == 0 || check[nr][nc]) continue;
            check[nr][nc] = true;
            dfs(nr, nc, cnt);
        }
    }

    public static boolean isOutRange(int r, int c){
        return r < 0 || c < 0 || r >= N || c >= N;
    }
}