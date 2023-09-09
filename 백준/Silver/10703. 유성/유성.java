import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int R, S;
    char[][] map;
    boolean[][] v;
    int[][] dirs = {{ -1, 0 },{ 0, 1 },{ 1, 0 },{ 0, -1 }};

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        v = new boolean[R][S];
        map = new char[R][S];
        int[] start = new int[2];
        for(int i = 0; i < R; i ++){
            String str = br.readLine();
            for(int j = 0; j < S; j ++){
                map[i][j] = str.charAt(j);
                if(str.charAt(j) == 'X'){
                    start = new int[] { i, j };
                }
            }
        }

        bfs(start[0], start[1]);
        StringBuilder sb = new StringBuilder();
        for(int r = 0; r < R; r ++){
            for(int c = 0; c < S; c ++){
                sb.append(map[r][c]);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public void bfs(int r, int c){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { r, c });
        v[r][c] = true;

        int dist = 987654321;
        ArrayList<int[]> pos = new ArrayList<>();
        while(!q.isEmpty()){
            int[] cur = q.poll();
            pos.add(cur);
            for(int d = 0; d < 4; d ++){
                int nr = cur[0] + dirs[d][0];
                int nc = cur[1] + dirs[d][1];
                if(nr < 0 || nc < 0 || nr >= R || nc >= S || v[nr][nc]) continue;
                if(map[nr][nc] == '.' || map[nr][nc] == '#') continue;

                v[nr][nc] = true;
                q.offer(new int[] { nr, nc });

            }
            int tmp_dist = 0;
            for(int i = cur[0] + 1; i < R; i ++){
                if(map[i][cur[1]] == 'X') {
                    tmp_dist = 3001;
                    break;
                }
                if(map[i][cur[1]] == '.'){
                    tmp_dist ++;
                }else break;
            }

            dist = Math.min(tmp_dist, dist);
        }

        for(int i = 0; i < pos.size(); i ++){
            int[] p = pos.get(i);
            map[p[0]][p[1]] = '.';
            map[p[0] + dist][p[1]] = 'X';
        }
    }
}