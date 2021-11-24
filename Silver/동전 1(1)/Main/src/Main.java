import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(stz.nextToken());
        int k = Integer.parseInt(stz.nextToken());

        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        solution(0, 0, n, k, coins);

        System.out.println(answer);
    }

    public static void solution(int index, int level, int curr, int k, int[] coins) {
        
        if(curr > k) return;
        if (curr == k) {
            answer ++;
            return;
        } else {
            for (int i = index; i < coins.length; i++) {
                solution(i, level + 1, curr + coins[i], k, coins);
            }
        }
    }
}
