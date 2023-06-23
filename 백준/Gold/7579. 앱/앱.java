import java.util.*;
import java.io.*;

public class Main {

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] size = new int[N];
        int[] cost = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i ++){
            size[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i ++){
            cost[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[10001];
        for(int j = 0; j < N; j ++){
            int c = cost[j];
            for(int i = 10000; i >= 1; i --){
                if (i - c >= 0 && dp[i] < dp[i - c] + size[j])
                    dp[i] = dp[i - c] + size[j];
                // if(i - c >= 0)
            }
        }

        int answer = 987654321;
        for(int i = 1; i <= 10000; i ++){
            if(dp[i] >= M)
                answer = Math.min(answer, i);
        }

        System.out.println(answer);
    }
}