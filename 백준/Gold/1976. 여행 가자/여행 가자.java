import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException{
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int N, M;
    int[] uf;

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        uf = new int[N + 1];

        for(int i = 0; i <= N; i ++) uf[i] = i;
        for(int i = 0; i < N; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j ++){
                int n = Integer.parseInt(st.nextToken());

                if(n == 1){
                    union(i + 1, j + 1);
                }
            }
        }

        int cur_node = 0;
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < M; i ++){
            int node = Integer.parseInt(st.nextToken());
            if(cur_node != 0 && cur_node != find(node)){
                System.out.println("NO");
                return;
            }else if(cur_node == 0){
                cur_node = find(node);
            }
        }

        System.out.println("YES");
    }

    public void union(int a, int b){
        a = find(a);
        b = find(b);

        if(a <= b){
            uf[b] = a;
        }else{
            uf[a] = b;
        }
    }

    public int find(int a){
        if(uf[a] == a) return a;
        return find(uf[a]);
    }
}

