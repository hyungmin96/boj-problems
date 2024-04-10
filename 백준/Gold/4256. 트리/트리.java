import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int depth = 0;
    StringBuilder sb = new StringBuilder();

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        while(tc -- > 0){
            depth = 0;
            int nums = Integer.parseInt(br.readLine());
            int[] pre = new int[nums];
            int[] in = new int[nums];
            int[] index = new int[nums + 1];
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < nums; i ++){
                pre[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < nums; i ++){
                in[i] = Integer.parseInt(st.nextToken());
                index[in[i]] = i;
            }

            dfs(pre[depth ++], 0, pre.length, pre, in, index);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public void dfs(int node, int l, int r, int[] pre, int[] in, int[] index){
        if(depth < pre.length){
            int next = pre[depth];
            int idx = index[next];
            if(l <= idx && idx < index[node]){
                depth ++;
                dfs(next, l, index[node], pre, in, index);
            }
        }

        if(depth < pre.length){
            int next = pre[depth];
            int idx = index[next];
            if(index[node] < idx && idx < r){
                depth ++;
                dfs(next, index[node], r, pre, in, index);
            }
        }

        sb.append(node + " ");
    }
}
