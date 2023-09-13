import java.util.*;
class Solution {
    // d, l, r, u 순서
    int max_r = 0, max_c = 0;
    int[] s = new int[2], e = new int[2];
    int[][] dirs = {{ 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 0 }};
    char[] ch = new char[] { 'd', 'l', 'r', 'u' };
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "impossible";
        
        x -= 1;
        y -= 1;
        r -= 1;
        c -= 1;
        max_r = n;
        max_c = m;
        s = new int[] { x, y };
        e = new int[] { r, c };
        
        ArrayList<Character> list = new ArrayList<>();
        int d = getDistance(x, y, r, c);
        if((d % 2 == 0 && k % 2 == 0) || (d % 2 != 0 && k % 2 != 0)){
            if(dfs(0, s[0], s[1], k, list)){
                answer = "";
                for(char cha : list){
                    answer += cha;
                }
            }
        }
        
        return answer;
    }
    
    public boolean dfs(int depth, int r, int c, int k, ArrayList<Character> list){
        if(getDistance(r, c, e[0], e[1]) > k - depth){
            return false;
        }
        
        if(r == e[0] && c == e[1] && depth == k){
            return true;
        }
        
        for(int d = 0; d < 4; d ++){
            int nr = r + dirs[d][0];
            int nc = c + dirs[d][1];
            if(isOutRange(nr, nc)) continue;
            list.add(ch[d]);
            if(dfs(depth + 1, nr, nc, k, list)) return true;
            list.remove(list.size() - 1);
        }
        
        return false;
    }
    
    public boolean isOutRange(int r, int c){
        return r < 0 || c < 0 || r >= max_r || c >= max_c;
    }
    
    public int getDistance(int r, int c, int dr, int dc){
        return Math.abs(r - dr) + Math.abs(c - dc);
    }
}