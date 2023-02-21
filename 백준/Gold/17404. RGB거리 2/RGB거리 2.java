import java.io.*;
import java.util.*;

public class Main {

    static int[][] arr, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        arr = new int[n + 1][3];
        dp = new int[n + 1][3];
        for(int i = 0; i < n; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < 3; j ++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp[0][0] = arr[0][0];
        // dp[0][1] = arr[0][1];
        // dp[0][2] = arr[0][2];

        int red = 987654321;
        int blue = 987654321;
        int green = 987654321;
        for(int color = 0; color < 3; color ++){ // 첫 번째 집을 Color색으로 칠 할 경우
            for(int i = 0; i < 3; i ++){
                if(color == i){
                    dp[1][i] = arr[0][i];
                }else{
                    dp[1][i] = 987654321;
                }
            }

            for(int d = 2; d <= n; d ++){
                dp[d][0] = Math.min(dp[d - 1][1], dp[d - 1][2]) + arr[d - 1][0];
                dp[d][1] = Math.min(dp[d - 1][0], dp[d - 1][2]) + arr[d - 1][1];
                dp[d][2] = Math.min(dp[d - 1][0], dp[d - 1][1]) + arr[d - 1][2];

                if(d == n){
                    if(color == 0){
                        red = Math.min(dp[n][1], dp[n][2]);
                    }
                    if(color == 1){
                        blue = Math.min(dp[n][0], dp[n][2]);
                    }
                    if(color == 2){
                        green = Math.min(dp[n][0], dp[n][1]);
                    }
                }
            }
        }
        System.out.println(Math.min(red, Math.min(blue, green)));
    }
}