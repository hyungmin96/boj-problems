import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
public class Main {

    private static int N, M, K;
    private static Shark[][] board;
    private static Shark[] sharks;
    private static ArrayList<int[][]> priorities = new ArrayList<>();
    private static int[] dx = new int[] {-1, 1, 0, 0 };
    private static int[] dy = new int[] { 0, 0,-1, 1 };

    public static class Shark{
        int id, x, y, dir, limit;
        boolean state;
        public Shark(int id, int x, int y, int limit, int dir, boolean state){
            this.id = id;
            this.x = x;
            this.y = y;
            this.limit = limit;
            this.dir = dir;
            this.state = state;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new Shark[N][N];
        sharks = new Shark[M];
        priorities = new ArrayList<>();

        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine());
            int col = 0;
            while(st.hasMoreTokens()){
                int val = Integer.parseInt(st.nextToken());
                if(val != 0){
                    sharks[val - 1] = new Shark(val - 1, i, col, K, -1, true);
                    board[i][col] = new Shark(val - 1, i, col, K, 0, true);
                }
                else
                    board[i][col] = new Shark(-1, i, col, 0, -1, false);
                col++;
            }
        }

        st = new StringTokenizer(br.readLine());
        int idx = 0;
        while(st.hasMoreTokens()){
            sharks[idx++].dir = Integer.parseInt(st.nextToken()) - 1;
        }

        for(int i = 0; i < M; i ++){
            int[][] direction_arr = new int[4][4];
            for(int j = 0; j < 4; j ++){
                st = new StringTokenizer(br.readLine());
                for(int k = 0; k < 4; k ++){
                    direction_arr[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            priorities.add(direction_arr);
        }
        System.out.println(solution());
    }

    public static int solution(){
        for(int i = 0; i <= 1000; i ++){
            if(check()){
                return i;
            }else{
                sharkMove();
                reduceTrace();
            }
        }
        return -1;
    }

    public static void sharkMove() {

        int[][] tempBoard = new int[N][N];
        for (int i = 0; i < sharks.length; i++) {
            if (sharks[i] != null) {
                Shark c = sharks[i];
                boolean flag = false;
                for (int j = 0; j < dx.length; j++) {
                    int d = priorities.get(c.id)[c.dir][j] - 1;
                    int nr = c.x + dx[d];
                    int nc = c.y + dy[d];
                    if (nr >= 0 && nc >= 0 && nr < N && nc < N && !board[nr][nc].state && board[nr][nc].limit <= 0) {
                        flag = true;
                        tempBoard[nr][nc] ++;
                        board[c.x][c.y] = new Shark(c.id, c.x, c.y, c.limit, -1, false);
                        c.x = nr;
                        c.y = nc;
                        c.dir = d;
                        break;
                    }
                }

                if(!flag && sharks[i] != null){
                    for (int j = 0; j < dx.length; j++) {
                        int d = priorities.get(c.id)[c.dir][j] - 1;
                        int nr = c.x + dx[d];
                        int nc = c.y + dy[d];
                        if (nr >= 0 && nc >= 0 && nr < N && nc < N && board[nr][nc].id == c.id) {
                            tempBoard[nr][nc] ++;
                            board[c.x][c.y] = new Shark(c.id, c.x, c.y, c.limit, -1, false);
                            c.x = nr;
                            c.y = nc;
                            c.dir = d;
                            break;
                        }
                    }
                }

            }
        }

        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j ++){
                if(tempBoard[i][j] > 1){
                    for(int k = 0; k < sharks.length - 1; k ++){
                        Shark temp = sharks[k];
                        for(int l = k + 1; l < sharks.length; l ++){
                            if(temp != null && sharks[l] != null && temp.x == sharks[l].x && temp.y == sharks[l].y){
                                if(temp.id > sharks[l].id){
                                    sharks[k] = null;
                                }else{
                                    sharks[l] = null;
                                }
                            }
                        }
                    }
                }
            }
        }

        for(int i = 0; i < sharks.length; i ++){
            if(sharks[i] != null){
                int r = sharks[i].x;
                int c = sharks[i].y;
                board[r][c] = new Shark(sharks[i].id, r, c, 4, sharks[i].dir, true);
            }
        }
    }

    public static void reduceTrace(){
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j ++){
                if(!board[i][j].state && board[i][j].limit > 0){
                    board[i][j].limit -= 1;
                }
            }
        }
    }

    public static boolean check(){
        int cnt = 0;
        for(int i = 0; i < sharks.length; i ++){
            if(sharks[i] == null){
                cnt ++;
            }
        }
        return (sharks.length - cnt == 1) ? true : false;
    }
}
