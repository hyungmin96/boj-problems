import java.io.*;
import java.util.*;

public class Main {

    static int[][] dirs = {
        { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];

        for(int i = 0; i < n; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; j ++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long answer = 0;
        long[][] dp = new long[n][n];
        for(int i = 0; i < n; i ++) Arrays.fill(dp[i], -1);
        for(int r = 0; r < n; r ++){
            for(int c = 0; c < n; c ++){
                if(dp[r][c] == -1)
                    dfs(n, 0, r, c, dp, map);
                answer = Math.max(dp[r][c], answer);
            }
        }
        System.out.println(answer);
    }

    public static long dfs(int n, int depth, int r, int c, long[][] dp, int[][] map){
        if(dp[r][c] != -1) return dp[r][c];
        dp[r][c] = 1;

        for(int d = 0; d < 4; d ++){
            int temp = 0;
            int nr = r + dirs[d][0];
            int nc = c + dirs[d][1];
            if(nr < 0 || nc < 0 || nr >= n || nc >= n || map[r][c] >= map[nr][nc]) continue;
            temp += (dfs(n, depth + 1, nr, nc, dp, map) + 1);
            dp[r][c] = Math.max(dp[r][c], temp);
        }
        return dp[r][c];
    }
}