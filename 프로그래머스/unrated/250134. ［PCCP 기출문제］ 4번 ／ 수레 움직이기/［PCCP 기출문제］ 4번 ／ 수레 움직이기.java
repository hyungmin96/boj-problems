import java.util.*;
class Solution {
    class Cart{
        int red_r, red_c, blue_r, blue_c, cnt;
        public Cart(int red_r, int red_c, int blue_r, int blue_c, int cnt){
            this.red_r = red_r;
            this.red_c = red_c;
            this.blue_r = blue_r;
            this.blue_c = blue_c;
            this.cnt = cnt;
        }
    }
    int N, M;
    int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};
    int[][] end = new int[2][2];
    
    boolean[][] red_v;
    boolean[][] blue_v;
    int answer = Integer.MAX_VALUE;
    
    public int solution(int[][] maze) {
        N = maze.length;
        M = maze[0].length;
        
        red_v = new boolean[N][M];
        blue_v = new boolean[N][M];
        
        Cart cart = new Cart(-1, -1, -1, -1, 0);
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < M; j ++){
                if(maze[i][j] == 1){
                    cart.red_r = i;
                    cart.red_c = j;
                }else if(maze[i][j] == 2){
                    cart.blue_r = i;
                    cart.blue_c = j;
                }else if(maze[i][j] == 3){
                    end[0] = new int[] { i, j };
                }else if(maze[i][j] == 4){
                    end[1] = new int[] { i, j };
                }
            }
        }
        
        red_v[cart.red_r][cart.red_c] = true;
        blue_v[cart.blue_r][cart.blue_c] = true;
        
        dfs(0, cart.red_r, cart.red_c, cart.blue_r, cart.blue_c, maze);
        
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
    
    public void dfs(int depth, int red_r, int red_c, int blue_r, int blue_c, int[][] maze){
        if(answer <= depth) return;
        if(isValid(red_r, red_c, blue_r, blue_c)){
            answer = Math.min(answer, depth);
            return;
        }
        
        for(int d1 = 0; d1 < 4; d1 ++){
            for(int d2 = 0; d2 < 4; d2 ++){
                
                int nrr = red_r;
                int nrc = red_c;

                int nbr = blue_r;
                int nbc = blue_c;
                
                boolean red_alive = false;
                boolean blue_alive = false;
                if(!(red_r == end[0][0] && red_c == end[0][1])){
                    nrr += dirs[d1][0];
                    nrc += dirs[d1][1];    
                }else{
                    red_alive = true;
                }

                if(!(blue_r == end[1][0] && blue_c == end[1][1])){
                    nbr += dirs[d2][0];
                    nbc += dirs[d2][1];    
                }else{
                    blue_alive = true;
                }
                
                if(isOutRange(nrr, nrc, nbr, nbc)) continue;
                if(nrr == nbr && nrc == nbc) continue;
                if((!red_alive && red_v[nrr][nrc]) || (!blue_alive && blue_v[nbr][nbc])) continue;
                if(nrr == blue_r && nrc == blue_c && nbr == red_r && nbc == red_c) continue;
                if(maze[nrr][nrc] == 5 || maze[nbr][nbc] == 5) continue;
                
                red_v[nrr][nrc] = true;
                blue_v[nbr][nbc] = true;
                dfs(depth + 1, nrr, nrc, nbr, nbc, maze);
                
                red_v[nrr][nrc] = false;
                blue_v[nbr][nbc] = false;
                
                nrr = red_r;
                nrc = red_c;
                nbr = blue_r;
                nbc = blue_c;
            }
        }
    }
    
    public boolean isOutRange(int rr, int rc, int br, int bc){
        if(rr < 0 || br < 0 || rc < 0 || bc < 0) return true;
        if(rr >= N || br >= N || rc >= M || bc >= M) return true;
        return false;
    }
    
    public boolean isValid(int red_r, int red_c, int blue_r, int blue_c){
        if(red_r == end[0][0] && red_c == end[0][1]){
            if(blue_r == end[1][0] && blue_c == end[1][1]){
                return true;
            }
        }
        
        return false;
    }
}