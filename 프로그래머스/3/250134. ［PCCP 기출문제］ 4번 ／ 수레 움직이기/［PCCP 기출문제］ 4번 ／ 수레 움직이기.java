class Solution {
    int N, M;
    int[] red_end = new int[2], blue_end = new int[2]; // 각 수레의 도착위치
    boolean[][] red_v, blue_v; // 각 수레의 방문처리
    int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};
    public int solution(int[][] maze) {
        int answer = 0;
        int[] red = new int[2], blue = new int[2];
        N = maze.length;
        M = maze[0].length;
        red_v = new boolean[N][M];
        blue_v = new boolean[N][M];
        for(int r = 0; r < maze.length; r ++){
            for(int c = 0; c < maze[r].length; c ++){
                if(maze[r][c] == 1){
                    red_v[r][c] = true;
                    red = new int[] { r, c };
                }else if(maze[r][c] == 2){
                    blue_v[r][c] = true;
                    blue = new int[] { r, c };
                }else if(maze[r][c] == 3){
                    red_end = new int[] { r, c };
                }else if(maze[r][c] == 4){
                    blue_end = new int[] { r, c };
                }
            }
        }
        answer = dfs(0, red, blue, maze);
        return answer == 987654321 ? 0 : answer;
    }
    
    public int dfs(int depth, int[] red, int[] blue, int[][] maze){
        if(red[0] == red_end[0] && red[1] == red_end[1]){
            if(blue[0] == blue_end[0] && blue[1] == blue_end[1]){
                return depth;
            }
        }
        
        int ret = 987654321;
        for(int d1 = 0; d1 < 4; d1 ++){
            for(int d2 = 0; d2 < 4; d2 ++){
                // 빨간수레의 다음 이동위치
                int nrr = red[0] + dirs[d1][0];
                int nrc = red[1] + dirs[d1][1];
                // 파란수레의 다음 이동위치
                int nbr = blue[0] + dirs[d2][0];
                int nbc = blue[1] + dirs[d2][1];
                
                if(red[0] == red_end[0] && red[1] == red_end[1]){
                    nrr = red[0];
                    nrc = red[1];
                }
                
                if(blue[0] == blue_end[0] && blue[1] == blue_end[1]){
                    nbr = blue[0];
                    nbc = blue[1];
                }
                
                // 격자밖을 벗어나는 경우
                if(isOutRange(nrr, nrc) || isOutRange(nbr, nbc)) continue;
                
                // 다음 위치가 벽인 경우
                if(maze[nrr][nrc] == 5 || maze[nbr][nbc] == 5) continue;
                
                // 1. 해당 수레의 도착지점이 아니면서 이미 방문한 위치인 경우.
                if(!(nrr == red_end[0] && nrc == red_end[1])){
                    if(red_v[nrr][nrc]) {
                        continue;
                    }
                }
                
                if(!(nbr == blue_end[0] && nbc == blue_end[1])){
                    if(blue_v[nbr][nbc]){
                        continue;
                    }
                }
                
                // 2. 두 수레가 동시에 같은 칸으로 이동하는 경우
                if(nrr == nbr && nrc == nbc) {
                    continue;
                }
                
                // 3. 수레가 서로 교차하는 경우
                if(nrr == blue[0] && nrc == blue[1] && nbr == red[0] && nbc == red[1]){
                    continue;
                }
                
                red_v[nrr][nrc] = true;
                blue_v[nbr][nbc] = true;
                ret = Math.min(ret, dfs(depth + 1, new int[] { nrr, nrc }, new int[] { nbr, nbc }, maze));
                red_v[nrr][nrc] = false;
                blue_v[nbr][nbc] = false;
            }
        }
        
        return ret;
    }
    
    public boolean isOutRange(int r, int c){
        return r < 0 || c < 0 || r >= N || c >= M;
    }
}