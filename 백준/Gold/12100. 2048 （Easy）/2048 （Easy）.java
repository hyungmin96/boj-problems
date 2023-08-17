import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int N, answer = 0;
    int[][] dirs = {
        { -1, 0 },
        { 0, 1 },
        { 1, 0 },
        { 0, -1 }
    };
    int[][] map;

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i = 0; i < N; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j ++){
                map[i][j] = Integer.parseInt(st.nextToken());
                answer = Math.max(answer, map[i][j]);
            }
        }

        dfs(0, new int[5]);
        System.out.println(answer);
    }

    public void move(int d){
        int min_r, max_r, min_c, max_c;
        switch(d){
            case 0:
                for(int c = 0; c < N; c ++){
                    min_r = 0;
                    for(int r = 0; r < N; r ++){
                        if(map[r][c] == 0) continue;
                        int cur_r = r;
                        int cur_num = map[r][c];
                        map[r][c] = 0;
                        while(cur_r - 1 >= 0 && cur_r - 1 >= min_r && (map[cur_r - 1][c] == cur_num || map[cur_r - 1][c] == 0)){
                            cur_r --;
                        }

                        if(r != cur_r && map[cur_r][c] == cur_num){
                            map[cur_r][c] = cur_num + cur_num;
                            answer = Math.max(answer, cur_num + cur_num);
                            min_r = cur_r + 1;
                        }else{
                            map[cur_r][c] = cur_num;
                        }
                    }
                }
                break;
            case 1:
                for(int r = 0; r < N; r ++){
                    max_c = N - 1;
                    for(int c = N - 1; c >= 0; c --){
                        if(map[r][c] == 0) continue;
                        int cur_c = c;
                        int cur_num = map[r][c];
                        map[r][c] = 0;
                        while(cur_c + 1 < N && cur_c + 1 <= max_c && (map[r][cur_c + 1] == cur_num || map[r][cur_c + 1] == 0)){
                            cur_c ++;
                        }

                        if(c != cur_c && map[r][cur_c] == cur_num){
                            map[r][cur_c] = cur_num + cur_num;
                            answer = Math.max(answer, cur_num + cur_num);
                            max_c = cur_c - 1;
                        }else{
                            map[r][cur_c] = cur_num;
                        }
                    }
                }
                break;
            case 2:
                for(int c = 0; c < N; c ++){
                    max_r = N - 1;
                    for(int r = N - 1; r >= 0; r --){
                        if(map[r][c] == 0) continue;
                        int cur_r = r;
                        int cur_num = map[r][c];
                        map[r][c] = 0;
                        while(cur_r + 1 < N && cur_r + 1 <= max_r && (map[cur_r + 1][c] == cur_num || map[cur_r + 1][c] == 0)){
                            cur_r ++;
                        }

                        if(r != cur_r && map[cur_r][c] == cur_num){
                            map[cur_r][c] = cur_num + cur_num;
                            answer = Math.max(answer, cur_num + cur_num);
                            max_r = cur_r - 1;
                        }else{
                            map[cur_r][c] = cur_num;
                        }
                    }
                }
                break;
            case 3:
                for(int r = 0; r < N; r ++){
                    min_c = 0;
                    for(int c = 0; c < N; c ++){
                        if(map[r][c] == 0) continue;
                        int cur_c = c;
                        int cur_num = map[r][c];
                        map[r][c] = 0;
                        while(cur_c - 1 >= 0 && cur_c - 1 >= min_c && (map[r][cur_c - 1] == cur_num || map[r][cur_c - 1] == 0)){
                            cur_c --;
                        }

                        if(c != cur_c && map[r][cur_c] == cur_num){
                            map[r][cur_c] = cur_num + cur_num;
                            answer = Math.max(answer, cur_num + cur_num);
                            min_c = cur_c + 1;
                        }else{
                            map[r][cur_c] = cur_num;
                        }
                    }
                }
                break;
        }
    }

    public void dfs(int depth, int[] arr){
        if(depth == 5){
            return;
        }

        for(int i = 0; i < 4; i ++){
            int[][] tmp = copy(map);
            move(i);
            arr[depth] = i;
            dfs(depth + 1, arr);
            map = copy(tmp);
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