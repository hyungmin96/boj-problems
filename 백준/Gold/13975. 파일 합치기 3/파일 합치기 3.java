import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        for(int i = 0; i < tc; i ++){
            int k = Integer.parseInt(br.readLine());

            PriorityQueue<Long> pq = new PriorityQueue<>();
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < k; j ++){
                pq.offer(Long.parseLong(st.nextToken()));
            }

            long total = 0;
            while(pq.size() > 1){
                long a = pq.poll();
                long b = pq.poll();
                pq.offer(a + b);
                total += a + b;
            }

            sb.append(total + "\n");
        }
        System.out.println(sb.toString());
    }
}