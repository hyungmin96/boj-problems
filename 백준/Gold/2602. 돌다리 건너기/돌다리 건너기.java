import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] dp;
    static char[][] arr;
    static String seq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        seq = br.readLine();
        String str1 = br.readLine();
        String str2 = br.readLine();

        N = seq.length();

        dp = new int[N + 1][str1.length() + 1];
        arr = new char[2][str1.length()];

        arr[0] = str1.toCharArray();
        arr[1] = str2.toCharArray();

        for (int i = 0; i < N; i++)
            Arrays.fill(dp[i], -1);

        int answer = 0;
        answer += dfs(0, 0, 0);

        for (int i = 0; i < N; i++)
            Arrays.fill(dp[i], -1);

        answer += dfs(0, 0, 1);

        System.out.println(answer);
    }

    /*
     * @param depth seq(두루마리) 현재 인덱스
     * 
     * @param idx arr[문자열] 현재 인덱스
     * 
     * @param flag 천사의 다리, 악마의 다리
     */
    public static int dfs(int depth, int idx, int flag) {
        if (depth == N)
            return 1;
        if (dp[depth][idx] != -1)
            return dp[depth][idx];
        if (idx >= arr[0].length || depth > N)
            return 0;

        dp[depth][idx] = 0;
        for (int i = idx; i < arr[0].length; i++) {
            if (arr[flag][i] == seq.charAt(depth)) {
                dp[depth][idx] += dfs(depth + 1, i + 1, (flag + 1) % 2);
            }
        }

        return dp[depth][idx];
    }
}