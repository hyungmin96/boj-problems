import java.util.*;
class Solution {
    // 현재위치에서 (r, c)까지 남은 거리 k, 맨해튼 거리
    // 남은 거리 k가 홀수이면 짝수번째 방문지점에 방문할 수 없음
    // 남은 거리 k가 짝수이면 홀수번째 방문지점 방문할 수 없음
    int N, M, R, C;
    char[] chs = new char[] { 'd', 'l', 'r', 'u' };
    int[][] dirs = {{1,0},{0,-1},{0,1},{-1,0}};
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        N = n;
        M = m;
        R = r - 1;
        C = c - 1;
        
        return dfs(x - 1, y - 1, k, new StringBuilder());
    }
    
    public String dfs(int r, int c, int k, StringBuilder sb){
        if(r == R && c == C && k == 0){
            return sb.toString();
        }
        
        String ret = "";
        for(int d = 0; d < 4; d ++){
            int nr = r + dirs[d][0];
            int nc = c + dirs[d][1];
            if(isOutRange(nr, nc)){
                continue;
            }
            int dist = Math.abs(nr - R) + Math.abs(nc - C);
            if(dist > k){
                continue;
            }
            if((dist % 2 == 0 && (k - 1) % 2 == 0) || (dist % 2 != 0 && (k - 1) % 2 != 0)){
                sb.append(chs[d]);
                if(ret.length() == 0){
                    ret = dfs(nr, nc, k - 1, sb);    
                }
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        return ret.equals("") ? "impossible" : ret;
    }
    
    public boolean isOutRange(int r, int c){
        return r < 0 || c < 0 || r >= N || c >= M;
    }
}