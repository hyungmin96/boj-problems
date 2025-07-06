import java.io.*;
import java.util.*;
public class Main{

    static int tc = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(tc -- > 0){
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            PriorityQueue<Long> pq = new PriorityQueue<>();
            for(int i = 0; i < n; i ++){
                pq.offer(Long.parseLong(st.nextToken()));
            }

            long answer = 0;
            while(pq.size() > 1){
                long n1 = pq.poll();
                long n2 = pq.poll();

                answer += n1 + n2;
                pq.offer(n1 + n2);
            }

            sb.append(answer + "\n");
        }

        System.out.println(sb.toString());
    }
}