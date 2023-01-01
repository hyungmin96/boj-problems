import java.io.*;
import java.util.*;

public class Main {

    static boolean isGraph = true;
    static ArrayList<Integer>[] vertex;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        for(int i = 0; i < tc; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int[] colors = new int[n + 1]; // 1 red, 2 blue, 0 empty

            isGraph = true;
            vertex = new ArrayList[n + 1];
            for(int k = 1; k <= n; k ++) vertex[k] = new ArrayList<>();
            for(int j = 0; j < e; j ++){
                st = new StringTokenizer(br.readLine(), " ");
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());

                vertex[n1].add(n2);
                vertex[n2].add(n1);
            }

            for(int k = 1; k <= n; k ++){
                if(!isGraph) break;
                if(colors[k] == 0){
                    dfs(k, 1, colors);
                }
            }
            if(isGraph) sb.append("YES\n"); else sb.append("NO\n");
        }
        System.out.println(sb.toString());
    }

    public static void dfs(int node, int color, int[] colors){
        colors[node] = color;
        for(int next : vertex[node]){
            if(colors[next] == color){
                isGraph = false;
                return;
            }

            if(colors[next] == 0){
                dfs(next, color == 1 ? 2 : 1, colors);
            }
        }
    }
}