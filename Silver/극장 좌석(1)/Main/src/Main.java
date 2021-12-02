import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[] dp = new int[n + 1];
        int[] arr = new int[m];

        for (int i = 0; i < m; i++)
            arr[i] = Integer.parseInt(br.readLine());

        System.out.println(solution(n, m, arr, dp));
    }

    public static int solution(int n, int m, int[] arr, int[] dp) {
        Arrays.fill(dp, 0);

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 0; i < arr.length; i++)
            dp[arr[i]] = -1;

        for (int i = 2; i <= n; i++) {
            if (dp[i] != -1) {
                int cnt = 0;
                for (int j = i; j > 0; j--) {
                    if (cnt == 0 && dp[i - 1] == -1) {
                        j --;
                        cnt++;
                    }
                    if(dp[j - 1] == -1) continue;
                    cnt++;
                    dp[i] += dp[j - 1];
                    if (cnt == 2)
                        break;
                }
            }
        }
        return (Arrays.stream(dp).max().getAsInt() == -1) ? 1 : Arrays.stream(dp).max().getAsInt();
    }
}
