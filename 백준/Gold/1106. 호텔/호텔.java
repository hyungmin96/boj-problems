import java.io.*;
import java.util.*;

public class Main {

    static int C, N;
    static int[] dp = new int[1201];
    static int[][] V;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        V = new int[N][2];

        Arrays.fill(dp, 987654321);
        for(int i = 1; i <= N; i ++){
            int order = 1;
            st = new StringTokenizer(br.readLine(), " ");
            int cost = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            V[i - 1] = new int[] { cost, p } ;
            while(true){
                if(p * order > 1200) break;
                dp[p * order] = Math.min(cost * order, dp[p * order]);
                order ++;
            }
        }

        for(int j = 0; j < N; j ++){
            for(int i = 1; i <= C + 100; i ++){
                int cost = V[j][0];
                int p = V[j][1];

                if(i - p >= 0){
                    dp[i] = Math.min(dp[i], dp[i - p] + dp[p]);
                }
            }
        }

        int answer = 987654321;
        for(int i = C; i <= 1200; i ++){
            answer = Math.min(dp[i], answer);
        }

        System.out.println(answer);
    }
}