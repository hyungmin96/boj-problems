import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int N, M;
    final long INF = 9876543210L;
    long[][] dp;
    ArrayList<int[]>[] vertex;
    StringBuilder sb = new StringBuilder();

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp = new long[N + 1][N + 1];
        vertex = new ArrayList[N + 1];
        for(int i = 1; i <= N; i ++) {
            Arrays.fill(dp[i], INF);
            vertex[i] = new ArrayList<>();
        }

        for(int i = 1; i <= N; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 1; j <= N; j ++){
                int t = Integer.parseInt(st.nextToken());
                if(t > 0){
                    vertex[i].add(new int[] { j, t });
                    dp[i][j] = t;
                }
            }
        }

        floyd();
        for(int i = 0; i < M; i ++){
            st = new StringTokenizer(br.readLine(), " ");

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            if(dp[s][e] > t){
                sb.append("Stay here\n");
            }else{
                sb.append("Enjoy other party\n");
            }
        }
        System.out.println(sb.toString());
    }

    public void floyd(){
        for(int i = 1; i <= N; i ++){
            for(int j = 1; j <= N; j ++){
                if(i == j) continue;
                for(int k = 1; k <= N; k ++){
                    if(i == k || j == k) continue;
                    dp[j][k] = Math.min(dp[j][k], dp[j][i] + dp[i][k]);
                }
            }
        }
    }
}