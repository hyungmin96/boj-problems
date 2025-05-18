import java.io.*;
import java.util.*;
public class Main {

    static int n;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String b1  = br.readLine();
        String b2  = br.readLine();
        n = b1.length();

        char[][] arr = new char[2][n];
        int[][][] dp = new int[s.length() + 1][2][n];

        arr[0] = b1.toCharArray();
        arr[1] = b2.toCharArray();
        
        int answer = 0;
        initDp(s, dp);
        answer += dfs(0, 1, 0, s, arr, dp);
        initDp(s, dp);
        answer += dfs(0, 0, 0, s, arr, dp);

        System.out.println(answer);
    }

    public static int dfs(int depth, int b_idx, int idx, String s, char[][] arr, int[][][] dp){
        if(depth == s.length()){
            return 1;
        }

        if(idx >= n){
            return 0;
        }

        if(dp[depth][b_idx][idx] != Integer.MAX_VALUE){
            return dp[depth][b_idx][idx];
        }

        dp[depth][b_idx][idx] = 0;
        for(int i = idx; i < n; i ++){
            char next = arr[(b_idx + 1) % 2][i];
            if(s.charAt(depth) == next){
                dp[depth][b_idx][idx] += dfs(depth + 1, (b_idx + 1) % 2, i + 1, s, arr, dp);
            }
        }

        return dp[depth][b_idx][idx];
    }

    public static void initDp(String s, int[][][] dp){
        for(int i = 0; i < s.length() + 1; i ++){
            for(int j = 0; j < 2; j ++){
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }
    }
}