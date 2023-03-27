import java.io.*;
import java.util.*;

public class Main {

    public static class Pair {
        int d, w;

        public Pair(int d, int w) {
            this.d = d;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Pair[] p = new Pair[n];
        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair p1, Pair p2) {
                if (p1.d == p2.d)
                    return p2.w - p1.w;
                return p1.d - p2.d;
            }
        });

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            pq.offer(new Pair(d, w));
        }

        PriorityQueue<Integer> cost = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer p1, Integer p2) {
                return p1.compareTo(p2);
            }
        });

        int day = 1;
        while (!pq.isEmpty()) {
            Pair cur = pq.peek();
            while(!pq.isEmpty() && cur.d <= day){
                cost.offer(cur.w);
                pq.poll();
                cur = pq.peek();
            }
            while(!cost.isEmpty() && day < cost.size()){
                cost.poll();
            }
            day ++;
        }

        int answer = 0;
        while(!cost.isEmpty()) answer += cost.poll();
        System.out.println(answer);
    }
}