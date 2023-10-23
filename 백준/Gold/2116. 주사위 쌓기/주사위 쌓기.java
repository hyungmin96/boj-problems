import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException{
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int N;
    int[] map = { 5, 3, 4, 1, 2, 0 };
    int[][] arr, idx;

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N][6];
        idx = new int[N][7];
        for(int i = 0; i < N; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < 6; j ++){
                int num = Integer.parseInt(st.nextToken());
                arr[i][j] = num;
                idx[i][num] = j;
            }
        }

        int answer = dfs(0, -1);
        System.out.println(answer);
    }

    public int dfs(int depth, int bottom){
        if(depth == N){
            return 0;
        }

        int ret = 0;
        for(int i = 1; i <= 6; i ++){
            // if i is bottom and i is equals to pre_top then,
            if(bottom == -1 || i == bottom){
                int top = arr[depth][map[idx[depth][i]]];
                int side = 0;
                for(int j = 6; j > 0; j --){
                    if(j != top && j != i){
                        side = j;
                        break;
                    }
                }
                ret = Math.max(ret, dfs(depth + 1, top) + side);
            }
        }
        return ret;
    }
}

