import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int idx = 0;
        while(true){
            idx ++;
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if(n == 0 && m == 0) break;
            
            boolean[] check = new boolean[n + 1];
            ArrayList<Integer>[] vertex = new ArrayList[n + 1];

            for(int i = 1; i <= n; i ++) vertex[i] = new ArrayList<>();
            for (int i = 0; i < m; i ++){
                st = new StringTokenizer(br.readLine(), " ");
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());

                vertex[start].add(end);
                vertex[end].add(start);
            }

            int cnt = 0;
            for(int i = 1; i <= n; i ++){
                if(!check[i]){
                    check[i] = true;
                    if(dfs(i, -1, check, vertex)) cnt ++;
                }
            }
            if(cnt == 0){
                sb.append("Case " + idx + ": No trees.\n");
            }else if(cnt == 1){
                sb.append("Case " + idx + ": There is one tree.\n");
            }else{
                sb.append("Case " + idx + ": A forest of " + cnt + " trees.\n");
            }
        }

        System.out.println(sb.toString());
    }

    public static boolean dfs(int node, int pre, boolean[] check, ArrayList<Integer>[] vertex){
        for(int next : vertex[node]){
            if(next == pre) continue;
            if(check[next]) return false;
            check[next] = true;
            if(!dfs(next, node, check, vertex)) return false;
        }
        return true;
    }
}