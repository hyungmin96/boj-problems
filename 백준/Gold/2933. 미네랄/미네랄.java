import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int R, C;
    char[][] map;
    int[][] dirs = {
        { -1, 0 },
        { 0, 1 },
        { 1, 0 },
        { 0, -1 },
    };

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for(int i = 0; i < R; i ++){
            String str = br.readLine();
            for(int j = 0; j < C; j ++){
                char ch = str.charAt(j);
                map[i][j] = ch;
            }
        }

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i <= n; i ++){
            char[][] tmp_map = new char[R][C];
            int height = R - Integer.parseInt(st.nextToken());
            throwsSpear(i, height);   
            clustering(tmp_map);

            for(int r = 0; r < R; r ++){
                for(int c = 0; c < C; c ++){
                    if(tmp_map[r][c] != 'x')
                        map[r][c] = '.';
                    else
                        map[r][c] = tmp_map[r][c];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int r = 0; r < R; r ++){
            for(int c = 0; c < C; c ++){
                sb.append(map[r][c]);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public void clustering(char[][] tmp_map){
        for(int r = R - 1; r >= 0; r --){
            for(int c = 0; c < C; c ++){
                if(map[r][c] == 'x'){
                    // 해당 열의 열 번호가 가장 큰 정보를 저장
                    ArrayList<int[]> tmp = new ArrayList<>();
                    HashMap<Integer, Integer> cols_min_row = new HashMap<>();
                    bfs(r, c, tmp, cols_min_row);
                    moveCluster(tmp_map, tmp, cols_min_row);
                }
            }
        }
    }

    public void moveCluster(char[][] tmp_map, ArrayList<int[]> tmp, HashMap<Integer, Integer> cols_min_row){
        int result_move = 987654321;
        for(int col : cols_min_row.keySet()){
            int cnt = 0;
            int s = cols_min_row.get(col);
            for(int r = s + 1; r < R; r ++){
                if(r >= R || tmp_map[r][col] == 'x'){
                    break;
                }
                cnt ++;
            }
            result_move = Math.min(cnt, result_move);
        }

        for(int[] p : tmp){
            tmp_map[p[0] + result_move][p[1]] = 'x';
        }
    }

    public void bfs(int r, int c, ArrayList<int[]> tmp, HashMap<Integer, Integer> cols_min_row) {
        boolean[][] chk = new boolean[R][C];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { r, c });
        chk[r][c] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            map[cur[0]][cur[1]] = '.';
            tmp.add(new int[] { cur[0], cur[1] });
            if (cols_min_row.containsKey(cur[1])) {
                if (cols_min_row.get(cur[1]) < cur[0]) {
                    cols_min_row.put(cur[1], cur[0]);
                }
            } else {
                cols_min_row.put(cur[1], cur[0]);
            }

            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dirs[d][0];
                int nc = cur[1] + dirs[d][1];
                if (nr < 0 || nc < 0 || nr >= R || nc >= C || chk[nr][nc] || map[nr][nc] == '.')
                    continue;
                chk[nr][nc] = true;
                q.offer(new int[] { nr, nc });
            }
        }
    }

    public void throwsSpear(int order, int height) {
        if (order % 2 != 0) {
            // 왼쪽에서 던지기
            for (int c = 0; c < C; c++) {
                if (map[height][c] != '.') {
                    map[height][c] = '.';
                    break;
                }
            }
        } else {
            // 오른쪽에서 던지기
            for (int c = C - 1; c >= 0; c--) {
                if (map[height][c] != '.') {
                    map[height][c] = '.';
                    break;
                }
            }
        }
    }
}

