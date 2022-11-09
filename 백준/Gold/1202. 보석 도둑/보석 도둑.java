import java.io.*;
import java.util.*;

public class Main {

    public static class Pair{
        int weight, cost;
        double value;
        public Pair(int weight, int cost){
            this.weight = weight;
            this.cost = cost;
            this.value = (double)((double)cost / (double)weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Pair[] jewelry = new Pair[N];
        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int weight = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            jewelry[i] = new Pair(weight, cost);
        }

        Arrays.sort(jewelry, new Comparator<>(){
            @Override
            public int compare(Pair p1, Pair p2){
                if(p1.weight == p2.weight) return p2.cost - p1.cost;
                return p1.weight - p2.weight;
            }
        });

        int[] bag = new int[K];
        for(int i = 0; i < K; i ++){
            int weight = Integer.parseInt(br.readLine());
            bag[i] = weight;
        }
        Arrays.sort(bag);

        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int idx = 0;
        for(int i = 0; i < K; i ++){
            while(idx < N && jewelry[idx].weight <= bag[i]){
                pq.offer(jewelry[idx].cost);
                idx ++;
            }
            if(!pq.isEmpty()) answer += pq.poll();
        }
        
        System.out.println(answer);
    }
}