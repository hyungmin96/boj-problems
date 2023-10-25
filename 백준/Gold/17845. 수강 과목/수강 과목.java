import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException{
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {
    
    int N, K;
    int[][] dp, arr;

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[K + 1][10001];
        arr = new int[K + 1][2];

        for(int k = 0; k < K; k ++){
            st = new StringTokenizer(br.readLine(), " ");

            int p = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            arr[k + 1] = new int[] { p, t };
        }
        
        System.out.println(solve());
    }

    public int solve(){
        dp[0][0] = 0;
        for(int i = 1; i <= K; i ++){ // i번째 과목
            for(int j = 1; j < dp[i].length; j ++){ // j 시간
                int p = arr[i][0];
                int t = arr[i][1];

                if(j - t >= 0){
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - t] + p);
                }else{
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }
            }
        }

        return dp[K][N];
    }
}

