import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {
    
    public int N;
    public int[][] map, dirs = {
        { -1, 0 },
        { 0, 1 },
        { 1, 0 },
        { 0, -1 }
    };

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        
        long cnt = 0;
        for(int i = 0; i < N; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j ++){
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
                cnt += num;
            }
        }

        long answer = 987654321;
        for(int i = 0; i < N - 2; i ++){
            for(int j = 1; j < N - 1; j ++){
                for(int d1 = 1; d1 < N - 1; d1 ++){
                    for(int d2 = 1; d2 < N - 1; d2 ++){
                        answer = Math.min(answer, dfs(cnt, i, j, d1, d2));
                    }
                }
            }
        }
        System.out.println(answer);
    }

    public long dfs(long cnt, int r, int c, int d1, int d2){
        if(c - d1 < 0 || r + d1 + d2 >= N || c + d2 >= N) return 987654321;

        long a1 = 0;
        for(int i = r + d1 - 1, j = c - d1; i >= 0; i --, j ++){
            for(int k = 0; k <= Math.min(j, c); k ++){
                a1 += map[i][k];
            }
        }

        long a2 = 0;
        for(int i = r + d2, j = c + d2 + 1; i >= 0; i --, j --){
            for(int k = Math.max(j, c + 1); k < N; k ++){
                a2 += map[i][k];
            }
        }  

        long a3 = 0;
        for(int i = r + d1, j = c - d1 - 1; i < N; i ++, j ++){
            for(int k = 0; k <= Math.min(c - d1 + d2 - 1, j); k ++){
                a3 += map[i][k];
            }
        }

        long a4 = 0;
        for(int i = r + d2 + 1, j = c + d2; i < N; i ++, j --){
            for(int k = N - 1; k >= Math.max(c - d1 + d2, j); k --){
                a4 += map[i][k];
            }
        }
        
        long a5 = cnt - a1 - a2 - a3 - a4;
        long max = Math.max(a5, Math.max(a1, Math.max(a2, Math.max(a3, a4))));
        long min = Math.min(a5, Math.min(a1, Math.min(a2, Math.min(a3, a4))));

        return max - min;
    }
}