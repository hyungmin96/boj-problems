import java.io.*;
import java.util.*;

public class Main {

    static int[] nodes;
    static ArrayList<Integer>[] vertex;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        nodes = new int[n + 1];
        vertex = new ArrayList[n + 1];
        
        for (int i = 1; i <= n; i ++) {
            nodes[i] = i;   
            vertex[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 2; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            // vertex[start].add(end);
            // vertex[end].add(start);
            union(start, end);
        }

        int root = 1;
        // boolean[] check = new boolean[n + 1];
        // check[root] = true;
        // dfs(1, check);
        for (int i = 1; i <= n; i ++){
            if(find(nodes[i]) != root){
                System.out.println(root + " " + find(nodes[i]));
                return;
            }
        }
    }
    
    public static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a < b) nodes[b] = a;
        else nodes[a] = b;
    }

    public static int find(int a){
        if(nodes[a] == a) return a;
        return nodes[a] = find(nodes[a]);
    }

    public static void dfs(int node, boolean[] check){
        for(int next : vertex[node]){
            if(check[next]) continue;
            check[next] = true;
            dfs(next, check);
        }
    }
}