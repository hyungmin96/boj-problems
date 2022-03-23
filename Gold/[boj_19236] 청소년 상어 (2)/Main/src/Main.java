import java.io.*;
import java.util.StringTokenizer;
public class Main {

    private static int[] dx = new int[] {-1,-1, 0, 1, 1, 1, 0,-1 };
    private static int[] dy = new int[] { 0,-1,-1,-1, 0, 1, 1, 1 };
    private static int[][] tb = new int[4][4];
    private static Fish[] tf = new Fish[17];
    private static int answer = 0;

    public static class Fish{
        int x, y, id, dir;
        boolean empty = false;
        boolean shark = false;
        public Fish(int x, int y, int id, int dir, boolean empty, boolean shark){
            this.x = x;
            this.y = y;
            this.id = id;
            this.dir = dir;
            this.empty = empty;
            this.shark = shark;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 4; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 4; j ++){
                int id = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;
                tb[i][j] = id;
                tf[id] = new Fish(i, j, id, dir, false, false);
            }
        }

        int shark_id = tb[0][0];
        int d = tf[shark_id].dir;
        tf[shark_id].shark = true;
        tf[shark_id].empty = true;
        solution(0, 0, d, shark_id);
        System.out.println(answer);
    }

    public static void solution(int x, int y, int d, int sum){
        
        answer = (sum > answer) ? sum : answer;
        int[][] board = copyBoard(tb);
        Fish[] fishs = copyFish(tf);
        fishMove();
        
        int id = tb[x][y];
        for(int step = 1; step <= 3; step ++){
            int nr = x + dx[d] * step;
            int nc = y + dy[d] * step;
            if(nr >= 0 && nc >= 0 && nr < 4 && nc < 4 && !tf[tb[nr][nc]].empty){
                int nd = tf[tb[nr][nc]].dir;
                tf[id].shark = false;
                tf[id].empty = true;
                tf[tb[nr][nc]].shark = true;
                tf[tb[nr][nc]].empty = true;
                solution(nr, nc, nd, sum + tb[nr][nc]);
                tf[id].shark = true;
                tf[tb[nr][nc]].shark = false;
                tf[tb[nr][nc]].empty = false;
            }
        }

        tb = copyBoard(board);
        tf = copyFish(fishs);
    }

    public static void fishMove(){
        for(int i = 1; i < tf.length; i ++){
            if(!tf[i].empty && !tf[i].shark){
                for(int j = 0; j < dx.length; j ++){
                    int d = (tf[i].dir + j) % dx.length;
                    int nr = tf[i].x + dx[d];
                    int nc = tf[i].y + dy[d];
                    if(nr >= 0 && nc >= 0 && nr < 4 && nc < 4 && !tf[tb[nr][nc]].shark){
                        tf[i].dir = d;
                        tf[tb[nr][nc]].x = tf[i].x;
                        tf[tb[nr][nc]].y = tf[i].y;
                        tb[tf[i].x][tf[i].y] = tb[nr][nc];
                        tb[nr][nc] = tf[i].id;
                        tf[i].x = nr;
                        tf[i].y = nc;
                        break;
                    }
                }
            }
        }
    }

    public static int[][] copyBoard(int[][] board){
        int[][] temp = new int[4][4];
        for(int i = 0; i < 4; i ++){
            for(int j = 0; j < 4; j ++){
                temp[i][j] = board[i][j];
            }
        }
        return temp;
    }

    public static Fish[] copyFish(Fish[] fishs){
        Fish[] temp = new Fish[17];
        for(int i = 1; i < fishs.length; i ++){
            temp[i] = new Fish(fishs[i].x, fishs[i].y, fishs[i].id, fishs[i].dir, fishs[i].empty, fishs[i].shark);
        }
        return temp;
    }

}
