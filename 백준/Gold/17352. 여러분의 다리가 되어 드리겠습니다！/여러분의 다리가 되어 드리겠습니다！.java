import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Integer>[] vertex;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        vertex = new ArrayList[n + 1];
        for (int i = 1; i <= n; i ++) vertex[i] = new ArrayList<>();
        for (int i = 0; i < n - 2; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            vertex[start].add(end);
            vertex[end].add(start);
        }

        int root = 1;
        boolean[] check = new boolean[n + 1];
        check[root] = true;
        dfs(1, check);
        for (int i = 1; i <= n; i ++){
            if(!check[i]){
                System.out.println(root + " " + i + "\n");
                return;
            }
        }
    }

    public static void dfs(int node, boolean[] check){
        for(int next : vertex[node]){
            if(check[next]) continue;
            check[next] = true;
            dfs(next, check);
        }
    }
}