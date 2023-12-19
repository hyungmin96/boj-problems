import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int N, M;
    int[] pos = new int[2];
    int[][] map, dirs = {{1,-1},{1,0},{1,1},{0,-1},{0,0},{0,1},{-1,-1},{-1,0},{-1,1}};

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        int answer = 0;

        for(int i = 0; i < N; i ++){
            String str = br.readLine();
            for(int j = 0; j < M; j ++){
                char c = str.charAt(j);
                if(c == 'I'){
                    pos = new int[] { i, j };
                }else if(c == 'R'){
                    map[i][j] ++;
                }
            }
        }

        String commands = br.readLine();
        for(char c : commands.toCharArray()){
            answer ++;
            int d = c - '0' - 1;
            pos[0] += dirs[d][0];
            pos[1] += dirs[d][1];
            if(map[pos[0]][pos[1]] > 0){
                System.out.println("kraj " + answer);
                return;
            }

            int[][] tmp = new int[N][M];
            ArrayList<int[]> explode = new ArrayList<>();
            for(int i = 0; i < N; i ++){
                for(int j = 0; j < M; j ++){
                    if(map[i][j] > 0){
                        map[i][j] = 0;
                        int cur_d = -1;
                        int min_dist = 987654321;
                        for(int d1 = 0; d1 < 9; d1 ++){
                            int nr = i + dirs[d1][0];
                            int nc = j + dirs[d1][1];
                            
                            int dist = Math.abs(nr - pos[0]) + Math.abs(nc - pos[1]);
                            if(min_dist > dist){
                                min_dist = dist;
                                cur_d = d1;
                            }
                        }

                        if(pos[0] == i + dirs[cur_d][0] && pos[1] == j + dirs[cur_d][1]){
                            System.out.println("kraj " + answer);
                            return;
                        }
                        tmp[i + dirs[cur_d][0]][j + dirs[cur_d][1]] ++;
                        if(tmp[i + dirs[cur_d][0]][j + dirs[cur_d][1]] > 1){
                            explode.add(new int[] { i + dirs[cur_d][0], j + dirs[cur_d][1]});
                        }
                    }
                }
            }

            for(int[] p : explode){
                tmp[p[0]][p[1]] = 0;
            }

            for(int i = 0; i < N; i ++){
                for(int j = 0; j < M; j ++){
                    if(tmp[i][j] > 0){
                        map[i][j] = tmp[i][j];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int r = 0; r < N; r ++){
            for(int c = 0; c < M; c ++){
                if(r == pos[0] && c == pos[1]){
                    sb.append("I");
                }else{
                    if(map[r][c] > 0){
                        sb.append("R");
                    }else{
                        sb.append(".");
                    }
                }
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
