import java.io.*;
import java.util.*;
public class Main {

    static class Ac{
        int r, c, d;
        public Ac(int r, int c, int d){
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    static int N, M, K;
    static int[][] map, wall;
    static int[][][] spreadDir = {
        {{2, 0}, {0}, {3, 0}}, // 오
        {{2, 1}, {1}, {3, 1}}, // 왼
        {{1, 2}, {2}, {0, 2}}, // 위
        {{0, 3}, {3}, {1, 3}} // 아래
    };
    static int[][] dirs = {
        { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 }
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        wall = new int[N][M];

        ArrayList<Ac> aircons = new ArrayList<>();
        ArrayList<int[]> office = new ArrayList<>();
        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < M; j ++){
                int num = Integer.parseInt(st.nextToken());
                if(num >= 1 && num <= 4) aircons.add(new Ac(i, j, num - 1));
                else if(num == 5) office.add(new int[] { i, j });
                else map[i][j] = num;
            }
        }   

        int walls = Integer.parseInt(br.readLine());
        for(int i = 0; i < walls; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) + 1;
            wall[r][c] += d;
        }
        System.out.println(solution(aircons, office));
    }

    public static int solution(ArrayList<Ac> aircons, ArrayList<int[]> office){
        int answer = 0;
        while(answer <= 100){
            spread(aircons);
            mixCoolAir();
            boundaryTempetureDown();
            answer ++;
            if(officeCheck(office)) return answer;
        }
        return 101;
    }

    public static void spread(ArrayList<Ac> aircons){
        for(Ac a : aircons){
            int cool = 5;
            boolean[][] vis = new boolean[N][M];
            Queue<Ac> que = new LinkedList<>();
            que.offer(new Ac(a.r + dirs[a.d][0], a.c + dirs[a.d][1], a.d));
            vis[a.r + dirs[a.d][0]][a.c + dirs[a.d][1]] = true;
            map[a.r + dirs[a.d][0]][a.c + dirs[a.d][1]] += cool--;

            while(!que.isEmpty()){
                int size = que.size();
                for(int k = 0; k < size; k ++){
                    Ac cur = que.poll();
                    for(int d = 0; d < spreadDir[cur.d].length; d ++){
                        boolean flag = true;
                        int nr = -1;
                        int nc = -1;
                        int cr = cur.r;
                        int cc = cur.c;
                        for(int i = 0; i < spreadDir[cur.d][d].length; i ++){
                            int cur_d = spreadDir[cur.d][d][i];
                            nr = cr + dirs[cur_d][0];
                            nc = cc + dirs[cur_d][1];
                            if(!oob(nr, nc) || vis[nr][nc] || !wallCheck(cr, cc, cur_d, nr, nc)) {
                                flag = false;
                                break;
                            }
                            cr = nr;
                            cc = nc;
                        }
                        if(flag){
                            vis[nr][nc] = true;
                            map[nr][nc] += cool;
                            que.offer(new Ac(nr, nc, a.d));
                        }
                    }
                }
                if(cool > 0) cool --;
            }
        }
    }

    public static void mixCoolAir(){
        int[][] temp = new int[N][M];
        for(int r = 0; r < N; r ++){
            for(int c = 0; c < M; c ++){
                for(int d = 0; d < 4; d ++){
                    int nr = r + dirs[d][0];
                    int nc = c + dirs[d][1];
                    if(!oob(nr, nc)) continue;
                    if(map[r][c] > map[nr][nc] && wallCheck(r, c, d, nr, nc)){
                        int diff = (map[r][c] - map[nr][nc]) / 4;
                        temp[r][c] -= diff;
                        temp[nr][nc] += diff;
                    }
                }
            }
        }
        for(int r = 0; r < N; r ++)
            for(int c = 0; c < M; c ++)
                map[r][c] += temp[r][c];
    }

    public static boolean wallCheck(int r, int c, int d, int nr, int nc){
        if(d == 0 && wall[r][c] >= 2){
            return false;
        }else if(d == 1 && wall[nr][nc] >= 2){
            return false;
        }else if(d == 2 && (wall[r][c] == 1 || wall[r][c] == 3)){
            return false;
        }else if(d == 3 && (wall[nr][nc] == 1 || wall[nr][nc] == 3)){
            return false;
        }
        return true;
    }

    public static void boundaryTempetureDown(){
        for(int r = 0; r < N; r ++){
            for(int c = 0; c < M; c ++){
                if(r == 0 || r == N - 1 || c == 0 || c == M - 1){
                    if(map[r][c] > 0) map[r][c] --;
                }
            }
        }
    }

    public static boolean officeCheck(ArrayList<int[]> office){
        for(int[] o : office)
            if(map[o[0]][o[1]] < K) return false;
        return true;
    }

    public static boolean oob(int nr, int nc){
        if(nr < 0 || nc < 0 || nr >= N || nc >= M) return false;
        return true;
    }
}