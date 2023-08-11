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
    public int[][] dirs = {
        { -1, 0 },
        { 0, 1 },
        { 1, 0 },
        { 0, -1 }
    };
    public char[][] map;
    public boolean[][][] v;

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        v = new boolean[2][N][N];
        map = new char[N][N];
        for(int i = 0; i < N; i ++){
            String str = br.readLine();
            for(int j = 0; j < N; j ++){
                map[i][j] = str.charAt(j);
            }
        }

        int[] cnt = new int[2];
        for(int r = 0; r < N; r ++){
            for(int c = 0; c < N; c ++){
                for(int k = 0; k < 2; k ++){
                    if(!v[k][r][c]){
                        cnt[k] ++;
                        v[k][r][c] = true;
                        dfs(k, map[r][c], r, c);
                    }
                }
            }
        }

        System.out.println(cnt[0] + " " + cnt[1]);
    }

    public void dfs(int idx, char ch, int r, int c){
        for(int d = 0; d < 4; d ++){
            int nr = r + dirs[d][0];
            int nc = c + dirs[d][1];
            if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
            if(v[idx][nr][nc]) continue;
            if(idx == 0 && ch != map[nr][nc]) continue;
            if(idx == 1 && ch != 'B' && map[nr][nc] == 'B') continue;
            if(idx == 1 && ch == 'B' && map[nr][nc] != ch) continue;
            v[idx][nr][nc] = true;
            dfs(idx, ch, nr, nc);
        }
    }
}