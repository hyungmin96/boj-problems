import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int D, P;
    int[] nums = { 2, 3, 4, 5, 6, 7, 8, 9 };

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        D = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        
        System.out.println(dfs(0, 0, 1));
    }

    public long dfs(int depth, int idx, long cur){
        long ret = -1;
        if(depth == P){
            return ret = cur;
        }
        for(int i = idx; i < nums.length; i ++){
            if(getLength(cur * nums[i]) > D) continue;
            ret = Math.max(ret, dfs(depth + 1, i, cur * nums[i]));
        }

        return ret;
    }

    public long getLength(long n){
        int cnt = 0;
        while(n > 0){
            n /= 10;
            cnt ++;
        }
        return cnt;
    }
}

