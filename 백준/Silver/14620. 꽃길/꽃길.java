import java.io.*;
import java.util.*;

public class Main {

    static int answer = 987654321;
    static boolean[][] visited;
    static int[][] dirs = {{ -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];

        visited = new boolean[n][n];
        for(int i = 0; i < n; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; j ++){
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
            }
        }

        dfs(n, 0, new int[3][2], map);
        System.out.println(answer);
    }

    public static void dfs(int n, int depth, int[][] pos, int[][] map){
        if(depth == 3){
            int temp = 0;
            boolean flag = true;
            boolean[][] put = new boolean[n][n];
            for(int i = 0; i < depth; i ++){
                put[pos[i][0]][pos[i][1]] = true;
                int ret = check(n, pos[i][0], pos[i][1], put, map);
                if(ret == -1){
                    flag = false;
                    break;
                }else{
                    temp += ret + map[pos[i][0]][pos[i][1]];
                    if(temp >= answer) return;
                }
            }

            if(flag) answer = Math.min(answer, temp);
            return;
        }

        for(int i = 1; i < n - 1; i ++){
            for(int j = 1; j < n - 1; j ++){
                if(visited[i][j]) continue;
                visited[i][j] = true;
                pos[depth] = new int[] { i, j };
                dfs(n, depth + 1, pos, map);
                visited[i][j] = false;
            }
        }
    }

    public static int check(int n, int r, int c, boolean[][] put, int[][] map){
        int ret = 0;
        for(int[] d : dirs){
            int nr = r + d[0];
            int nc = c + d[1];
            if(!isRange(nr, nc, n) || put[nr][nc]) return -1;
            put[nr][nc] = true;
            ret += map[nr][nc];
        }
        return ret;
    }

    public static boolean isRange(int nr, int nc, int n){
        return !(nr < 0 || nc < 0 || nr >= n || nc >= n);
    }
}