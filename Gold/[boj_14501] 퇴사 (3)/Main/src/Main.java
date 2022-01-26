import java.io.*;
import java.util.StringTokenizer;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N + 1][2];
        for(int i = 1; i <= N; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            board[i] = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
        }
        int[] dp = new int[N + 1];
        System.out.println(solution(N, dp, board));
    }

    public static int solution(int N, int[] dp, int[][] board){
        
        dp[0] = 0;
        int answer = dp[0];
        for(int i = 1; i <= N; i ++){
            if(i + board[i][0] <= N + 1){
                for(int j = 0; j < i; j ++){
                    if(j + board[j][0] <= i){
                        dp[i] = Math.max(dp[i], dp[j] + board[i][1]);
                    }
                }
                answer = (answer > dp[i]) ? answer : dp[i];
            }
        }

        return answer;
    }
}
