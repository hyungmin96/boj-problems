import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException{
        Solution sol = new Solution();
        sol.solution();
    }
}
    
class Solution{

    int N, max = -987654321, min = 987654321;
    int[] nums, opers;

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        opers = new int[4];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i ++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < 4; i ++){
            opers[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, nums[0]);
        System.out.println(max);
        System.out.println(min);
    }

    public void dfs(int depth, int cur){
        if(depth == N - 1){
            max = Math.max(max, cur);
            min = Math.min(min, cur);
            return;
        }

        for(int i = 0; i < 4; i ++){
            if(opers[i] > 0){
                opers[i] --;
                int tmp = cal(cur, i, nums[depth + 1]);
                dfs(depth + 1, tmp);
                opers[i] ++;
            }
        }
    }

    public int cal(int cur, int o, int n1){
        switch(o){
            case 0:
                return cur + n1;
            case 1:
                return cur - n1;
            case 2:
                return cur * n1;
            default:
                return cur / n1;
        }
    }
}