import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] start, end;
    static int[][] dirs = {{ -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }};
    static boolean flag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        char[][] map = new char[N][M];
        int[][] check = new int[N][M];
        for(int i = 0; i < N; i ++){
            String str = br.readLine();
            for(int j = 0; j < M; j ++){
                char c = str.charAt(j);
                if(c == 'M') start = new int[] { i, j };
                else if(c == 'Z') end = new int[] { i, j };

                if(c != 'M' && c != '.'){
                    check[i][j] ++;
                    if(c == '+') check[i][j] ++;
                }
                map[i][j] = c;
            }
        }

        char[] ch = new char[] { '|', '+', '-', '1', '2', '3', '4' };
        for(int r = 0; r < N; r ++){
            for(int c = 0; c < M; c ++){
                if(map[r][c] == '.'){
                    for(int j = 0; j < ch.length; j ++){
                        map[r][c] = ch[j];
                        check[r][c] ++;
                        if(ch[j] == '+') check[r][c] ++;
                        for(int d = 0; d < 4; d ++){
                            int nr = start[0] + dirs[d][0];
                            int nc = start[1] + dirs[d][1];
                            
                            if(nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] == '.') continue;
                            check[nr][nc] --;
                            dfs(0, start[0] + dirs[d][0], start[1] + dirs[d][1], d, map, check);
                            if(flag){
                                System.out.println((r + 1) + " " + (c + 1) + " " + ch[j]);
                                return;
                            }
                            
                            check[nr][nc] ++;
                        }
                        check[r][c] --;
                        if(ch[j] == '+') check[r][c] --;
                    }
                    map[r][c] = '.';
                }
            }
        }
    }

    public static void dfs(int depth, int r, int c, int d, char[][] map, int[][] check){
        if(flag) return;
        if(r == end[0] && c == end[1]) {
            flag = true;
            for(int a = 0; a < N; a ++){
                for(int b = 0; b < M; b ++){
                    if(check[a][b] > 0) {
                        flag = false;
                        break;
                    }
                }
            }
            return;
        }
        int cur_d = getDirection(map[r][c], d);
        if(cur_d == -1) return;

        int nr = r + dirs[cur_d][0];
        int nc = c + dirs[cur_d][1];
        if(nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] == '.') return;
        if(check[nr][nc] > 0){
            check[nr][nc] --;
            dfs(depth + 1, nr, nc, cur_d, map, check);
            check[nr][nc] ++;
        }
    }

    public static int getDirection(char c, int d){
        if(c == '1'){
            if(d == 0) return 1;
            if(d == 3) return 2;
        }else if(c == '2'){
            if(d == 2) return 1;
            if(d == 3) return 0;
        }else if(c == '3'){
            if(d == 1) return 0;
            if(d == 2) return 3;
        }else if(c == '4'){
            if(d == 1) return 2;
            if(d == 0) return 3;
        }else if(c == '|'){
            if(d == 2 || d == 0) return d;
        }else if(c == '-'){
            if(d == 1 || d == 3) return d;
        }else if(c == '+'){
            return d;
        }
        return - 1;
    }
}