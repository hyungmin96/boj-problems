import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException{
        Solution sol = new Solution();
        sol.solution();
    }
}
    
class Solution{

    int N;
    int[] sum, answer;

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        sum = new int[N];
        answer = new int[N];

        for(int i = 0; i < N; i ++){
            int num = Integer.parseInt(br.readLine());
            sum[i] = num;
        }

        dfs(0, new int[N]);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < answer.length; i ++){
            sb.append(answer[i] + "\n");
        }

        System.out.println(sb.toString());
    }

    public void dfs(int depth, int[] tmp){
        if(depth >= N){
            if(tmp[0] + tmp[N - 1] == sum[N - 1]){
                answer = tmp.clone();
            }
            return;
        }

        if(depth == 0){
            for(int i = 0; i <= sum[0]; i ++){
                tmp[0] = i;
                if(sum[N - 1] - i < 0) break;
                tmp[N - 1] = sum[N - 1] - tmp[0];
                dfs(depth + 1, tmp);
            }
        }else{
            int pre_idx = depth - 1;
            if(sum[pre_idx] - tmp[pre_idx] < 0) return;
            tmp[depth] = sum[pre_idx] - tmp[pre_idx];
            dfs(depth + 1, tmp);
        }
    }
}