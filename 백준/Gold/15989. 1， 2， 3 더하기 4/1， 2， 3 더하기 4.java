import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException{
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int N;
    int[] dp;

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i ++){
            dp = new int[10001];
            int num = Integer.parseInt(br.readLine());

            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 3;
            for(int j = 4; j <= num; j ++){
                dp[j] = (j / 2) + dp[j - 3] + 1;
            }

            sb.append(dp[num] + "\n");
        }
        System.out.println(sb.toString());
    }
}

