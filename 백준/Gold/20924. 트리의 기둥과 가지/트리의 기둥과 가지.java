import java.io.*;
import java.util.*;


public class Main {

    static int answer = 0;
    static ArrayList<int[]>[] vertex;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int root = Integer.parseInt(st.nextToken());

        check = new boolean[n + 1];
        vertex = new ArrayList[n + 1];
        for(int i = 1; i <= n; i ++) vertex[i] = new ArrayList<>();
        for(int i = 0; i < n - 1; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            vertex[start].add(new int[] { next, cost });
            vertex[next].add(new int[] { start, cost });
        }

        check[root] = true;
        StringBuilder sb = new StringBuilder();
        int[] giga_info = dfs(root, root, 0);
        
        if(giga_info[0] != -1){
            find(giga_info[0], giga_info[0], 0);
            sb.append(giga_info[1] + " " + answer);
        }else
            sb.append(giga_info[1] + " " + 0);

        System.out.println(sb.toString());
    }

    public static void find(int root, int node, int cost){
        if(root != node && vertex[node].size() == 1){
            answer = Math.max(answer, cost);
            return;
        }
        for(int[] next : vertex[node]){
            if(check[next[0]]) continue;
            check[next[0]] = true;
            find(root, next[0], cost + next[1]);
        }
    }

    public static int[] dfs(int root, int node, int cur){
        int[] temp = { -1, cur };
        if(vertex[node].size() >= 2 && root == node) 
            return new int[] { node, 0 };

        if(vertex[node].size() >= 3 && root != node) 
            return new int[] { node, cur };

        for(int[] next : vertex[node]){
            if(check[next[0]]) continue;
            check[next[0]] = true;
            temp = dfs(root, next[0], cur + next[1]);
        }
        return temp;
    }
}