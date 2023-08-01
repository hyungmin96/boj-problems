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
    
    public char[][] map;

    public void solution(int n){
        int width = 4 * (n - 1) + 1;
        int height = 4 * n - 1;
        map = new char[height][width];

        if(n == 1){
            System.out.println("*");
            return;
        }
        dfs1(n, 0, 0, width, true);
        dfs2(n, height - 1, width - 1);
        StringBuilder sb = new StringBuilder();
        for(int r = 0; r < height; r ++){
            for(int c = 0; c < width; c ++){
                if(r == 1 && c == 1) break;
                if(map[r][c] == '*')
                    sb.append(map[r][c]);
                else
                    sb.append(' ');
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public void dfs1(int depth, int r, int c, int w, boolean flag){
        if(depth == 0){
            map[r][c] = '*';
            return;
        }

        for(int i = 0; i < w; i ++){
            map[r][c + i] = '*';
        }

        for(int i = 0; i < 4 * depth - 1; i ++){
            map[r + i][c] = '*';
        }

        if(flag)
            dfs1(depth - 1, r + 2, c + 2, w - 2, false);
        else
            dfs1(depth - 1, r + 2, c + 2, w - 4, false);
    }

    public void dfs2(int depth, int r, int c){
        if(depth == 0){
            map[r][c] = '*';
            return;
        }

        for(int i = 0; i < 4 * (depth - 1) + 1; i ++){
            map[r][c - i] = '*';
            map[r - i][c] = '*';
        }

        dfs2(depth - 1, r - 2, c - 2);
    }
}