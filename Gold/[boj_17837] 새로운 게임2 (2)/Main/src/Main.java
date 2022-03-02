import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
public class Main {

    private static int N, K;
    private static int[][] board;
    private static ArrayList<Integer>[][] chessBoard;
    private static int[] dx = new int[] { 0, 0,-1, 1 };
    private static int[] dy = new int[] { 1,-1, 0, 0 };

    public static class Chess{
        int r, c, d;
        public Chess(int r, int c, int d){
            this.r = r;
            this.c = c;
            this.d = d;
        }

        public void setR(int r){
            this.r = r;
        }

        public void setC(int c){
            this.c = c;
        }

        public void setD(int d){
            this.d = d;
        }
    }

    public static int solution(Chess[] chesses){
        for(int time = 1; time <= 1000; time ++){
            for(int i = 0; i < chesses.length; i ++){
                int d = chesses[i].d;
                int cr = chesses[i].r;
                int cc = chesses[i].c;
                int nr = chesses[i].r + dx[d];
                int nc = chesses[i].c + dy[d];
                if(nr < 0 || nc < 0 || nr >= N || nc >= N || board[nr][nc] == 2){
                    nr -= dx[d];
                    nc -= dy[d];

                    if(d == 0) d = 1;
                    else if(d == 1) d = 0;
                    else if(d == 2) d = 3;
                    else d = 2;

                    nr += dx[d];
                    nc += dy[d];
                    chesses[i].setD(d);
                    if(nr < 0 || nc < 0 || nr >= N || nc >= N || board[nr][nc] == 2){
                        nr -= dx[d];
                        nc -= dy[d];
                        continue;
                    }
                }
                
                move(nr, nc, d, cr, cc, chesses, i);
                if(chessBoard[nr][nc].size() >= 4) return time;
            }
        }
        return -1;
    }

    public static void move(int nr, int nc, int d, int cr, int cc, Chess[] chesses, int index){
        ArrayList<Integer> part = getPart(index, cr, cc);
        
        if(board[nr][nc] == 0){
            for(int i = part.size() - 1; i >= 0; i --){
                chesses[part.get(i)].setR(nr);
                chesses[part.get(i)].setC(nc);
                chessBoard[nr][nc].add(0, part.get(i));
            }
        }else if(board[nr][nc] == 1){
            for(int i = 0; i < part.size(); i ++){
                chesses[part.get(i)].setR(nr);
                chesses[part.get(i)].setC(nc);
                chessBoard[nr][nc].add(0, part.get(i));
            }
        }
    }

    public static ArrayList<Integer> getPart(int index, int row, int col){
        ArrayList<Integer> temp = new ArrayList<>();
        while(chessBoard[row][col].size() != 0){
            int item = chessBoard[row][col].get(0);
            temp.add(item);
            chessBoard[row][col].remove(0);
            if(item == index) break;
        }
        return temp;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        chessBoard = new ArrayList[N][N];

        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int index = 0;
            while(st.hasMoreTokens()){
                board[i][index] = Integer.parseInt(st.nextToken());
                chessBoard[i][index] = new ArrayList<>();
                index ++;
            }
        }

        Chess[] order = new Chess[K];
        for(int k = 0; k < K; k ++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            order[k] = new Chess(r, c, d);
            chessBoard[r][c].add(k);
        }

        System.out.println(solution(order));
    }
}
