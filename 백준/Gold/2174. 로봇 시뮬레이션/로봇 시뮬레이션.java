import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    public class Robot{
        int r, c, d;
        public Robot(int r, int c, int d){
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    int N, M;
    int[][] map;
    int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};
    HashMap<Character, Integer> dir_map = new HashMap<>();
    String answer = "OK";

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        dir_map.put('N', 1);
        dir_map.put('E', 2);
        dir_map.put('S', 3);
        dir_map.put('W', 4);

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[M + 1][N + 1];
        Robot[] robots = new Robot[101];

        st = new StringTokenizer(br.readLine(), " ");
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        
        for(int i = 0; i < R; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            
            int c = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);

            int d = dir_map.get(dir) - 1;

            robots[i + 1] = new Robot(M + 1 - r, c, d);

            map[M + 1 - r][c] = i + 1;
        }

        for(int i = 0; i < C; i ++){
            st = new StringTokenizer(br.readLine(), " ");

            int idx = Integer.parseInt(st.nextToken());
            char cmd = st.nextToken().charAt(0);
            int repeat = Integer.parseInt(st.nextToken());

            if(command(idx, cmd, repeat, robots) == -1){
                System.out.println(answer);
                return;
            }
        }
        System.out.println(answer);
    }

    public int command(int idx, char cmd, int repeat, Robot[] robots){
        if(cmd == 'L'){
            repeat %= 4;
            robots[idx].d = (robots[idx].d + 4 - repeat) % 4;
        }else if(cmd == 'R'){
            repeat %= 4;
            robots[idx].d = (robots[idx].d + repeat) % 4;
        }else{
            // F
            map[robots[idx].r][robots[idx].c] = 0;
            for(int k = 1; k <= repeat; k ++){
                int nr = robots[idx].r + (dirs[robots[idx].d][0] * k);
                int nc = robots[idx].c + (dirs[robots[idx].d][1] * k);

                if(nr < 1 || nc < 1 || nr > M || nc > N){
                    answer = "Robot " + idx + " crashes into the wall";
                    return -1;
                }

                if(map[nr][nc] >= 1){
                    answer = "Robot " + idx + " crashes into robot " + map[nr][nc];
                    return -1;
                }
            }

            robots[idx].r += (dirs[robots[idx].d][0] * repeat);
            robots[idx].c += (dirs[robots[idx].d][1] * repeat);

            map[robots[idx].r][robots[idx].c] = idx;
        }
        return 0;
    }
}
