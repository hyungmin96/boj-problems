import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {

    private static int[][] board;
    private static Loc[] destination;
    private static int[] dx = new int[] {-1, 0, 1, 0 };
    private static int[] dy = new int[] { 0, 1, 0,-1 };
    private static int N, M, K;
    private static Taxi ct;

    public static class Loc implements Comparable<Loc>{
        int id, sx, sy, ex, ey, dis;
        public Loc(int id, int sx, int sy, int ex, int ey, int dis){
            this.id = id;
            this.sx = sx;
            this.sy = sy;
            this.ex = ex;
            this.ey = ey;
            this.dis = dis;
        }
        @Override
        public int compareTo(Main.Loc o) {
            if(this.dis != o.dis)
                return this.dis - o.dis;
            else if(this.dis == o.dis && this.sx != o.sx) 
                return this.sx - o.sx;
            else return this.sy - o.sy;
        }
    }

    public static class Taxi{
        int x, y, fuel, time;
        public Taxi(int x, int y, int fuel, int time){
            this.x = x;
            this.y = y;
            this.fuel = fuel;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        board = new int[N][N];
        destination = new Loc[M];

        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine());
            int index = 0;
            while(st.hasMoreTokens()){
                board[i][index++] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;

        for(int i = 0; i < M; i ++){
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken()) - 1;
            int sy = Integer.parseInt(st.nextToken()) - 1;
            int ex = Integer.parseInt(st.nextToken()) - 1;
            int ey = Integer.parseInt(st.nextToken()) - 1;
            destination[i] = new Loc(i, sx, sy, ex, ey, Integer.MAX_VALUE);
            board[sx][sy] = i + 2;
        }

        System.out.println(solution(x, y));
    }

    public static int solution(int x, int y){
        ct = new Taxi(x, y, K, 0);
        for(int i = 0; i < destination.length; i ++){
            Loc close_client = findClient(ct.x, ct.y);
            if(close_client == null) return -1;
            for(int j = 0; j < 2; j ++){
                int tx = (j == 0) ? close_client.sx : close_client.ex;
                int ty = (j == 0) ? close_client.sy : close_client.ey;
                boolean isDes = (j == 0) ? false : true;
                int dis = moveTaxi(tx, ty, isDes);
                if(dis == -1){
                    return -1;
                }else{
                    K -= dis;
                    if(isDes) K += dis * 2;
                }
                if(K < 0) 
                    return -1;
            }
            destination[close_client.id] = null;
        }
        return K;
    }

    public static int moveTaxi(int tx, int ty, boolean isDes){
        boolean[][] visited = new boolean[N][N];
        Queue<Taxi> que = new LinkedList<>();
        board[ct.x][ct.y] = 0;
        visited[ct.x][ct.y] = true;
        que.offer(new Taxi(ct.x, ct.y, K - 1, 0));
        
        if(ct.x == tx && ct.y == ty)
            return 0;

        while(!que.isEmpty()){
            Taxi c = que.poll();
            for(int  i = 0; i < dx.length; i ++){
                int nr = c.x + dx[i];
                int nc = c.y + dy[i];
                if(c.fuel < 0 || nr < 0 || nc < 0 || nr >= N || nc >= N || board[nr][nc] == 1 || visited[nr][nc]){
                    continue;
                }
                que.offer(new Taxi(nr, nc, c.fuel - 1, c.time + 1));
                visited[nr][nc] = true;
                if(nr == tx && nc == ty){
                    c.x = nr;
                    c.y = nc;
                    ct = c;
                    return c.time + 1;
                }
            }
        }
        return -1;
    }

    public static Loc findClient(int cx, int cy) {
        Queue<int[]> que = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        ArrayList<Loc> destinations = new ArrayList<>();
        que.offer(new int[] { cx, cy, 0 });
        visited[cx][cy] = true;

        if(board[cx][cy] >= 2) return destination[board[cx][cy] - 2];

        while(!que.isEmpty()){
            int[] curr = que.poll();
            for(int i = 0; i < dx.length; i ++){
                int nr = curr[0] + dx[i];
                int nc = curr[1] + dy[i];
                if(nr < 0 || nc < 0 || nr >= N || nc >= N || board[nr][nc] == 1 || visited[nr][nc]){
                    continue;
                }
                que.offer(new int[] { nr, nc, curr[2] + 1});
                visited[nr][nc] = true;
                if(board[nr][nc] >= 2){
                    if(destination[board[nr][nc] - 2] != null){
                        destination[board[nr][nc] - 2].dis = curr[2] + 1;
                        destinations.add(destination[board[nr][nc] - 2]);
                    }
                }
            }
        }

        Collections.sort(destinations);
        if(destinations.size() > 0)
            return destinations.get(0);
        else
            return null;
    }
}
