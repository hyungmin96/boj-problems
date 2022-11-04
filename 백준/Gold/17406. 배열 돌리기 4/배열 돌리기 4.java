import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K, answer = Integer.MAX_VALUE;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        check = new boolean[K];
        int[][] map = new int[N][M];

        for(int r = 0; r < N; r ++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int c = 0; c < M; c ++){
                int num = Integer.parseInt(st.nextToken());
                map[r][c] = num;
            }
        }

        int[][] cmd = new int[K][3];
        for(int i = 0; i < K; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            cmd[i][0] = r;
            cmd[i][1] = c;
            cmd[i][2] = k;
        }

        dfs(0, new int[K], cmd, map);
        System.out.println(answer);
    }

    public static void dfs(int depth, int[] order, int[][] cmd, int[][] map){
        if(depth == K){
            int[][] temp = copy(map);
            answer = Math.min(rotate(order, cmd, temp), answer);
            return;
        }

        for(int i = 0; i < K; i ++){
            if(check[i]) continue;
            check[i] = true;
            order[depth] = i;
            dfs(depth + 1, order, cmd, map);
            check[i] = false;
        }
    }

    public static int rotate(int[] order, int[][] cmd, int[][] temp){
        for(int i : order){
            int r = cmd[i][0];
            int c = cmd[i][1];
            int s = cmd[i][2];

            move(r - s - 1, c - s - 1, r + s - 1, c + s - 1, temp);
        }

        int ret = 987654321;
        for(int r = 0; r < N; r ++){
            int row_total = 0;
            for(int c = 0; c < M; c ++){
                row_total += temp[r][c];
            }
            ret = Math.min(ret, row_total);
        }

        return ret;
    }

    public static void move(int r, int c, int rs, int cs, int[][] board){
        if(r == rs && c == cs){
            return;
        }
        int[] temp = new int[3];
        temp[0] = board[r][cs];
        temp[1] = board[rs][cs];
        temp[2] = board[rs][c];

        for(int i = cs; i > c; i --){
            board[r][i] = board[r][i - 1];
        }

        for(int i = rs; i > r; i --){
            if(i == r + 1) board[i][cs] = temp[0];
            else board[i][cs] = board[i - 1][cs];
        }

        for(int i = c; i < cs; i ++){
            if(i == cs - 1) board[rs][i] = temp[1];
            else board[rs][i] = board[rs][i + 1];
        }

        for(int i = r; i < rs; i ++){
            if(i == rs - 1) board[i][c] = temp[2];
            else board[i][c] = board[i + 1][c];
        }
        move(r + 1, c + 1, rs - 1, cs - 1, board);
    }

    public static int[][] copy(int[][] map){
        int[][] temp = new int[N][M];
        for(int r = 0; r < N; r ++){
            for (int c = 0; c < M; c ++){
                temp[r][c] = map[r][c];
            }
        }
        return temp;
    }
}