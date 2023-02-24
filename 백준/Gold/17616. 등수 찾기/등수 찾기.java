import java.io.*;
import java.util.*;

public class Main {

    static boolean[] check;
    static ArrayList<Integer>[][] vertex;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        vertex = new ArrayList[n + 1][2];
        for(int i = 1; i <= n; i ++) {
            vertex[i][0] = new ArrayList<>(); 
            vertex[i][1] = new ArrayList<>();
        }

        for(int i = 0; i < m; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int win = Integer.parseInt(st.nextToken());
            int lose = Integer.parseInt(st.nextToken());

            vertex[win][0].add(lose);
            vertex[lose][1].add(win);
        }

        StringBuilder sb = new StringBuilder();
        check = new boolean[n + 1];
        check[x] = true;
        sb.append(dfs(1, 0, x) + " ");

        check = new boolean[n + 1];
        check[x] = true;
        sb.append(n - dfs(0, 0, x) + 1);

        System.out.println(sb.toString());
    }

    public static int dfs(int t, int depth, int node){
        check[node] = true;
        int temp = 1;
        // temp = 1, output : 6
        // temp = 0, output : 0
        // if(vertex[node][t].size() == 0), output : 3
        for(int next : vertex[node][t]){
            if(check[next]) continue;
            check[next] = true;
            temp += dfs(t, depth + 1, next);
        }
        return temp;
    }
}