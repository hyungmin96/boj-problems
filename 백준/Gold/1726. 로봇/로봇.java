import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] start, end;
    static int[] cost = { 0, 1, 2 };
    static int[][] map, dir = {
        { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }
    };
    static int[][][] md = {
        {{0}, {2, 3}, {1}},
        {{1}, {2, 3}, {0}},
        {{2}, {0, 1}, {3}},
        {{3}, {0, 1}, {2}}
    };
    static int[][][] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        check = new int[4][N][M];

        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < M; j ++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        start = new int[] { Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1};

        st = new StringTokenizer(br.readLine(), " ");
        end = new int[] { Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1};
    
        System.out.println(bfs());
    }

    public static int bfs(){
        int min = Integer.MAX_VALUE;
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] { start[0], start[1], start[2], 0 }); // r, c, dir, count

        for(int i = 0; i < 4; i ++)
            for(int j = 0; j < N; j ++)
                Arrays.fill(check[i][j], 987654321);

        check[start[2]][start[0]][start[1]] = 0;
        while(!que.isEmpty()){
            int[] cur = que.poll();
            int temp = check(cur[0], cur[1], cur[2], cur[3]);
            if(temp != -1) {
                min = Math.min(temp, min);
                continue;
            }
            int[][] dirs = md[cur[2]];
            for(int i = 0; i < 3; i ++){
                for(int j = 0; j < dirs[i].length; j ++){
                    int di = dirs[i][j];
                    for(int k = 1; k <= 3; k ++){
                        int nr = cur[0] + dir[di][0] * k;
                        int nc = cur[1] + dir[di][1] * k;
                        if(!isRange(nr, nc) || map[nr][nc] == 1) break;
                        if(check[di][nr][nc] > cur[3] + cost[i] + 1){
                            check[di][nr][nc] = cur[3] + cost[i] + 1;
                            que.offer(new int[] { nr, nc, di, cur[3] + cost[i] + 1});
                        }
                    }
                }
            }
        }

        return min;
    }

    public static int check(int r, int c, int d, int cur){
        int min = 987654321;
        int[][] dirs = md[d];
        for(int i = 0; i < 3; i ++){
            for(int j = 0; j < dirs[i].length; j ++){
                if(r == end[0] && c == end[1] && dirs[i][j] == end[2]){
                    min = Math.min(min, cur + cost[i]);
                }
            }
        }
        return min == 987654321 ? -1 : min;
    }

    public static boolean isRange(int r, int c){
        return !(r < 0 || c < 0 || r >= N || c >= M);
    }
}