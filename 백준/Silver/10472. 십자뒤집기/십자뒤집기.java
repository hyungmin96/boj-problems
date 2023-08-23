import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int P;
    char[][] map = new char[3][3];
    int[][] dirs = {{ -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }, { 0, 0 }};

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        P = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(P -- > 0){
            for(int i = 0; i < 3; i ++){
                String str = br.readLine();
                for(int j = 0; j < 3; j ++){
                    map[i][j] = str.charAt(j);
                }
            }
            
            int min = dfs(0, 0);
            sb.append(min + "\n");
        }

        System.out.println(sb.toString());
    }

    public int dfs(int r, int c){
        int result = 987654321;
        if(r >= 3){
            for(int i = 0; i < 3; i ++){
                for(int j = 0; j < 3; j ++){
                    if(map[i][j] == '*'){
                        return 987654321;
                    }
                }
            }
            return 0;
        }

        int nr = r, nc = c + 1;
        if(nc >= 3){
            nr = r + 1;
            nc = 0;
        }

        result = Math.min(result, dfs(nr, nc));
        click(r, c);

        result = Math.min(result, dfs(nr, nc) + 1);
        click(r, c);

        return result;
    }

    public void click(int r, int c){
        for(int d = 0; d < 5; d ++){
            int nr = r + dirs[d][0];
            int nc = c + dirs[d][1];
            if(nr < 0 || nc < 0 || nr >= 3 || nc >= 3) continue;
            map[nr][nc] = (map[nr][nc] == '.') ? '*' : '.';
        }
    }
}

