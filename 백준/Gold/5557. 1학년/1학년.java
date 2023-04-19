import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static long answer = 0, cnt = 0;
    static int[] arr;
    static long[][] dp;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new long[N + 1][21];
        arr = new int[N];

        for(int i = 0; i < N; i ++)
            Arrays.fill(dp[i], -1);

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i ++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1, arr[0]);
        System.out.println(dp[1][arr[0]]);
    }

    public static long dfs(int depth, int cur){
        if(cur < 0 || cur > 20) return 0;
        if(depth == N - 1 && cur == arr[N - 1]) return 1;
        if(dp[depth][cur] != -1) return dp[depth][cur];
        dp[depth][cur] = 0;

        dp[depth][cur] += dfs(depth + 1, cur + arr[depth]);
        dp[depth][cur] += dfs(depth + 1, cur - arr[depth]);

        return dp[depth][cur];
    }
}