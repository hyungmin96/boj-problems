import java.io.*;
import java.util.*;

public class Main {

    static int[][] dirs = {{ -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        int[][] dp = new int[n][m];

        for(int i = 0; i < n; i ++) Arrays.fill(dp[i], -1);
        for (int i = 0; i < n; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j ++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(dfs(0, 0, n, m, map, dp));
    }
    
    public static int dfs(int r, int c, int n, int m, int[][] map, int[][] dp){
        if(r == n - 1 && c == m - 1){
            return 1;
        }

        if(dp[r][c] != -1) return dp[r][c];
        dp[r][c] = 0;
        for(int d = 0; d < 4; d ++){
            int nr = r + dirs[d][0];
            int nc = c + dirs[d][1];
            if(!isRange(nr, nc, n, m) || map[r][c] <= map[nr][nc]) continue;
            dp[r][c] += dfs(nr, nc, n, m, map, dp);
        }

        return dp[r][c];
    }

    public static boolean isRange(int r, int c, int n, int m){
        return !(r < 0 || c < 0 || r >= n || c >= m);
    }
}