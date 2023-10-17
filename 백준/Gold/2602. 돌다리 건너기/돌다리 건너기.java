import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException{
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    String s, s1, s2;

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s = br.readLine();
        s1 = br.readLine();
        s2 = br.readLine();

        int[][][] dp = new int[2][s.length() + 1][s1.length() + 1];
        for(int i = 0; i < 2; i ++)
            for(int j = 0; j < s.length(); j ++)
                Arrays.fill(dp[i][j], -1);

        int angel = dfs(0, 0, 0, dp);
        int devil = dfs(0, 0, 1, dp);

        System.out.println(angel + devil);
    }

    public int dfs(int depth, int idx, int flag, int[][][] dp){
        if(depth == s.length()){
            return 1;
        }

        if(dp[flag][depth][idx] != -1){
            return dp[flag][depth][idx];
        }

        dp[flag][depth][idx] = 0;
        if(flag == 0){
            for(int i = idx; i < s1.length(); i ++){
                if(s.charAt(depth) == s1.charAt(i)){
                    dp[flag][depth][idx] += dfs(depth + 1, i + 1, 1, dp);
                }
            }
        }else{
            for(int i = idx; i < s2.length(); i ++){
                if(s.charAt(depth) == s2.charAt(i)){
                    dp[flag][depth][idx] += dfs(depth + 1, i + 1, 0, dp);
                }
            }
        }

        return dp[flag][depth][idx];
    }
}

