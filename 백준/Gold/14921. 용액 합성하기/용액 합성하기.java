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
    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int[] arr = new int[N];
        for(int i = 0; i < N; i ++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int answer = Integer.MAX_VALUE;
        int left = 0, right = N - 1;
        while(left < right){
            int sum = arr[left] + arr[right];
            if(sum == 0){
                System.out.println(0);
                return;
            }else if(sum > 0){
                right --;
            }else{
                left ++;
            }
            
            if(Math.abs(answer) > Math.abs(sum)){
                answer = sum;
            }
        }

        System.out.println(answer);
    }
}