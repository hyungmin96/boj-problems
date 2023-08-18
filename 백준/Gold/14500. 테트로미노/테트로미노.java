import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int N, M, answer = 0;
    int[][] map;
    int[][] dirs = {
        { -1, 0 },
        { 0, 1 },
        { 1, 0 },
        { 0, -1 }
    };
    boolean[][] v;

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        v = new boolean[N][M];
        map = new int[N][M];

        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < M; j ++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i ++){
            for(int j = 0; j < M; j ++){
                v[i][j] = true;
                dfs(0, i, j, map[i][j]);
                v[i][j] = false;
                answer = Math.max(answer, other(i, j));
            }
        }

        System.out.println(answer);
    }

    public void dfs(int depth, int r, int c, int cur){
        if(depth == 3){
            answer = Math.max(answer, cur);
            return ;
        }
        for(int d = 0; d < 4; d ++){
            int nr = r + dirs[d][0];
            int nc = c + dirs[d][1];
            if(nr < 0 || nc < 0 || nr >= N || nc >= M || v[nr][nc]) continue;
            v[nr][nc] = true;
            dfs(depth + 1, nr, nc, cur + map[nr][nc]);
            v[nr][nc] = false;
        }
    }

    public int other(int r, int c){
        int ret = 0;
        //"ㅏ"
        if(r - 1 >= 0 && r + 1 < N && c - 1 >= 0){
            int tmp = map[r][c] + map[r - 1][c - 1] + map[r + 1][c - 1] + map[r][c - 1];
            ret = Math.max(ret, tmp);
        }

        //"ㅗ"
        if(r + 1 < N && c - 1 >= 0 && c + 1 < M){
            int tmp = map[r][c] + map[r + 1][c - 1] + map[r + 1][c + 1] + map[r + 1][c];
            ret = Math.max(ret, tmp);
        }

        //"ㅓ"
        if(r - 1 >= 0 && r + 1 < N && c + 1 < M){
            int tmp = map[r][c] + map[r - 1][c + 1] + map[r + 1][c + 1] + map[r][c + 1];
            ret = Math.max(ret, tmp);
        }

        //"ㅜ"
        if(r - 1 >= 0 && c - 1 >= 0 && c + 1 < M){
            int tmp = map[r][c] + map[r - 1][c - 1] + map[r - 1][c] + map[r - 1][c + 1];
            ret = Math.max(ret, tmp);
        }
        return ret;
    }
}