import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    public int R, C, T;
    public int[][] map;
    public int[] air1 = null, air2 = null;

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];

        for(int r = 0; r < R; r ++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int c = 0; c < C; c ++){
                int num = Integer.parseInt(st.nextToken());
                map[r][c] = num;
                if(num == -1 && air1 == null){
                    air1 = new int[] { r, c, };
                }else if(air1 != null && num == -1){
                    air2 = new int[] { r, c, };
                }
            }
        }

        for(int t = 0; t < T; t ++){
            spread();
            top_rotate();
            bottom_rotate();
        }
        System.out.println(count());
    }

    public long count(){
        long ret = 0;
        for(int r = 0; r < R; r ++){
            for(int c = 0; c < C; c ++){
                if(map[r][c] > 0) ret += map[r][c];
            }
        }
        return ret;
    }

    public void top_rotate(){
        int pos_r = air1[0];
        int pos_c = air1[1];

        for(int i = pos_r - 1; i > 0; i --){
            map[i][0] = map[i - 1][0];
        }

        for(int i = 0; i < C - 1; i ++){
            map[0][i] = map[0][i + 1];
        }

        for(int i = 0; i < pos_r; i ++){
            map[i][C - 1] = map[i + 1][C - 1];
        }

        for(int i = C - 1; i > 1; i --){
            map[pos_r][i] = map[pos_r][i - 1];
        }
        map[pos_r][pos_c + 1] = 0;
    }

    public void bottom_rotate(){
        int pos_r = air2[0];
        int pos_c = air2[1];

        for(int i = pos_r + 1; i < R - 1; i ++){
            map[i][0] = map[i + 1][0];
        }

        for(int i = 0; i < C - 1; i ++){
            map[R - 1][i] = map[R - 1][i + 1];
        }

        for(int i = R - 1; i > pos_r; i --){
            map[i][C - 1] = map[i - 1][C - 1];
        }

        for(int i = C - 1; i > 1; i --){
            map[pos_r][i] = map[pos_r][i - 1];
        }
        map[pos_r][pos_c + 1] = 0;
    }

    public void spread(){
        int[][] dirs = {
            { -1, 0 },
            { 0, 1 },
            { 1, 0 },
            { 0, -1 }
        };
       
        int[][] temp = new int[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] > 0) {
                    int cnt = 0;
                    int val = map[r][c] / 5;
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dirs[d][0];
                        int nc = c + dirs[d][1];
                        if (nr < 0 || nc < 0 || nr >= R || nc >= C || map[nr][nc] == -1)
                            continue;
                        cnt++;
                        temp[nr][nc] += val;
                    }
                    temp[r][c] -= (cnt * val);
                }
            }
        }

        for(int r = 0; r < R; r ++){
            for(int c = 0; c < C; c ++){
                map[r][c] += temp[r][c];
            }
        }
    }
}