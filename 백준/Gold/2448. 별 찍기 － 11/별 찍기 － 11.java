import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Solution sol = new Solution();
        sol.solution(n);
    }
}

class Solution {
    public void solution(int n){
        char[][] map = new char[n][(2 * n) - 1];
        dfs(0, n - 1, n, map);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i ++){
            for(int j = 0; j < 2 * n - 1; j ++){
                if(map[i][j] == '*')
                    sb.append(map[i][j]);
                else
                    sb.append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }

    public void dfs(int r, int c, int len, char[][] map){
        len = len / 2;
        if(len == 1){
            map[r][c] = '*';
            map[r + 1][c - 1] = '*';
            map[r + 1][c + 1] = '*';
            for(int i = 0; i < 5; i ++){
                map[r + 2][i + c - 2] = '*';
            }
            return;
        }
        dfs(r, c, len, map);
        dfs(r + len, c - len, len, map);
        dfs(r + len, c + len, len, map);
    }
}