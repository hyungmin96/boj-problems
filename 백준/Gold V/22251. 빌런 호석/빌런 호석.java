import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException{
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {
    
    int N, K, P, X;
    boolean[][] digit = {
        {true, true, true, false, true, true, true},
        {false, false,false, false,false, true, true},
        {false, true, true, true, true, true, false},
        {false, false, true, true, true, true, true},
        {true, false, false, true, false, true, true},
        {true, false, true, true, true, false, true},
        {true, true, true, true, true, false, true},
        {false, false, true, false, false, true, true},
        {true, true, true, true, true, true, true},
        {true, false, true, true, true, true, true},
    };

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

      int[] arr = new int[K];
        int tmp = X;
        for(int i = 0; i < K; i ++){
            arr[K - i - 1] = tmp % 10;
            tmp /= 10;
        }
        System.out.println(dfs(0, 0, P, arr));
    }

    public int dfs(int depth, long cur, int cnt, int[] arr){
        if(depth == K && cur >= 1 && cur <= N){
            if(cur == X) return 0;
            return 1;
        }
        if(depth == K) return 0;

        int ret = 0;
        for(int i = 0; i < 10; i ++){
            int change_cnt = 0;
            for(int j = 0; j < 7; j ++){
                if(digit[arr[depth]][j] != digit[i][j]){
                    change_cnt ++;
                }
            }

            if(cnt - change_cnt >= 0){
                ret += dfs(depth + 1, cur * 10 + i, cnt - change_cnt, arr);
            }
        }
        return ret;
    }
}

