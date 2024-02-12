import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {
    
    StringBuilder sb = new StringBuilder();

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < testcase; i ++){
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N + 1];
            for(int j = 0; j < N; j ++){
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int interview = Integer.parseInt(st.nextToken());
                int paper = Integer.parseInt(st.nextToken());

                arr[interview] = paper;
            }

            int answer = 0;
            int max = 987654321;
            for(int j = 1; j <= N; j ++){
                if(max > arr[j]){
                    max = arr[j];
                    answer ++;
                }
            }

            sb.append(answer + "\n");
        }
        System.out.println(sb.toString());
    }
}
