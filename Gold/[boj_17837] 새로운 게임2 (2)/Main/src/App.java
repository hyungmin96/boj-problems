import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;
public class App {

    private static int N, K;
    private static int[][] board;
    public static Chess[][] chessBoard;
    private static int[] dx = new int[] {-0, 0,-1, 1 };
    private static int[] dy = new int[] { 1,-1, 0, 0 };

    public static class Chess{
        int r, c, d;
        Deque<Chess> que = new LinkedList<>();
        public Chess(int r, int c, int d){
            this.r = r;
            this.c = c;
            this.d = d;
            this.que.offer(this);
        }

        public void part(Chess c){
            Chess item = que.poll();
            while(!item.equals(c)){
                c.que.offer(item);
            }
            c.que.offer(item);
        }

        public void put(Chess c){
            while(!c.que.isEmpty()){
                this.que.offer(c.que.poll());
            }
        }

        public void swap(){
            Deque<Chess> temp = new LinkedList<>();
            while(!que.isEmpty()){
                temp.offer(que.getLast());
            }
            this.que = temp;
        }

        public void changeDirection(){
            if(d == 0) d = 1;
            else if(d == 1) d = 0;
            else if(d == 2) d = 3;
            else d = 2;
        }

        public void setR(int r){
            this.r = r;
        }

        public void setC(int c){
            this.c = c;
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
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
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
                    chesses[i].changeDirection();
                    nr += dx[d];
                    nc += dy[d];
                    if(board[nr + dx[d]][nc + dy[d]] != 2){
                        nr += dx[d];
                        nc += dy[d];
                    }
                }

                chesses[i].setR(nr);
                chesses[i].setC(nc);
                chessBoard[nr][nc].part(chesses[i]);
                if(board[nr][nc] == 0){
                    if(chessBoard[nr][nc] == null){
                        chessBoard[nr][nc] = chesses[i];
                    }else{
                        chessBoard[nr][nc].put(chesses[i]);
                    }
                }else if(board[nr][nc] == 1){
                    chesses[i].swap();
                    if(chessBoard[nr][nc] == null){
                        chessBoard[nr][nc] = chesses[i];
                    }else{
                        chessBoard[nr][nc].put(chesses[i]);
                    }
                }

                if(chessBoard[nr][nc].que.size() == 4) return time;

            }
        }
        return -1;
    }
}
