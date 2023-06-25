import java.util.*;
import java.io.*;

public class Main {

    static class Ball{
        int idx, r, c, d;
        boolean state;
        public Ball(int idx, int r, int c, int d, boolean state){
            this.idx = idx;
            this.r = r;
            this.c = c;
            this.d = d;
            this.state = state;
        }
    }

    static final int RED = 0;
    static final int BLUE = 1;

    static int N, M, answer = 12;
    static char[][] map;
    static int[][] dirs = {{ -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }};
    static int[][] order = {{1,3},{0,2},{1,3},{0,2}};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        
        Ball red = new Ball(0, 0, 0, 0, true), blue = new Ball(0, 0, 0, 0, true);
        for(int i = 0; i < N; i ++){
            String str = br.readLine();
            for(int j = 0; j < str.length(); j ++){
                if(str.charAt(j) == 'R') {
                    red = new Ball(RED, i, j, -1, true);
                }else if(str.charAt(j) == 'B') {
                    blue = new Ball(BLUE, i, j, -1, true);
                }

                map[i][j] = str.charAt(j);
            }
        }

        dfs(0, -1, red, blue);
        System.out.println(answer == 12 ? -1 : answer);
    }

    public static void dfs(int depth, int dir, Ball red, Ball blue){
        
        if(depth > 10 || !blue.state){
            return;
        }

        if(!red.state && blue.state){
            answer = Math.min(answer, depth);
            return;
        }

        if(dir == -1){
            for(int d = 0; d < 4; d ++){
                Ball[] next = move(d, red, blue);
                int idx = (next[0].idx == RED) ? 0 : 1;
                int idx2 = (next[1].idx == BLUE) ? 1 : 0;
                dfs(depth + 1, d, next[idx], next[idx2]);
                for(int r = 0; r < N; r ++){
                    for(int c = 0; c < M; c ++){
                        if(map[r][c] == 'R' || map[r][c] == 'B'){
                            map[r][c] = '.';
                        }
                    }
                }
            }
        }else{
            for(int d : order[dir]){
                Ball[] next = move(d, red, blue);
                int idx = (next[0].idx == RED) ? 0 : 1;
                int idx2 = (next[1].idx == BLUE) ? 1 : 0;
                dfs(depth + 1, d, next[idx], next[idx2]);
                for(int r = 0; r < N; r ++){
                    for(int c = 0; c < M; c ++){
                        if(map[r][c] == 'R' || map[r][c] == 'B'){
                            map[r][c] = '.';
                        }
                    }
                }
            }
        }
        
    }

    public static Ball[] move(int d, Ball red, Ball blue){
        Ball[] temp = new Ball[2];
        temp[0] = new Ball(red.idx, red.r, red.c, d, red.state);
        temp[1] = new Ball(blue.idx, blue.r, blue.c, d, blue.state);

        Arrays.sort(temp, new Comparator<>(){
            @Override
            public int compare(Ball o1, Ball o2){
                if(o1.d == 0){
                    return o1.r - o2.r;
                }else if(o1.d == 1){
                    return o2.c - o1.c;
                }else if(o1.d == 2){
                    return o2.r - o1.r;
                }else{
                    return o1.c - o2.c;
                }
            }
        });
            
        for(int i = 0; i < temp.length; i ++){
            int idx = temp[i].idx;
            int nr = temp[i].r;
            int nc = temp[i].c;
            while(true){
                nr += dirs[d][0];
                nc += dirs[d][1];
                if(!oob(nr, nc) || (map[nr][nc] != '.' && map[nr][nc] != 'O')){
                    nr += dirs[d][0] * -1;
                    nc += dirs[d][1] * -1;
                    break;
                }
                if(map[nr][nc] == 'O'){
                    map[temp[i].r][temp[i].c] = '.';
                    temp[i].state = false;
                    break;
                }   
            }
            
            if(map[temp[i].r][temp[i].c] != 'O')
                map[temp[i].r][temp[i].c] = '.';
            temp[i] = new Ball(idx, nr, nc, d, temp[i].state);
            if(temp[i].state && map[temp[i].r][temp[i].c] != 'O')
                map[temp[i].r][temp[i].c] = (temp[i].state && idx == RED) ? 'R' : 'B';
        }

        return temp;
    }

    public static boolean oob(int nr, int nc){
        if(nr < 0 || nc < 0 || nr >= N || nc >= M) return false;
        return true;
    }
}