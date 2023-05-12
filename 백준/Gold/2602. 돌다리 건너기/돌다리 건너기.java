import java.io.*;
import java.util.*;

import javax.sound.midi.Sequence;

public class Main {

    static int N;
    static int[][] dp = new int[21][101];
    static char[][] arr = new char[2][101];
    static String seq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        seq = br.readLine();
        String str1 = br.readLine();
        String str2 = br.readLine();

        N = seq.length();
        arr[0] = str1.toCharArray();
        arr[1] = str2.toCharArray();

        int answer = dfs(0, 0, 0);
        
        for(int i = 0; i < N; i ++){
            Arrays.fill(dp[i], 0);
        }
        answer += dfs(0, 0, 1);
        System.out.println(answer);
    }

    public static int dfs(int depth, int idx, int flag){
        if (dp[depth][idx] != 0)
            return dp[depth][idx];
        if (depth == N)
            return 1;
        if (idx >= arr[0].length || depth > N)
            return 0;

        for (int i = idx; i < arr[0].length; i++) {
            if (arr[flag][i] == seq.charAt(depth)) {
                dp[depth][idx] += dfs(depth + 1, i + 1, (flag + 1) % 2);
            }
        }

        return dp[depth][idx];
    }
}