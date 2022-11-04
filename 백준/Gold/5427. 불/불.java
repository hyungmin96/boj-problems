import java.io.*;
import java.util.*;

public class Main {

    static int[][] dirs = {
        { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, - 1 }
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        for(int i = 0; i < tc; i ++){
            Queue<int[]> que = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int c = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            char[][] map = new char[r][c];
            int[] pos = new int[4];
            for(int j = 0; j < r; j ++){
                String str = br.readLine();
                for(int k = 0; k < c; k ++){
                    char ch = str.charAt(k);
                    map[j][k] = ch;
                    if(ch == '@') pos = new int[] { j, k, 0, 0 };
                    else if(ch == '*') que.offer(new int[] { j, k, 0, 1 });
                }
            }
            que.offer(pos);
            int ret = bfs(r, c, que, map);
            sb.append(ret == -1 ? "IMPOSSIBLE\n" : ret + "\n");
        }
        System.out.println(sb.toString());
    }

    public static int bfs(int r, int c, Queue<int[]> que, char[][] map){
        boolean[][] check = new boolean[r][c];
        while(!que.isEmpty()){
            int[] cur = que.poll();
            int type = cur[3];
            check[cur[0]][cur[1]] = true;
            
            for(int d = 0; d < 4; d ++){
                int nr = cur[0] + dirs[d][0];
                int nc = cur[1] + dirs[d][1];
                if(type == 0 && !isRange(r, c, nr, nc)) return cur[2] + 1;
                if(!isRange(r, c, nr, nc) || check[nr][nc] || map[nr][nc] == '#') continue;
                if(type == 0 && map[nr][nc] == '*') continue;
                check[nr][nc] = true;
                que.offer(new int[] { nr, nc, cur[2] + 1, type});
            }
        }
        return -1;
    }

    public static boolean isRange(int r, int c, int i, int j){
        return !(i < 0 || j < 0 || i >= r || j >= c);
    }
}
