import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {

    public static class Heater{
        int id, r, c;
        public Heater(int id, int r, int c){
            this.id = id;
            this.r = r;
            this.c = c;
        }
    }

    public static class MapInfo{
        int id, r, c, t;
        boolean[] w = new boolean[2];
        public MapInfo(int id, int r, int c, int t, boolean[] w){
            this.id = id;
            this.r = r;
            this.c = c;
            this.t = t;
            this.w = w;
        }
    }

    private static int[] dx = new int[] { 0, 0, 0,-1, 1 };
    private static int[] dy = new int[] { 0, 1,-1, 0, 0 };
    private static int N, M, K;
    private static MapInfo[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new MapInfo[N][M];
        ArrayList<int[]> investigations = new ArrayList<>();
        ArrayList<Heater> heaters = new ArrayList<>();
        
        for(int i = 0; i < N; i ++){
            int idx = 0;
            st = new StringTokenizer(br.readLine(), " ");
            while(st.hasMoreTokens()){
                int in = Integer.parseInt(st.nextToken());
                if(in == 5){
                    investigations.add(new int[] { i, idx });
                }else if(in > 0 && in < 5){
                    heaters.add(new Heater(in, i, idx));
                }
                board[i][idx++] = new MapInfo(-1, i, idx, 0, new boolean[2]);
            }
        }
        int w = Integer.parseInt(br.readLine());
        for(int i = 0; i < w; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken());
            board[r][c].w[t] = true;
        }
        System.out.println(solution(investigations, heaters));
    }

    public static int solution(ArrayList<int[]> investigations, ArrayList<Heater> heaters){
        for(int i = 1; i <= 100; i ++){
            operateHeater(heaters);
            adjustTempeture();
            adjustSuburbTempeture();
            if(investigateSpace(investigations)){
                return i;
            }
        }
        return 101;
    }

    public static void adjustTempeture(){
        int[][] temp = new int[N][M];
        for(int r = 0; r < N; r ++){
            for(int c = 0; c < M; c ++){
                for(int d = 1; d < dx.length; d ++){
                    int nr = r + dx[d];
                    int nc = c + dy[d];
                    if(nr < 0 || nc < 0 || nr >= N || nc >= M || !checkWall(d, r, c, nr, nc) || board[r][c].t <= board[nr][nc].t){
                        continue;
                    }
                    if(board[r][c].t / 4 >= 1){
                        int val = (board[r][c].t - board[nr][nc].t) / 4;
                        temp[r][c] -= val;
                        temp[nr][nc] += val;
                    }
                }
            }
        }
        for(int r = 0; r < N; r ++){
            for(int c = 0; c < M; c ++){
                board[r][c].t += temp[r][c];
            }
        }
    }

    public static void operateHeater(ArrayList<Heater> heaters){
        int[][][] diffusion = new int[][][]{
            { {0}, {0} },
            { { 0,-1, 1 }, { 1, 1, 1 } },
            { {-1, 0, 1 }, {-1,-1,-1 } },
            { {-1,-1,-1 }, { 0,-1, 1 } },
            { { 1, 1, 1 }, { 0,-1, 1 } }
        };

        for (int i = 0; i < heaters.size(); i++) {
            int[][] temp = new int[N][M];
            Heater heater = heaters.get(i);
            int d = heater.id;
            int sr = heater.r + dx[d];
            int sc = heater.c + dy[d];
            Queue<MapInfo> que = new LinkedList<>();
            que.offer(new MapInfo(heater.id, sr, sc, 4, board[sr][sc].w));
            temp[sr][sc] += 5;

            while(!que.isEmpty()){
                MapInfo curr = que.poll();
                for (int nd = 0; nd < 3; nd++) {
                    int nr = curr.r + diffusion[d][0][nd];
                    int nc = curr.c + diffusion[d][1][nd];
                    if (curr.t <= 0 || nr < 0 || nc < 0 || nr >= N || nc >= M || temp[nr][nc] != 0 || !checkWall(d, curr.r, curr.c, nr, nc)) {
                        continue;
                    }

                    temp[nr][nc] = curr.t;
                    que.offer(new MapInfo(curr.id, nr, nc, curr.t - 1, curr.w));
                }
            }
            sumNewOperateHeater(temp);
        }
    }

    public static boolean checkWall(int d, int cr, int cc, int nr, int nc){
        int rd = nr - cr;
        int cd = nc - cc;

        if(d == 1){
            if(rd == -1 && cd == 1){ // 위 오른쪽
                if(board[cr][cc].w[0] || board[cr + rd][cc].w[1])
                    return false;
            }else if(rd == 0 && cd == 1){ // 오른쪽
                if(board[cr][cc].w[1])
                    return false;
            }else{ // 아래 오른쪽
                if(board[cr + rd][cc].w[0] || board[cr + rd][cc].w[1])
                    return false;
            }
        }else if(d == 2){
            if(rd == -1 && cd == -1){ // 위 왼쪽
                if(board[cr][cc].w[0] || board[cr + rd][cc + cd].w[1])
                    return false;
            }else if(rd == 0 && cd == -1){ // 왼쪽
                if(board[cr][cc + cd].w[1])
                    return false;
            }else{ // 아래 왼쪽
                if(board[cr + rd][cc].w[0] || board[cr + rd][cc + cd].w[1])
                    return false;
            }
        }else if(d == 3){
            if(rd == -1 && cd == 1){ // 오른쪽 위
                if(board[cr][cc].w[1] || board[cr][cc + cd].w[0])
                    return false;
            }else if(rd == -1 && cd == 0){ // 위
                if(board[cr][cc].w[0])
                    return false;
            }else{
                if(board[cr][cc + cd].w[1] || board[cr][cc + cd].w[0]) // 왼쪽 위
                    return false;
            }
        }else if(d == 4){
            if(rd == 1 && cd == 1){ // 오른쪽 아래
                if(board[cr][cc].w[1] || board[cr + rd][cc + cd].w[0])
                    return false;
            }else if(rd == 1 && cd == 0){ // 아래
                if(board[cr + rd][cc].w[0])
                    return false;
            }else{
                if(board[cr][cc + cd].w[1] || board[cr + rd][cc + cd].w[0]) // 왼쪽 아래
                    return false;
            }
        }
        
        return true;
    }

    public static boolean investigateSpace(ArrayList<int[]> investigations){
        for(int i = 0; i < investigations.size(); i ++){
            int r = investigations.get(i)[0];
            int c = investigations.get(i)[1];
            if(board[r][c].t < K){
                return false;
            }
        }
        return true;
    }

    public static void adjustSuburbTempeture(){
        for(int r = 0; r < N; r ++){
            for(int c = 0; c < M; c ++){
                if((r == 0 || r == N - 1 || c == 0 || c == M - 1) && board[r][c].t > 0){
                    board[r][c].t --;
                }
            }
        }
    }

    public static void sumNewOperateHeater(int[][] temp){
        for(int r = 0; r < N; r ++){
            for(int c = 0; c < M; c ++){
                board[r][c].t += temp[r][c];
            }
        }
    }
}
