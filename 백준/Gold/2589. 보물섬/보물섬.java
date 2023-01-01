import java.io.*;
import java.util.*;

public class Main {

    static int[][] dirs = {
        { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 }
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<int[]> list = new ArrayList<>();
        char[][] map = new char[n][m];
        for(int i = 0; i < n; i ++){
            String str = br.readLine();
            for(int j = 0; j < m; j ++){
                char c = str.charAt(j);;
                map[i][j] = c;
                if(c == 'L')
                    list.add(new int[] { i, j });
            }
        }

        System.out.println(bfs(list, n, m, map));
    }

    public static int bfs(ArrayList<int[]> list, int n, int m, char[][] map){
        int ret = 0;
        Queue<int[]> que = new LinkedList<>();
        for(int[] d : list){
            boolean[][] check = new boolean[n][m];
            check[d[0]][d[1]] = true;
            que.offer(new int[] { d[0], d[1], 0 });
            while(!que.isEmpty()){
                int[] cur = que.poll();
                ret = Math.max(cur[2], ret);
                for(int[] dir : dirs){
                    int nr = cur[0] + dir[0];
                    int nc = cur[1] + dir[1];
                    if(nr < 0 || nc < 0 || nr >= n || nc >= m) continue;
                    if(check[nr][nc]) continue;
                    if(map[nr][nc] == 'W') continue;

                    check[nr][nc] = true;
                    que.offer(new int[] { nr, nc, cur[2] + 1 });
                }
            }
        }

        return ret;
    }
}