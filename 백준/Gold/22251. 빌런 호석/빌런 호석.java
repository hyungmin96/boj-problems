import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException{
        Solution sol = new Solution();
        sol.solution();
    }
}
    
class Solution{

    int N, P, K, X;
    final boolean[][] digit = {
        { true, true, true, false, true, true, true },
        { false, false, false, false, false, true, true },
        { false, true, true, true, true, true, false },
        { false, false, true, true, true, true, true },
        { true, false, false, true, false, true, true },
        { true, false, true, true, true, false, true },
        { true, true, true, true, true, false, true },
        { false, false, true, false, false, true, true },
        { true, true, true, true, true, true, true },
        { true, false, true, true, true, true, true }
    };

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        
        int idx = 0;
        int tmp = X;
        int mod = (int)Math.pow(10, K - 1);
        int[] arr = new int[K];
        while(idx < K){
            arr[idx ++] = tmp / mod;
            if(X / mod > 0){
                tmp %= mod;
            }
            mod /= 10;
        }
        
        System.out.println(dfs(0, P, 0, arr));
    }

    public int dfs(int depth, int p, int cur_floor, int[] arr){
        if(depth == K && cur_floor >= 1 && cur_floor <= N){
            if(cur_floor == X) return 0;
            return 1;
        }

        if(depth == K) return 0;

        int ret = 0;
        int cur_num = arr[depth];
        for(int i = 0; i < 10; i ++){
            int diff_cnt = 0;
            for(int j = 0; j < 7; j ++){
                if(digit[i][j] != digit[cur_num][j]){
                    diff_cnt ++;
                }
            }
            if(diff_cnt > p) continue;
            ret += dfs(depth + 1, p - diff_cnt, cur_floor * 10 + i, arr);
        }
        return ret;
    }
}