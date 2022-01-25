import java.io.*;
import java.util.StringTokenizer;
public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][2];
        for(int i = 0; i < N; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            board[i] = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
        }
        int[] dp = new int[N + 1];
        System.out.println(solution(N, dp, board));
    }

    public static int solution(int N, int[] dp, int[][] board){
        
        dp[0] = 0;
        int answer = Integer.MIN_VALUE;
        for(int i = 0; i < N; i ++){
            if()
        }

        return answer;
    }
}
