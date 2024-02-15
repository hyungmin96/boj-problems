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
    int[] answer, sum;

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sum = new int[N];
        answer = new int[N];

        for(int i = 0; i < N; i ++){
            sum[i] = Integer.parseInt(br.readLine());
        }
        
        dfs(0, new int[N]);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < answer.length; i ++)
            sb.append(answer[i] + "\n");

        System.out.println(sb.toString());
    }

    public boolean dfs(int depth, int[] arr){
        if(depth == N){
            if(arr[0] + arr[N - 1] == sum[N - 1]){
                answer = arr;
                return true;
            }

            return false;
        }

        if(depth == 0){
            for(int i = 0; i <= sum[depth]; i ++){
                if(sum[N - 1] - i >= 0){
                    arr[depth] = i;
                    if(dfs(depth + 1, arr)) return true;
                }
            }
        }else{
            if(sum[depth - 1] - arr[depth - 1] >= 0){
                arr[depth] = sum[depth - 1] - arr[depth - 1];
                if(dfs(depth + 1, arr)) return true;
            }
        }

        return false;
    }
}

