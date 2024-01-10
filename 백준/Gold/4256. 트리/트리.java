import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int N, depth = 0;
    int[] pre, in, index;
    StringBuilder sb = new StringBuilder();

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i ++){
            depth = 0;
            int num = Integer.parseInt(br.readLine());
            
            pre = new int[num];
            in = new int[num];
            index = new int[num + 1];

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < num; j ++){
                pre[j] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < num; j ++){
                in[j] = Integer.parseInt(st.nextToken());
                index[in[j]] = j;
            }

            dfs(pre[depth ++], 0, num);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public void dfs(int root, int l, int r){
        if(depth < pre.length){
            int next_num = pre[depth];
            int next_idx = index[next_num];

            if(l <= next_idx && next_idx < index[root]){
                depth ++;
                dfs(next_num, l, index[root]);
            }
        }

        if(depth < pre.length){
            int next_num = pre[depth];
            int next_idx = index[next_num];

            if(index[root] < next_idx && next_idx < r){
                depth ++;
                dfs(next_num, index[root], r);
            }
        }
        sb.append(root + " ");
    }
}