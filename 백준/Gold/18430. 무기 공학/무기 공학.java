import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    public int N, M;
    public long ret = 0;
    public int[][] map;
    public int[][] tools = {
        { -1, 1 },
        { 1, -1 },
        { -1, -1 },
        { 1, 1 }
    };
    public boolean[][] v;

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        v = new boolean[N][M];
        map = new int[N][M];

        for(int r = 0; r < N; r ++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int c = 0; c < M; c ++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
        System.out.println(ret);
    }

    public void dfs(int depth, int cur){
        if(depth == N * M){
            ret = Math.max(ret, cur);
            return;
        }

        for(int i = 0; i < 4; i ++){
            int r = depth / M;
            int c = depth % M;

            if(r + tools[i][0] < 0 || c + tools[i][1] < 0 || r + tools[i][0] >= N || c + tools[i][1] >= M) continue;
            if(v[r][c]) continue;
            if(v[r + tools[i][0]][c]) continue;
            if(v[r][c + tools[i][1]]) continue;
            int cur_score = map[r][c] * 2 + map[r + tools[i][0]][c] + map[r][c + tools[i][1]];

            v[r][c] = true;
            v[r + tools[i][0]][c] = true;
            v[r][c + tools[i][1]] = true;
            dfs(depth + 1, cur + cur_score);
            v[r][c] = false;
            v[r + tools[i][0]][c] = false;
            v[r][c + tools[i][1]] = false;
        }

        dfs(depth + 1, cur);
    }
}