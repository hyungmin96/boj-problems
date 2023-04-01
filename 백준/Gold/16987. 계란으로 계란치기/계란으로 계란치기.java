import java.io.*;
import java.util.*;

public class Main {

    static class Pair{
        int d, w;
        public Pair(int d, int w) { this.d = d; this.w = w; }
    }

    static int max = 0;
    static Pair[] arr;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new Pair[n];
        check = new boolean[n];

        for(int i = 0; i < n; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int durability = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            arr[i] = new Pair(durability, weight);
        }

        dfs(n, 0, 0);
        System.out.println(max);
    }

    public static void dfs(int n, int depth, int cnt){
        if(n == depth){
            max = Math.max(max, cnt);
            return;
        }

        if(arr[depth].d <= 0){
            dfs(n, depth + 1, cnt);
            return;
        }

        for(int i = 0; i < n; i ++){
            if(depth == i) continue;
            int temp = 0;
            boolean flag = false;
            if(arr[depth].d > 0 && arr[i].d > 0){
                flag = true;
                arr[depth].d -= arr[i].w;
                arr[i].d -= arr[depth].w;
                if(arr[depth].d <= 0) temp ++;
                if(arr[i].d <= 0) temp ++;
            }
            dfs(n, depth + 1, cnt + temp);
            if(flag){
                arr[depth].d += arr[i].w;
                arr[i].d += arr[depth].w;
            }
        }
    }
}