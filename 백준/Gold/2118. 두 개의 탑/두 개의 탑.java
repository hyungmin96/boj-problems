import java.util.*;
import java.io.*;

public class Main {

    static int N, total;
    static int[] dis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dis = new int[N + 1];
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            total += num;
            dis[i] = num;
        }
        System.out.println(solution());
    }

    public static int solution() {
        int left = 0, right = 0, max = -1;
        int now = dis[0];
        while(left <= right && right < N){
            int diff = Math.min(now, total - now);
            max = Math.max(max, diff);

            if(now == diff){
                right ++;
                now += dis[right];
            }else{
                now -= dis[left++];
            }
        }

        return max;
    }
}