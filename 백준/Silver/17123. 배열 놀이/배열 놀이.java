import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    StringBuilder sb = new StringBuilder();

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for(int i = 0; i < tc; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            // 0 : row
            // 1 : col
            int[][] cur = new int[2][n];
            int[][] sum = new int[2][n];

            int[][] map = new int[n][n];

            for(int j = 0; j < n; j ++){
                st = new StringTokenizer(br.readLine(), " ");
                for(int k = 0; k < n; k ++){
                    map[j][k] = Integer.parseInt(st.nextToken());
                    
                    cur[0][j] += map[j][k];
                    cur[1][k] += map[j][k];
                }
            }
            
            for(int j = 0; j < m; j ++){
                st = new StringTokenizer(br.readLine(), " ");

                int r1 = Integer.parseInt(st.nextToken()) - 1;
                int c1 = Integer.parseInt(st.nextToken()) - 1;
                int r2 = Integer.parseInt(st.nextToken()) - 1;
                int c2 = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken());

                for(int k = r1; k <= r2; k ++){
                    sum[0][k] += (c2 - c1 + 1) * v;
                }

                for(int k = c1; k <= c2; k ++){
                    sum[1][k] += (r2 - r1 + 1) * v;
                }
            }

            for(int j = n - 1; j >= 0; j --){
                cur[0][j] += sum[0][j];
                cur[1][j] += sum[1][j];
            }

            for(int k = 0; k < 2; k ++){
                for(int j = 0; j < n; j ++){
                    sb.append(cur[k][j] + " ");
                }
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}