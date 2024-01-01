import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {
    int N, K;

    class Pair{
        int val, cr, cc, tr, tc;
        public Pair(int val, int cr, int cc, int tr, int tc){
            this.val = val;
            this.cr = cr;
            this.cc = cc;
            this.tr = tr;
            this.tc = tc;
        }
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Pair[] pairs = new Pair[K];
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < K; i ++){
            st = new StringTokenizer(br.readLine());
            int target_num = Integer.parseInt(st.nextToken());
            int target_r = Integer.parseInt(st.nextToken()) - 1;
            int target_c = Integer.parseInt(st.nextToken()) - 1;
    
            int cur_r = (target_num - 1) / N;
            int cur_c = (target_num - 1) % N;

            pairs[i] = new Pair(target_num, cur_r, cur_c, target_r, target_c);
        }

        for(int i = 0; i < K; i ++){
            Pair cur = pairs[i];
            int move_row = (N + cur.tr - cur.cr) % N;
            int move_col = (N + cur.tc - cur.cc) % N;

            update(i, pairs, move_row, move_col);
            sb.append((move_row + move_col) + "\n");
        }
        System.out.println(sb.toString());
    }

    public void update(int idx, Pair[] pairs, int move_row, int move_col){
        Pair cur = pairs[idx];
        for(int i = idx + 1; i < K; i ++){
            Pair next = pairs[i];
            if(next.val == cur.val){
                next.cr = cur.tr;
                next.cc = cur.tc;
            }else if(cur.cr == next.cr){
                next.cc = (next.cc + move_col) % N;
            }else if(cur.tc == next.cc){
                next.cr = (next.cr + move_row) % N;
            }
        }
    }
}
