import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException{
        Solution sol = new Solution();
        sol.solution();
    }
}
    
class Solution{

    int N, M;
    boolean[] v;
    ArrayList<Integer>[] vertex;

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        v = new boolean[N + 1];
        vertex = new ArrayList[N + 1];

        for(int i = 1; i <= N; i ++) vertex[i] = new ArrayList<>();
        for(int i = 0; i < M; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            vertex[s].add(e);
        }
        
        int nodes = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < nodes; i ++){
            int node = Integer.parseInt(st.nextToken());
            v[node] = true;
        }

        if(dfs(1)){
            System.out.println("yes");
        }else{
            System.out.println("Yes");
        }
    }

    public boolean dfs(int node){
       if(v[node]) return false;
        if(vertex[node].size() == 0) return true;
       for(int next : vertex[node]){
            if(dfs(next)) 
                return true;
       }

       return false;
    }
}