import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static char[][] map;
    static int[] start, end;
    static int[][][] check;
    static int[][] dir = {{ -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        check = new int[4][N][M];

        for(int i = 0; i < N; i ++){
            String str = br.readLine();
            for(int j = 0; j < M; j ++){
                map[i][j] = str.charAt(j);
                if(str.charAt(j) == 'C'){
                    if(start == null) start = new int[] { i, j };
                    else end = new int[] { i, j };
                }
            }
        }
        
        System.out.println(bfs());
    }

    public static int bfs(){
        int min = 987654321;
        Queue<int[]> que = new LinkedList<>();
        for(int i = 0; i < 4; i ++){
            for(int j = 0; j < N; j ++){
                Arrays.fill(check[i][j], 987654321);
            }
        }
        
        for(int i = 0; i < 4; i ++){
            que.offer(new int[] { start[0], start[1], i, 0 }); // r, c, dir, time;
            check[i][start[0]][start[1]] = 0;
        }

        while(!que.isEmpty()){
            int[] cur = que.poll();
            if(cur[0] == end[0] && cur[1] == end[1]){
                min = Math.min(min, cur[3]);
                continue;
            }
            for(int d = 0; d < 4; d ++){
                int installed = 0;
                int nr = cur[0] + dir[d][0];
                int nc = cur[1] + dir[d][1];
                if(isOutRange(nr, nc) || map[nr][nc] == '*' || check[d][nr][nc] <= cur[3]) continue;
                if(d == (cur[2] + 3) % 4 || d == (cur[2] + 1) % 4)
                    installed ++;
                else if(d == (cur[2] + 2) % 4) continue;
                check[d][nr][nc] = cur[3] + installed;
                que.offer(new int[] { nr, nc, d, cur[3] + installed });
            }
        }

        return min;
    }

    public static boolean isOutRange(int r, int c){
        return (r < 0 || c < 0 || r >= N || c >= M);
    }
}