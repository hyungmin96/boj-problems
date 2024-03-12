import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int[][] dp;
    char[][] arr;

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        String s1 = br.readLine();
        String s2 = br.readLine();

        arr = new char[2][s1.length()];
        for(int j = 0; j < s1.length(); j ++){
            arr[0][j] = s1.charAt(j);
        }

        for(int j = 0; j < s2.length(); j ++){
            arr[1][j] = s2.charAt(j);
        }

        int start_s1 = solve(0, s, s1, s2);
        int start_s2 = solve(1, s, s1, s2);

        System.out.println(start_s1 + start_s2);
    }

    public int solve(int start, String s, String s1, String s2){
        dp = new int[s.length() + 1][s1.length() + 1];
        Arrays.fill(dp[0], 1);

        for(int i = 1; i <= s.length(); i ++){
            for(int j = 1; j <= arr[start].length; j ++){
                char c1 = s.charAt(i - 1);
                char c2 = arr[start][j - 1];

                if(c1 == c2){
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                }else{
                    dp[i][j] = dp[i][j - 1];
                }
            }
            start = (start + 1) % 2;
        }

        return dp[s.length()][s1.length()];
    }
}
