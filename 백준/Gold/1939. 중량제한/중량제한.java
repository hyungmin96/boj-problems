import java.io.*;
import java.util.*;

public class Main {

    static class Pair{
        int s, e;
        long cost;
        public Pair(int s, int e, long cost) { this.s = s; this.e = e; this.cost = cost; }
    }

    static int N, M;
    static ArrayList<Pair>[] vertex;
    static boolean[] check;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        vertex = new ArrayList[N + 1];

        long left = 0, right = 1000000000;
        for(int i = 1; i <= N; i ++) vertex[i] = new ArrayList<>();
        for(int i = 0; i < M; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            long cost = Integer.parseInt(st.nextToken());
            vertex[s].add(new Pair(s, e, cost));
            vertex[e].add(new Pair(e, s, cost));
        }

        st = new StringTokenizer(br.readLine(), " ");
        int c1 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());
        while(left <= right){
            flag = false;
            check = new boolean[N + 1];
            check[c1] = true;
            long mid = (right + left) / 2;
            dfs(c1, c2, mid);
            if(flag){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        System.out.println(right);
    }

    public static void dfs(int start, int node, long cur){
        if(start == node){
            flag = true;
            return;
        }
        for(Pair next : vertex[start]){
            if(next.cost < cur) continue;
            if(!check[next.e]){
                check[next.e] = true;
                dfs(next.e, node, cur);
            }
        }
    }
}