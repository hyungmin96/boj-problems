import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
public class Main {

    public static class Node implements Comparable<Node>{
        int start, destination, cost;
        public Node(int start, int destination, int cost){
            this.start = start;
            this.destination = destination;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }

    }

    private static ArrayList<ArrayList<Node>> nodes = new ArrayList<>();
    private static int[] distance;
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        distance = new int[n + 1];
        Arrays.fill(distance, INF);

        for(int i = 0; i <= n; i ++){
            nodes.add(new ArrayList<>());
        }

        for(int i = 0; i < k; i ++){
            StringTokenizer stz = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(stz.nextToken());
            int destination = Integer.parseInt(stz.nextToken());
            int cost = Integer.parseInt(stz.nextToken());

            nodes.get(start).add(new Node(start, destination, cost));
        }

        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");
        int start = Integer.parseInt(stz.nextToken());
        int end = Integer.parseInt(stz.nextToken());
        solution(start, end);
        System.out.println(distance[end]);
    }

    public static void solution(int start, int end){

        PriorityQueue<Node> que = new PriorityQueue<>();

        distance[0] = 0;
        que.offer(new Node(0, start, 0));

        while(!que.isEmpty()){
            Node curr = que.poll();
            if(distance[curr.destination] > curr.cost + distance[curr.start]){
                distance[curr.destination] = curr.cost + distance[curr.start];
                for(Node node : nodes.get(curr.destination)){
                    que.offer(node);
                }
            }
        }
    }
}
