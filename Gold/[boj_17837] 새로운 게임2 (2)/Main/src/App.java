import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
public class App {

    private static int N, K;
    private static int[][] board;
    public static Chess[][] chessBoard;
    private static int[] dx = new int[] {-0, 0,-1, 1 };
    private static int[] dy = new int[] { 1,-1, 0, 0 };

    public static class Chess{
        int r, c, d;
        public Chess(int r, int c, int d){
            this.r = r;
            this.c = c;
            this.d = d;
        }

        public void changeDirection(int d){
            if(d == 0) d = 1;
            else if(d == 1) d = 0;
            else if(d == 2) d = 3;
            else d = 2;
            this.d = d;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        chessBoard = new Chess[N][N];

        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int index = 0;
            while(st.hasMoreTokens()){
                board[i][index++] = Integer.parseInt(st.nextToken());
            }
        }

        Chess[] order = new Chess[N];
        for(int k = 0; k < K; k ++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            Chess chess = new Chess(r, c, d);
            chessBoard[r][c] = chess;
            order[k] = chess;
        }

        System.out.println(solution(order));
    }

    public static int solution(Chess[] chesses){
        for(int time = 0; time <= 1000; time ++){
            for(int i = 0; i < chesses.length; i ++){
                int d = chesses[i].d;
                int nr = chesses[i].r + dx[d];
                int nc = chesses[i].c + dy[d];
                if(nr < 0 || nc < 0 || nr >= N || nc >= N || board[nr][nc] == 2){
                    chesses[i].changeDirection(d);
                    nr += dx[d];
                    nc += dy[d];
                }

            }
        }
        return -1;
    }
}
