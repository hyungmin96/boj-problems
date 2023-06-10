import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] dp;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        arr = new int[N + 1][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            arr[i][0] = left;
            arr[i][1] = right;
        }

        Arrays.sort(arr, new Comparator<>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[0] - o2[0];
            }
        });

        for (int i = 1; i <= N; i++) {
            dp[i] = 1;
            for(int j = i - 1; j >= 0; j --){
                if(arr[j][1] < arr[i][1]){
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }

        int max = 0;
        for(int i = 1; i <= N; i ++)
            max = Math.max(max, dp[i]);

        System.out.println(N - max);
    }
}