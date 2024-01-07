import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int N;

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] a = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(a);  // 정렬 추가

        int tot = 0;
        for (int i = 0; i < N; i++) {
            tot += a[i];
            if (tot < i * (i + 1) / 2) {
                System.out.println(-1);
                return;
            }
        }

        if (tot != N * (N - 1) / 2) {
            System.out.println(-1);
            return;
        }

        System.out.println(1);
    }
}
