import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[][] dirs = {{ -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }}, map;
    static int[] start = new int[2], end = new int[2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine(), " ");
        start[0] = Integer.parseInt(st.nextToken()) - 1;
        start[1] = Integer.parseInt(st.nextToken()) - 1;

        st = new StringTokenizer(br.readLine(), " ");
        end[0] = Integer.parseInt(st.nextToken()) - 1;
        end[1] = Integer.parseInt(st.nextToken()) - 1;


        map = new int[n][m];
        for(int i = 0; i < n; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < m; j ++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());
    }

    public static int bfs(){
        boolean[][][] check = new boolean[2][n + 1][m + 1];
        Queue<int[]> que = new LinkedList<>();
        
        // 현재위치 (x, y), 무기사용여부, time
        que.offer(new int[] { start[0], start[1], 0, 0 });
        check[0][start[0]][start[1]] = true;
        while(!que.isEmpty()){
            int[] cur = que.poll();
            if(cur[0] == end[0] && cur[1] == end[1]) return cur[3];
            for(int[] d : dirs){
                boolean flag = false;
                int isUsedWeapon = cur[2];
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                if(!isRange(nr, nc) || check[isUsedWeapon][nr][nc]) continue;
                if(map[nr][nc] == 1 && isUsedWeapon == 0){
                    flag = true;
                    isUsedWeapon = 1;
                }

                if(map[nr][nc] == 0 || flag){
                    check[isUsedWeapon][nr][nc] = true;
                    que.offer(new int[] { nr, nc, isUsedWeapon, cur[3] + 1 });
                }
            }
        }

        return -1;
    }

    public static boolean isRange(int x, int y){
        return !(x < 0 || y < 0 || x >= n || y >= m);
    }
}