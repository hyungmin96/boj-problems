import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class App {

    private static int R, C, M;
    private static int[][] board;
    private static int[] dx = new int[] {-1, 1, 0, 0 };
    private static int[] dy = new int[] { 0, 0, 1,-1 };

    public static class Shark{
        int r, c, s, d, z;
        public Shark(int r, int c, int s, int d, int z){
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken()) + 1;
        M = Integer.parseInt(st.nextToken());

        Shark[] sharks = new Shark[M];
        board = new int[R][C];

        for(int i = 0; i < M; i ++){
            st = new StringTokenizer(br.readLine());

            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken()) - 1;

            sharks[i] = new Shark(row, col, 
                        Integer.parseInt(st.nextToken()), 
                        Integer.parseInt(st.nextToken()), 
                        Integer.parseInt(st.nextToken()));

            board[row][col] = i;
        }
        System.out.println(solution(sharks));
    }

    public static int solution(Shark[] sharks) {
        int answer = 0;
        for(int i = 1; i < C; i ++){
            for(int j = 0; j < R; j ++){
                if(board[j][i] > 0){
                    answer += sharks[board[j][i]].s;
                    board[j][i] = 0;
                    sharks[board[j][i]] = null;
                    break;
                }
            }
            moveShark(sharks);
        }
        return answer;
    }

    public static void moveShark(Shark[] sharks){
        board = new int[R][C];
        for(int k = 0; k < sharks.length; k ++){
            if(sharks[k] != null){
                int cr = sharks[k].r;
                int cc = sharks[k].c;
                int dir = sharks[k].d;
                for(int l = 0; l < sharks[k].z; l ++){
                    cr += dx[sharks[k].d - 1];
                    cc += dy[sharks[k].d - 1];
                    if(cr < 0 || cc < 0 || cr >= R || cc >= C){
                        
                    }
                }
            }
        }
    }

}