import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static char[][] map;
    static int[] red = new int[] { -1, - 1 };
    static int[] blue = new int[] { -1, -1 };
    static int[][] dirs = {{ -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }};
    static int answer = 987654321;
    static int[][][] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        check = new int[N][M][2];

        for(int r = 0; r < N; r ++){
            String str = br.readLine();
            for(int c = 0; c < M; c ++){
                char ch = str.charAt(c);
                map[r][c] = ch;
                if(ch == 'o'){
                    map[r][c] = '.';
                    if(red[0] == -1) red = new int[] { r, c };
                    else blue = new int[] { r, c };
                }
            }
        }

        // check[red[0]][red[1]][0] = 1;
        // check[blue[0]][blue[1]][1] = 1;
        backtracking(0, red[0], red[1], blue[0], blue[1], true, true);
        System.out.println(answer == 987654321 ? -1 : answer);
    }

    public static void backtracking(int depth, int rr, int rc, int br, int bc, boolean red_flag, boolean blue_flag){
        if(depth > 10){
            return;
        }

        if((!red_flag && blue_flag) || (!blue_flag && red_flag)){
            answer = Math.min(answer, depth);
            return;
        }

        for(int d = 0; d < 4; d ++){
            int r_nr = rr + dirs[d][0];
            int r_nc = rc + dirs[d][1];
            int b_nr = br + dirs[d][0];
            int b_nc = bc + dirs[d][1];


            boolean r_flag = red_flag;
            boolean b_flag = blue_flag;
            if(r_nr < 0 || r_nc < 0 || r_nr >= N || r_nc >= M) {
                r_flag = false;
            }
            if(b_nr < 0 || b_nc < 0 || b_nr >= N || b_nc >= M) {
                b_flag = false;
            }

            if(r_flag){
                if(check[r_nr][r_nc][0] == 0 && map[r_nr][r_nc] != '#'){
                    // check[r_nr][r_nc][0] = 1;
                }else{
                    r_nr = rr;
                    r_nc = rc;
                }
            }

            if(b_flag){
                if(check[b_nr][b_nc][1] == 0 && map[b_nr][b_nc] != '#'){
                    // check[b_nr][b_nc][1] = 1;
                }else{
                    b_nr = br;
                    b_nc = bc;
                }
            }
            
            if(!r_flag && !b_flag) continue;
            backtracking(depth + 1, r_nr, r_nc, b_nr, b_nc, r_flag, b_flag);
            if(isRange(r_nr, r_nc)) check[r_nr][r_nc][0] = 0;
            if(isRange(b_nr, b_nc)) check[b_nr][b_nc][1] = 0;
        }
    }

    public static boolean isRange(int r, int c){
        return !(r < 0 || c < 0 || r >= N || c >= M);
    }
}
