import java.io.*;
import java.util.*;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        long total = 0;
        long[] arr = new long[N];
        long[] sum = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i ++){
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
            sum[i] = total + num;
            total += num;
        }

        long answer = 0;
        for(int i = 1; i < N - 1; i ++){
            answer = Math.max(answer, sum[N - 1] - (arr[i] + arr[0]) + (sum[N - 1] - sum[i]));
        }

        for(int i = 1; i < N - 1; i ++){
            answer = Math.max(answer, sum[i] - sum[0] + sum[N - 1] - sum[i - 1] - arr[N - 1]);
        }

        for(int i = 1; i < N - 1; i ++){
            answer = Math.max(answer, sum[N - 1] - arr[i] - arr[N - 1] + sum[i - 1]);
        }

        System.out.println(answer);
    }
}