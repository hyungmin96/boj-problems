import java.util.*;
class Solution {
    
    String answer = "";
    char[] ch = new char[] { 'd', 'l', 'r', 'u' };
    int[][] dirs = {{ 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 0 }};
    int[][] map;
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        map = new int[n + 1][m + 1];
        int diff = Math.abs(x - r) + Math.abs(y - c);
        if((diff % 2 == 0 && k % 2 == 0) || (diff % 2 == 1 && k % 2 == 1)){
            dfs(0, n, m, x, y, r, c, k, x, y, "", diff);
        }
        
        if(answer.equals("")) answer = "impossible";
        return answer;
    }
    
    public boolean dfs(int depth, int n, int m, int x, int y, int r, int c, int k, int cr, int cc, String cur, int diff){
        if(k == 0 && diff == 0){
            answer = cur;
            return true;
        }
            
        for(int d = 0; d < 4; d ++){
            int nr = cr + dirs[d][0];
            int nc = cc + dirs[d][1];
            if(nr > 0 && nc > 0 && nr <= n && nc <= m && !(k < diff)){
                if(dfs(depth + 1, n, m, x, y, r, c, k - 1, nr, nc, cur + ch[d], Math.abs(nr - r) + Math.abs(nc - c))){
                    return true;
                }
            }
        }
        return false;
    }
    
}