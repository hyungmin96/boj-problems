import java.io.*;
import java.util.*;

public class Main {

    public static class People{
        long min, max, rest;
        public People(long min, long max, long rest) { this.min = min; this.max = max; this.rest = rest; }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        long t = Long.parseLong(st.nextToken());
        People[] arr = new People[n];

        long sum_min = 0, sum_max = 0;
        long left = sum_min, right = sum_max;
        for(int i = 0; i < n; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            sum_min += s;
            sum_max += e;
            left = Math.max(s, left);
            right = Math.max(e, right);
            arr[i] = new People(s, e, e - s);
        }

        if(sum_min > t || sum_max < t){
            System.out.println(-1);
            return;
        }

        long answer = 987654321;
        while(left <= right){
            long mid = (right + left) / 2;
            long temp = t;
            long rest = 0;
            for(int i = 0; i < n; i ++){
                temp -= arr[i].min;
                rest += Math.min(mid - arr[i].min, arr[i].rest);
            }

            if(rest >= temp){
                answer = Math.min(mid, answer);
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }

        System.out.println(answer == 987654321 ? -1 : answer);
    }
}