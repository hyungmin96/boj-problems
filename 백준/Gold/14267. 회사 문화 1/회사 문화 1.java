import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Integer>[] vertex;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        vertex = new ArrayList[n + 1];
        dp = new int[n + 1];
        for(int i = 1; i <= n; i ++) vertex[i] = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i ++){
            int node = Integer.parseInt(st.nextToken());
            if(node != -1){
                vertex[node].add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i ++){
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());

            dp[node] += score;
        }

        dfs(1);

        for(int i = 1; i <= n; i ++) sb.append(dp[i] + " ");
        System.out.println(sb.toString());
    }

    public static void dfs(int start){
        for(int next : vertex[start]){
            dp[next] += dp[start];
            dfs(next);
        }
    }
}