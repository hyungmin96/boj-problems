import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        Long n = Long.parseLong(st.nextToken());
        Long m = Long.parseLong(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");

        PriorityQueue<Long> pq = new PriorityQueue<>();
        for(int i = 0; i < n; i ++){
            pq.offer(Long.parseLong(st.nextToken()));
        }

        while(m --> 0){
            long n1 = pq.poll();
            long n2 = pq.poll();
            pq.offer(n1 + n2);
            pq.offer(n1 + n2);
        }

        long answer = 0;
        while(!pq.isEmpty()) answer += pq.poll();

        System.out.println(answer);
    }
}