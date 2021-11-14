import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(stz.nextToken());
        int s = Integer.parseInt(stz.nextToken());
        int[] array = new int[n];

        stz = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(stz.nextToken());
        }

        System.out.println(solution(n, s, array));
    }

    public static int solution(int n, int s, int[] array) {
         int INF = Integer.MAX_VALUE;
        int answer = INF;
        int l = 0;
        int r = 0;
        int sum = array[0];

        while (l <= r && r < n) {
            if (sum >= s) {
                int length = r - l + 1;
                answer = Math.min(answer, length);
            }

            if (sum < s) {
                if (r + 1 >= n)
                    break;
                sum += array[++r];
            } else {
                sum -= array[l++];
            }
        }

        return (answer == INF) ? 0 : answer;
    }
}
