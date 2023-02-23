import java.util.*;
class Solution {
    
    static class Pair{
        int next;
        ArrayList list;
        public Pair(int next, ArrayList<Integer> list) { 
            this.next = next; 
            this.list = list; 
        }
    }
    
    static ArrayList<Integer>[] vertex;
    static int[] dist;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        
        dist = new int[n + 1];
        vertex = new ArrayList[n + 1];
        Arrays.fill(dist, - 987654321);
        
        for(int i = 1; i <= n; i ++) vertex[i] = new ArrayList<>();
        for(int i = 0; i < roads.length; i ++){
            int s = roads[i][0];
            int e = roads[i][1];
            
            vertex[s].add(e);
            vertex[e].add(s);
        }
        
        bfs(n, destination, sources);
        int idx = 0;
        for(int s : sources){
            answer[idx ++] = dist[s] == -987654321 ? -1 : dist[s];
        }
        return answer;
    }
    
    public static void bfs(int n, int destination, int[] source){
        boolean[] check = new boolean[n + 1];
        Queue<int[]> que = new LinkedList<>();
        
        que.offer(new int[] { destination, 0 });
        check[destination] = true;
        dist[destination] = 0;
        
        while(!que.isEmpty()){
            int[] cur = que.poll();
            for(Integer next : vertex[cur[0]]){
                if(check[next]) continue;
                check[next] = true;
                dist[next] = cur[1] + 1;
                que.offer(new int[] { next, cur[1] + 1 });
            }
        }
    }
}