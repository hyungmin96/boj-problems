import java.io.*;
import java.util.*;

public class Main {

    public static class Pair {
        int num, remain;

        public Pair(int num, int remain) {
            this.num = num;
            this.remain = remain;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Pair[] arr = new Pair[n];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = new Pair(num, num % 10);
        }

        Arrays.sort(arr, new Comparator<>() {
            @Override
            public int compare(Pair p1, Pair p2) {
                if (p1.remain == p2.remain)
                    return p1.num - p2.num;
                return p1.remain - p2.remain;
            }
        });

        int idx = 0, answer = 0;
        while (idx < n) {
            if (arr[idx].num >= 10) {
                if (arr[idx].num == 10) {
                    answer++;
                } else {
                    int cur = arr[idx].num;
                    int cost = cur / 10;
                    if (m >= cost) {
                        answer += cost;
                        if (cur % 10 == 0) {
                            m -= (cost - 1);
                        } else {
                            m -= cost;
                        }
                    } else {
                        if(cur % 10 == 0 && cost - 1 == m) answer ++;
                        answer += m;
                        break;
                    }
                }
            }
            idx++;
        }
        System.out.println(answer);
    }
}