import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException{
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int N, M, min = 987654321, answer = -2;
    ArrayList<int[]>[] vertex;
    ArrayList<Integer> cp = new ArrayList<>();

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        vertex = new ArrayList[N + 1];
        for(int i = 1; i <= N; i ++) vertex[i] = new ArrayList<>();

        for(int i = 0; i < M; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            vertex[s].add(new int[] { e, t });
            vertex[e].add(new int[] { s, t });
        }

        getDistance(true, 0, 0);

        for(int i = 0; i < cp.size() - 1; i ++){
            int n1 = cp.get(i);
            int n2 = cp.get(i + 1);
            getDistance(false, n1, n2);
        }
        System.out.println(answer == -2 ? -1 : answer);
    }

    public int getDistance(boolean getPath, int n1, int n2){
        int[] path = new int[N + 1];
        int[] v = new int[N + 1];
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[0] - o2[0];
            }
        });

        // [소요시간, 방문노드]
        pq.offer(new int[] { 0, 1 });
        v[1] = 0;
        Arrays.fill(v, 987654321);

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            if(cur[1] == N){
                if(getPath){
                    dfs(N, path);
                    return min = cur[0];
                }else{
                    return answer = Math.max(answer, cur[0] - min);
                }
            }
            for(int[] next : vertex[cur[1]]){
                if(!getPath){
                    if((cur[1] == n1 && next[0] == n2) || cur[1] == n2 || next[0] == n1) 
                        continue;
                }
                if(v[next[0]] <= cur[0] + next[1]) continue;
                v[next[0]] = cur[0] + next[1];
                if(getPath) path[next[0]] = cur[1];
                pq.offer(new int[] { cur[0] + next[1], next[0] });
            }
        }
        return -1;
    }

    public void dfs(int node, int[] path){
        if(node == 0) return;
        cp.add(node);
        if(node == 1) return;
        dfs(path[node], path);
    }
}

