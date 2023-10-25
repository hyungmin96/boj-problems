import java.util.*;
class Solution {
    
    class Pair{
        int r, c, cnt;
        String path;
        public Pair(int r, int c, int cnt, String path){
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.path = path;
        }
    }
    boolean flag = false;
    String answer = "impossible";
    int[][] v;
    char[] ch = new char[] { 'd', 'l', 'r', 'u' };
    int[][] dirs = {{ 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 0 }};
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        v = new int[n + 1][m + 1];
        dfs(n, m, x, y, r, c, k, "");
        return answer;
    }
    
    public void dfs(int n, int m, int x, int y, int r, int c, int k, String cur){
        if(flag || k < 0) return;
        if(k == 0 && x == r && y == c){
            answer = cur;
            flag = true;
            return;
        }
        for(int d = 0; d < 4; d ++){
            int nr = x + dirs[d][0];
            int nc = y + dirs[d][1];
            if(isOutOfRange(n, m, nr, nc)) continue;
            int dist = Math.abs(r - nr) + Math.abs(c - nc);
            if(k - 1 >= dist && (dist % 2) - (k - 1) % 2 == 0){
                dfs(n, m, nr, nc, r, c, k - 1, cur + ch[d]);
            }
        }
    }
    
    public boolean isOutOfRange(int n, int m, int r, int c){
        return r < 1 || c < 1 || r > n || c > m;
    }
}