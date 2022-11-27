import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Integer>[] vertex;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        
        int[] wack = new int[m];
        HashSet<Integer> wacks = new HashSet<>();
        for(int i = 0; i < m; i ++) {
            int num = Integer.parseInt(st.nextToken());
            wack[i] = num;
            wacks.add(num);
        }

        dfs(0, m, 0, 0, wack, wacks);

        int[] dp = new int[n + 1];
        Arrays.fill(dp, 987654321);
        for(int w : wacks) if(n >= w) dp[w] = 1;
      
        for(int i = 1; i <= n; i ++){
            if(dp[i] == 1) continue;
            for(int w : wacks){
                if(i >= w && i - w > 0){
                    dp[i] = Math.min(dp[i], dp[i - w] + 1);
                }
            }
        }

        System.out.println(dp[n] == 987654321 ? -1 : dp[n]);
    }

    public static void dfs(int depth, int m, int idx, int cur, int[] wack, HashSet<Integer> wacks){
        if(depth == 2){
            wacks.add(cur);
            return;
        }

        for(int i = idx; i < m; i ++){
            dfs(depth + 1, m, i + 1, cur + wack[i], wack, wacks);
        }
    }
}