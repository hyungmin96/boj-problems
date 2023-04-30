import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] arr;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][M + 2];
        dp = new int[3][N + 1][M + 2];

        for(int i = 0; i <= N; i ++){
            for(int j = 0; j <= M + 1; j ++){
                if(j == 0 || j == M + 1){
                    for(int k = 0; k < 3; k ++){
                        dp[k][i][j] = 987654321;
                    }
                }
            }
        }

        for(int i = 0; i <= N; i ++){
            arr[i] = new int[M + 2];
            if(i == 0) continue;
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 1; j <= M; j ++){
                int num = Integer.parseInt(st.nextToken());
                arr[i][j] = num;
            }
        }
      
        System.out.println(solution());
    }

    public static int solution(){
        for(int r = 1; r <= N; r ++){
            for(int c = 1; c <= M; c ++){
                dp[0][r][c] = Math.min(dp[1][r - 1][c], dp[2][r - 1][c + 1]) + arr[r][c]; // 왼 아래
                dp[1][r][c] = Math.min(dp[0][r - 1][c - 1], dp[2][r - 1][c + 1]) + arr[r][c]; // 아래
                dp[2][r][c] = Math.min(dp[0][r - 1][c - 1], dp[1][r - 1][c]) + arr[r][c]; // 오른쪽 아래
            }
        }

        int ret = 987654321;
        for(int i = 1; i <= M + 1; i ++){
            for(int j = 0; j < 3; j ++){
                ret = Math.min(ret, dp[j][N][i]);
            }
        }
        return ret;
    }
}