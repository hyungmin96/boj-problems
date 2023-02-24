import java.io.*;
import java.util.*;

public class Main {

    static int answer = -1;
    static boolean flag = false;
    static boolean[] check;
    static ArrayList<Integer>[] vertex;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());
        check = new boolean[n + 1];
        vertex = new ArrayList[n + 1];

        for(int i = 1; i <= n; i ++) vertex[i] = new ArrayList<>();
        for(int i = 0; i < m; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            vertex[s].add(e);
            vertex[e].add(s);
        }

        check[n1] = true;
        dfs(0, n1, n2);

        System.out.println(answer);
    }

    public static void dfs(int depth, int n1, int n2){
        if(flag) return;
        if(n1 == n2) {
            answer = depth;
            flag = true;
            return;
        }
        for(int next : vertex[n1]){
            if(check[next]) continue;
            check[next] = true;
            dfs(depth + 1, next, n2);
        }
    }
}