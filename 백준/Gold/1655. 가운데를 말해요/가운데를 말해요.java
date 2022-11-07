import java.io.*;
import java.util.*;

public class Main {

    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());        
        PriorityQueue<Integer> pq1 = new PriorityQueue<>(new Comparator<>(){
            @Override
            public int compare(Integer o1, Integer o2) { return o2 - o1; }
        });
        PriorityQueue<Integer> pq2 = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i ++){
            int num = Integer.parseInt(br.readLine());
            if(pq1.isEmpty() || pq1.size() == pq2.size()){
                pq1.offer(num);
            }else{
                pq2.offer(num);
            }

            if(!pq1.isEmpty() && ! pq2.isEmpty() && pq1.peek() > pq2.peek()){
                int temp = pq1.poll();
                pq1.offer(pq2.poll());
                pq2.offer(temp);
            }

            sb.append(pq1.peek() + "\n");
        }
        System.out.println(sb.toString());
    }
}