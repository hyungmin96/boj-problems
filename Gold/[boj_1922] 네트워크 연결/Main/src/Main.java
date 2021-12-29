import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
public class Main {

    static class Info implements Comparable<Info>{
        int to, cost;
        public Info(int to, int cost){
            this.to = to;
            this.cost = cost;
        }
        @Override
        public int compareTo(Main.Info o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int v = Integer.parseInt(br.readLine());
        int e = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[v + 1];
        ArrayList<ArrayList<Info>> arrList = new ArrayList<>();
        
        for(int i = 0; i <= v; i ++)
            arrList.add(new ArrayList<>());

        for(int i = 0; i < e; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            
            arrList.get(x).add(new Info(y, cost));
            arrList.get(y).add(new Info(x, cost));
        }

        System.out.println(solution(v, arrList, visited));
    }

    public static int solution(int v, ArrayList<ArrayList<Info>> arrList, boolean[] visited){
        int answer = 0;
        int cnt = 0;
        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.offer(new Info(1, 0));

        while(!pq.isEmpty()){
            Info curr = pq.poll();
            if(!visited[curr.to]){
                visited[curr.to] = true;
                answer += curr.cost;
                for(Info next : arrList.get(curr.to)){
                    if(!visited[next.to]){
                        pq.offer(next);
                    }
                }
                if(++cnt == v) return answer;
            }
        }

        return answer;
    }
}
