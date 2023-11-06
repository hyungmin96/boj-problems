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
    int[] arr;

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i ++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = new int[N];
        Arrays.fill(answer, -1);

        Stack<Integer> stk = new Stack<>();
        for(int i = 0; i < N; i ++){
            int cur = arr[i];
            if(!stk.isEmpty()){
                while(!stk.isEmpty() && arr[stk.peek()] < cur){
                    answer[stk.pop()] = cur;
                }
            }
            stk.push(i);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i ++){
            sb.append(answer[i] + " ");
        }

        System.out.println(sb.toString());
    }
}

