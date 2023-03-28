import java.io.*;
import java.util.*;

public class Main {

    public static class Pair{
        int p, d;
        public Pair(int p, int d) { this.p = p; this.d = d; }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>(new Comparator<>(){
            @Override
            public int compare(Pair p1, Pair p2){
                if(p1.d == p2.d) return p2.p - p1.p;
                return p1.d - p2.d;
            }
        });

        for(int i = 0; i < n; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            pq.offer(new Pair(p, d));
        }

        int day = 1;
        PriorityQueue<Integer> cost = new PriorityQueue<>();
        while(!pq.isEmpty()){
            while(!pq.isEmpty() && pq.peek().d <= day){
                cost.offer(pq.poll().p);
            }

            while(cost.size() > day){
                cost.poll();
            }
            day ++;
        }

        int answer = 0;
        while(!cost.isEmpty()) answer += cost.poll();
        System.out.println(answer);
    }
}