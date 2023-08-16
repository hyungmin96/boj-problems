import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int A, B, N, M;
    int[][] map, dirs = {
        { -1, 0 },
        { 0, 1 },
        { 1, 0 },
        { 0, -1 }
    };
    HashMap<Integer, int[]> robots = new HashMap<>();

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        map = new int[B][A];

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = B - Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            int d = (c == 'N') ? 0 : (c == 'E') ? 1 : (c == 'S') ? 2 : 3;

            map[y][x] = robots.size() + 1;
            robots.put(robots.size() + 1, new int[] { y, x, d });
        }

        String answer = "OK";
        for(int i = 0; i < M; i ++){
            st = new StringTokenizer(br.readLine(), " ");

            int robot_idx = Integer.parseInt(st.nextToken());
            char cmd = st.nextToken().charAt(0);
            int repeat = Integer.parseInt(st.nextToken());

            answer = move(robot_idx, cmd, repeat);
            if(!answer.equals("OK")) break;
        }

        System.out.println(answer);
    }
/* 5 5
2 3
3 3 E
4 5 N
2 L 3
2 R 8
2 F 3 */
    public String move(int robot_idx, char cmd, int repeat){
        int cur_y = robots.get(robot_idx)[0];
        int cur_x = robots.get(robot_idx)[1];
        int cur_d = robots.get(robot_idx)[2];

        if(cmd == 'F'){
            int ny = cur_y;
            int nx = cur_x;
            for(int i = 0; i < repeat; i ++){
                ny += dirs[cur_d][0];
                nx += dirs[cur_d][1];
                if(ny < 0 || nx < 0 || ny >= B || nx >= A){
                    return "Robot " + robot_idx + " crashes into the wall";
                }else if(map[ny][nx] != 0){
                    return "Robot " + robot_idx + " crashes into robot " + map[ny][nx];
                }
            }
            map[cur_y][cur_x] = 0;
            map[ny][nx] = robot_idx;
            robots.put(robot_idx, new int[] { ny, nx, cur_d });
        }else{
            if(cmd == 'L'){
                robots.put(robot_idx, new int[] { cur_y, cur_x, (4 + cur_d - (repeat % 4)) % 4 });
            }else{
                robots.put(robot_idx, new int[] { cur_y, cur_x, (cur_d + repeat) % 4 });
            }
        }
        return "OK";
    }
}

