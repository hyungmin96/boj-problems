import java.io.*;
import java.util.*;

public class Main {

    static int[][] dp;
    static boolean[] check;
    static ArrayList<Integer>[] vertex;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        dp = new int[n + 1][2];
        check = new boolean[n + 1];
        vertex = new ArrayList[n + 1];

        for(int i = 1; i <= n; i ++) vertex[i] = new ArrayList<>();
        for(int i = 0; i < n - 1; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            vertex[s].add(e);
            vertex[e].add(s);
        }

        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    public static void dfs(int node){
        check[node] = true;
        dp[node][0] = 0;
        dp[node][1] = 1;
        for(int next : vertex[node]){
            if(check[next]) continue;
            dfs(next);
            dp[node][0] += dp[next][1];
            dp[node][1] += Math.min(dp[next][0], dp[next][1]);
        }       
    }
}