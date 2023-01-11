import java.io.*;
import java.util.*;

public class Main {

    static int answer = 0;
    static ArrayList<Integer>[] vertex;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        vertex = new ArrayList[n];
        check = new boolean[n];
        
        int[] nodes = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i ++) vertex[i] = new ArrayList<>();
        for (int i = 0; i < n; i ++){
            int node = Integer.parseInt(st.nextToken());
            if(node != -1){
                vertex[node].add(i);
            }else if(node == -1) {
                nodes[i] = -1;
            }
        }

        int delete_node = Integer.parseInt(br.readLine());
        check[delete_node] = true;
        for(int i = 0; i < n; i ++){
            if(nodes[i] == -1)
                dfs(i);
        }

        System.out.println(answer);
    }

    public static void dfs(int node){
        if(check[node]) return;
        if(vertex[node].size() == 0){
            answer ++;
            return;
        }

        boolean flag = true;
        for(int next : vertex[node]){
            if(!check[next]){
                flag = false;
                break;
            }
        }

        if(flag){
            answer ++;
            return;
        }
        
        for(int next : vertex[node]){
            check[node] = true;
            dfs(next);
        }
    }
}