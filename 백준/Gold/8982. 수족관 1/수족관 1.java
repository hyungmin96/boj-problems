import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {
    
    int N, K;
    int[] height = new int[40001];
    int[] diff = new int[40001];

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int max_c = 0;
        br.readLine();
        for (int i = 0; i < N / 2 - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int c = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine(), " ");
            int c1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            for (int j = c; j < c1; j++) {
                height[j] = r;
            }

            max_c = Math.max(max_c, c1);
        }
        br.readLine();

        K = Integer.parseInt(br.readLine());
        for(int i = 0; i < K; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int c = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            
            int left = r, right = r;
            for(int j = c; j >= 0; j --){
                left = left > height[j] ? height[j] : left;
				diff[j] = left > diff[j] ? left : diff[j];
            }
            for(int j = c; j <= max_c; j ++){
                right = right > height[j] ? height[j] : right;
				diff[j] = right > diff[j] ? right : diff[j];
            }
        }

        long answer = 0;
        for(int i = 0; i < max_c; i ++){
            answer += height[i] - diff[i];
        }
        System.out.println(answer);
    }
}
