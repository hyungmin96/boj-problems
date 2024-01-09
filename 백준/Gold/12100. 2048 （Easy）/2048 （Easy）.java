import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int N, answer = -1;

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];

        for(int i = 0; i < N; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j ++){
                map[i][j] = Integer.parseInt(st.nextToken());
                answer = Math.max(answer, map[i][j]);
            }
        }

        dfs(0, map);
        System.out.println(answer);
    }

    public void move(int d, int[][] tmp){
        switch(d){
            case 0:
                up(tmp);
                break;
            case 1:
                right(tmp);
                break;
            case 2:
                down(tmp);
                break;
            case 3:
                left(tmp);
                break;
        }
    }

    public void right(int[][] map){
        for(int row = 0; row < N; row ++){
            int col = N - 1;
            for(int i = col - 1; i >= 0; i --){
                if(map[row][i] == 0) continue;
                for(int j = i; j < col; j ++){
                    if(map[row][j] == 0) continue;
                    if(map[row][col] == map[row][j]){
                        // 동일한 숫자일 경우
                        map[row][col] *= 2;
                        answer = Math.max(answer, map[row][col]);
                        map[row][j] = 0;
                        col --;
                    }else if(map[row][col] != map[row][j]){
                        // 다른 숫자일 경우
                        if(map[row][col] > 0){
                            col --;
                        }
                        int tmp = map[row][j];
                        map[row][j] = 0;
                        map[row][col] = tmp;
                    }
                    break;
                }
            }
        }
    }

    public void left(int[][] map){
        for(int row = 0; row < N; row ++){
            int col = 0;
            for(int i = col + 1; i < N; i ++){
                if(map[row][i] == 0) continue;
                for(int j = i; j > col; j --){
                    if(map[row][j] == 0) continue;
                    if(map[row][col] == map[row][j]){
                        // 동일한 숫자일 경우
                        map[row][col] *= 2;
                        answer = Math.max(answer, map[row][col]);
                        map[row][j] = 0;
                        col ++;
                    }else if(map[row][col] != map[row][j]){
                        // 다른 숫자일 경우
                        if(map[row][col] > 0){
                            col ++;
                        }
                        int tmp = map[row][j];
                        map[row][j] = 0;
                        map[row][col] = tmp;
                    }
                    break;
                }
            }
        }
    }

    public void down(int[][] map){
        for(int col = 0; col < N; col ++){
            int row = N - 1;
            for(int i = row - 1; i >= 0; i --){
                if(map[i][col] == 0) continue;
                for(int j = i; j < row; j ++){
                    if(map[j][col] == 0) continue;
                    if(map[row][col] == map[j][col]){
                        // 동일한 숫자일 경우
                        map[row][col] *= 2;
                        answer = Math.max(answer, map[row][col]);
                        map[j][col] = 0;
                        row --;
                    }else if(map[row][col] != map[j][col]){
                        // 다른 숫자일 경우
                        if(map[row][col] > 0){
                            row --;
                        }
                        int tmp = map[j][col];
                        map[j][col] = 0;
                        map[row][col] = tmp;
                    }
                    break;
                }
            }
        }
    }

    public void up(int[][] map){
        for(int col = 0; col < N; col ++){
            int row = 0;
            for(int i = row + 1; i < N; i ++){
                if(map[i][col] == 0) continue;
                for(int j = i; j > row; j --){
                    if(map[j][col] == 0) continue;
                    if(map[row][col] == map[j][col]){
                        // 동일한 숫자일 경우
                        map[row][col] *= 2;
                        answer = Math.max(answer, map[row][col]);
                        map[j][col] = 0;
                        row ++;
                    }else if(map[row][col] != map[j][col]){
                        // 다른 숫자일 경우
                        if(map[row][col] > 0){
                            row ++;
                        }
                        int tmp = map[j][col];
                        map[j][col] = 0;
                        map[row][col] = tmp;
                    }
                    break;
                }
            }
        }
    }

    public void dfs(int depth, int[][] map){
        if(depth == 5){
            return;
        }
        for(int d = 0; d < 4; d ++){
            int[][] tmp = copy(map);
            move(d, tmp);
            dfs(depth + 1, tmp);
            tmp = new int[N][N];
            tmp = copy(map);
        }
    }

    public int[][] copy(int[][] map){
        int[][] tmp = new int[N][N];
        for(int r = 0; r < N; r ++){
            for(int c = 0; c < N; c ++){
                tmp[r][c] = map[r][c];
            }
        }

        return tmp;
    }
}