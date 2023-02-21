import java.io.*;
import java.util.*;

public class Main {

    static class Info {
        int r, c, hp, d, time;
        boolean state;

        public Info(int r, int c, int hp, int d, int time, boolean state) {
            this.r = r;
            this.c = c;
            this.hp = hp;
            this.d = d;
            this.time = time;
            this.state = state;
        }
    }

    static int N, H, D;
    static int[] start, end;
    static char[][] map;
    static int[][] dirs = {
            { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = str.charAt(j);
                map[i][j] = c;
                if (c == 'E')
                    end = new int[] { i, j };
                else if (c == 'S')
                    start = new int[] { i, j };
            }
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        // 내구도, 위치 R, 위치 C
        int[][] check = new int[N][N];
        Queue<Info> que = new LinkedList<>();
        que.offer(new Info(start[0], start[1], H, 0, 0, false));
        check[start[0]][start[1]] = H;

        while (!que.isEmpty()) {
            Info cur = que.poll();
            for (int[] d : dirs) {

                int hp = cur.hp;
                int dp = cur.d;
                int time = cur.time;
                boolean state = cur.state;

                int nr = cur.r + d[0];
                int nc = cur.c + d[1];
                if (!isRange(nr, nc))
                    continue;

                if(map[nr][nc] == 'E') return cur.time + 1;

                if (map[nr][nc] == 'U') {
                    dp = D;
                    state = true;
                }
                
                if (state) {
                    dp --;
                    if(dp <= 0) state = false;
                } else {
                    hp --;
                }
                
                if(hp == 0) continue;
                if(check[nr][nc] < hp){
                    check[nr][nc] = hp;
                    que.offer(new Info(nr, nc, hp, dp, time + 1, state));
                }
            }
        }
        return -1;
    }

    public static boolean isRange(int r, int c) {
        return !(r < 0 || c < 0 || r >= N || c >= N);
    }
}