import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int N, M;
    char[][] map;
    boolean[][][][] v;
    int[] end = new int[2];
    int[][] ball;
    int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        v = new boolean[N][M][N][M];
        ball = new int[2][2];

        for(int i = 0; i < N; i ++){
            String str = br.readLine();
            for(int j = 0; j < M; j ++){
                char c = str.charAt(j);
                map[i][j] = c;
                if(c == 'R'){
                    ball[0] = new int[] { i, j };
                }else if(c == 'B'){
                    ball[1] = new int[] { i, j };
                }else if(c == 'O'){
                    end = new int[] { i, j };
                }
            }
        }
        int answer = backtracking(0, new int[11], false, false);
        System.out.println(answer == 987654321 ? -1 : answer);
    }

    public int backtracking(int depth, int[] arr, boolean red, boolean blue){
        if(blue){
            return 987654321;
        }
        if(depth == 11){
            return 987654321;
        }

        if(map[ball[0][0]][ball[0][1]] == 'O' && map[ball[1][0]][ball[1][1]] != 'O'){
            return depth;
        }

        if(v[ball[0][0]][ball[0][1]][ball[1][0]][ball[1][1]]){
            return 987654321;
        }

        v[ball[0][0]][ball[0][1]][ball[1][0]][ball[1][1]] = true;
        int ret = 987654321;
        for(int d = 0; d < 4; d ++){
            int idx = selectBall(d); // 0: 빨간색, 1: 파란색
            
            int[][] tmp = copyArr(ball);

            map[ball[0][0]][ball[0][1]] = '.';
            map[ball[1][0]][ball[1][1]] = '.';
            move(d, idx, ball[idx]);
            move(d, 1 - idx, ball[1 - idx]);
            map[end[0]][end[1]] = 'O';
            boolean red_state = map[ball[0][0]][ball[0][1]] == 'O' ? true : false;
            boolean blue_state = map[ball[1][0]][ball[1][1]] == 'O' ? true : false;
            arr[depth] = d;
            ret = Math.min(ret, backtracking(depth + 1, arr, red_state, blue_state));
            arr[depth] = -1;
            map[ball[0][0]][ball[0][1]] = '.';
            map[ball[1][0]][ball[1][1]] = '.';
            ball = copyArr(tmp);
            map[ball[0][0]][ball[0][1]] = 'R';
            map[ball[1][0]][ball[1][1]] = 'B';
            map[end[0]][end[1]] = 'O';
        }
        v[ball[0][0]][ball[0][1]][ball[1][0]][ball[1][1]] = false;
        return ret;
    }

    public void move(int d, int idx, int[] cur_ball){
        int r = cur_ball[0];
        int c = cur_ball[1];
        if(r == end[0] && c == end[1]) return;
        while(true){
            int nr = r + dirs[d][0];
            int nc = c + dirs[d][1];
            if(nr < 0 || nc < 0 || nr >= N || nc >= M) break;
            if(map[nr][nc] == 'R' || map[nr][nc] == 'B') break;
            if(map[nr][nc] == 'O') {
                ball[idx][0] = nr;
                ball[idx][1] = nc;
                return;
            }else if(map[nr][nc] != '.') {
                break;
            }
            r = nr;
            c = nc;
        }
        
        ball[idx][0] = r;
        ball[idx][1] = c;
        map[ball[idx][0]][ball[idx][1]] = idx == 0 ? 'R' : 'B';
    }

    public int selectBall(int d){
        int[] red_ball = ball[0];
        int[] blue_ball = ball[1];
        switch(d){
            case 0:
            if(red_ball[1] == blue_ball[1]){
                if(red_ball[0] > blue_ball[0]){
                    return 1;
                }else{
                    return 0;
                }
            }
            break;
            case 1:
            if(red_ball[0] == blue_ball[0]){
                if(red_ball[1] > blue_ball[1]){
                    return 0;
                }else{
                    return 1;
                }
            }
            break;
            case 2:
            if(red_ball[1] == blue_ball[1]){
                if(red_ball[0] > blue_ball[0]){
                    return 0;
                }else{
                    return 1;
                }
            }
            break;
            case 3:
            if(red_ball[0] == blue_ball[0]){
                if(red_ball[1] > blue_ball[1]){
                    return 1;
                }else{
                    return 0;
                }
            }
            break;
        }
        return 0;
    }

    public int[][] copyArr(int[][] ball){
        int[][] tmp = new int[2][2];
        for(int i = 0; i < 2; i ++){
            for(int j = 0; j < 2; j ++){
                tmp[i][j] = ball[i][j];
            }
        }

        return tmp;
    }
}
