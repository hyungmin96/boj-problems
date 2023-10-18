import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException{
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int N, M;
    int[][] map;

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 1; j <= N; j ++){
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num + map[i - 1][j] + map[i][j - 1] - map[i - 1][j - 1];
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());

            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            int min_row = Math.min(r1, r2);
            int min_col = Math.min(c1, c2);

            int total = map[r2][c2] - map[min_row - 1][c2] - map[r2][min_col - 1] + map[min_row - 1][min_col - 1];
            sb.append(total + "\n");
        }

        System.out.println(sb.toString());
    }
}

