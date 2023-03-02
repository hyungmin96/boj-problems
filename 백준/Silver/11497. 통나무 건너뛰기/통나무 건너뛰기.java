import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < tc; i ++){
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<>(){
                @Override
                public int compare(Integer o1, Integer o2) { return Integer.compare(o2, o1); }
            });
            for(int j = 0; j < n; j ++) pq.offer(Integer.parseInt(st.nextToken()));

            int[] temp = new int[n];
            temp[n / 2] = pq.poll();
            for(int k = 1; k <= n / 2; k ++){
                if(!pq.isEmpty()) temp[((n / 2) + k) % n] = pq.poll();
                if(!pq.isEmpty()) temp[n / 2 - k] = pq.poll();
            }

            int answer = -987654321;
            for(int j = 0; j < temp.length; j ++){
                answer = Math.max(answer, Math.abs(temp[j] - temp[(j + 1) % temp.length]));
            }

            sb.append(answer + "\n");
        }
        System.out.println(sb.toString());
    }
}