import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int N;
    int[] arr = new int[3];
    int[][][] dp = new int[61][61][61];

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i ++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < 61; i ++){
            for(int j = 0; j < 61; j ++){
                Arrays.fill(dp[i][j], -1);
            }
        }

        System.out.println(dfs(arr[0], arr[1], arr[2]));
    }

    public void solve(){
        int answer = 0;
        System.out.println(answer);
    }

    public int dfs(int s1, int s2, int s3){
        s1 = Math.max(s1, 0);
        s2 = Math.max(s2, 0);
        s3 = Math.max(s3, 0);
        if(s1 <= 0 && s2 <= 0 && s3 <= 0){
            return 0;
        }

        if(dp[s1][s2][s3] != -1){
            return dp[s1][s2][s3];
        }

        dp[s1][s2][s3] = 987654321;
        dp[s1][s2][s3] = Math.min(dp[s1][s2][s3], Math.min(dfs(s1 - 9, s2 - 3, s3 - 1) + 1, dfs(s1 - 9, s2 - 1, s3 - 3) + 1));
        dp[s1][s2][s3] = Math.min(dp[s1][s2][s3], Math.min(dfs(s1 - 3, s2 - 9, s3 - 1) + 1, dfs(s1 - 3, s2 - 1, s3 - 9) + 1));
        dp[s1][s2][s3] = Math.min(dp[s1][s2][s3], Math.min(dfs(s1 - 1, s2 - 9, s3 - 3) + 1, dfs(s1 - 1, s2 - 3, s3 - 9) + 1));

        return dp[s1][s2][s3];
    }
}