import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int W, H;
    char[][] map;
    int[] start = new int[2], end = new int[2];
    int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new char[H][W];

        boolean flag = false;
        for(int r = 0; r < H; r ++){
            String str = br.readLine();
            for(int c = 0; c < W; c ++){
                map[r][c] = str.charAt(c);
                if(!flag && map[r][c] == 'C'){
                    flag = true;
                    start = new int[] { r, c };
                }else if(flag && map[r][c] == 'C'){
                    end = new int[] { r, c };
                }
            }
        }

        System.out.println(bfs());
    }

    public int bfs(){
        int ret = 987654321;
        int[][][] v = new int[4][H][W];
        for(int j = 0; j < 4; j ++){
            for(int i = 0; i < H; i ++){
                Arrays.fill(v[j][i], 987654321);
            }
        }
        // [r, c, cnt, dir, flag]
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[2] - o2[2];
            }
        });

        pq.offer(new int[] { start[0], start[1], 0, -1, 1 });
        v[0][start[0]][start[1]] = 0;
        v[1][start[0]][start[1]] = 0;
        v[2][start[0]][start[1]] = 0;
        v[3][start[0]][start[1]] = 0;

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            if(cur[0] == end[0] && cur[1] == end[1]){
                ret = Math.min(ret, cur[2]);
                continue;
            }
            for(int d = 0; d < 4; d ++){
                int cnt = cur[2];
                int nr = cur[0] + dirs[d][0];
                int nc = cur[1] + dirs[d][1];
                if(isOutRange(nr, nc) || v[d][nr][nc] <= cur[2] || map[nr][nc] == '*') continue;
                // 출발지점이 아니고 현재 방향에서 변경해야할 경우 count
                if(cur[4] == 0 && cur[3] != d){
                    cnt ++;
                }

                v[d][nr][nc] = cnt;
                pq.offer(new int[] { nr, nc, cnt, d, 0 });
            }
        }

        return ret;
    }

    public boolean isOutRange(int r, int c){
        return r < 0 || c < 0 || r >= H || c >= W;
    }
}

