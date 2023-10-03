import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution{

    int pos = 1;
    StringBuilder answer = new StringBuilder();
    StringBuilder sb = new StringBuilder();
    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        for(int i = 0; i < tc; i ++){
            pos = 1;
            sb = new StringBuilder();
            int nodes = Integer.parseInt(br.readLine());
            int[] pre = new int[nodes];
            int[] in = new int[nodes];
            int[] idx = new int[nodes + 1];
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < nodes; j ++){
                pre[j] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < nodes; j ++){
                int num = Integer.parseInt(st.nextToken());
                in[j] = num;
                idx[num] = j;
            }

            dfs(pre[0], -1, nodes, pre, in, idx);
            answer.append(sb.toString() + "\n");
        }
        System.out.println(answer.toString());
    }

    public void dfs(int root, int left, int right, int[] pre, int[] in, int[] idx){
        if(pos < in.length){
            int next = pre[pos];
            if(left < idx[next] && idx[next] < idx[root]){
                pos ++;
                dfs(next, left, idx[root], pre, in, idx);
            }
        }
        if(pos < in.length){
            int next = pre[pos];
            if(right > idx[next] && idx[root] < idx[next]){
                pos ++;
                dfs(next, idx[root], right, pre, in, idx);
            }
        }
        sb.append(root + " ");
    }
}