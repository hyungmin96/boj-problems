import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    public int R, C;
    public int[] player;
    public int[][] map;
    public int[][] dirs = {
        { 1, -1 },
        { 1, 0 },
        { 1, 1 },
        { 0, -1 },
        { 0, 0 },
        { 0, 1 },
        { -1, -1 },
        { -1, 0 },
        { -1, 1 },
    };
    public HashMap<Integer, int[]> arduino = new HashMap<>();

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];

        for(int r = 0; r < R; r ++){
            String str = br.readLine();
            for(int c = 0; c < C; c ++){
                char ch = str.charAt(c);
                if(ch == 'R'){
                    map[r][c] = 1;
                    arduino.put(arduino.size() + 1, new int[] { r, c });
                }else if(ch == 'I'){
                    player = new int[] { r, c };
                }else{
                    map[r][c] = 0;
                }
            }
        }

        int cnt = 0;
        String d = br.readLine();
        for(int i = 0; i < d.length(); i ++){
            cnt ++;
            movePlayer(d.charAt(i));
            if(map[player[0]][player[1]] > 0){
                break;
            }
            moveArduino();
            if(map[player[0]][player[1]] > 0){
                break;
            }
            putArduino();
        }

        if(cnt == d.length()){
            for(int r = 0; r < R; r ++){
                for(int c = 0; c < C; c ++){
                    if(r == player[0] && c == player[1])
                        System.out.print("I");
                    else if(map[r][c] > 0)
                        System.out.print("R");
                    else
                        System.out.print(".");
                }
                System.out.println();
            }
        }else{
            System.out.println("kraj " + cnt);
        }
    }

    public void putArduino(){
        arduino.clear();
        for(int r = 0; r < R; r ++){
            for(int c = 0; c < C; c ++){
                if(map[r][c] == 1){
                    arduino.put(arduino.size() + 1, new int[] { r, c });
                }else{
                    map[r][c] = 0;
                }
            }
        }
    }

    public void moveArduino(){
        for(int k : arduino.keySet()){
            int dist = 987654321;
            int[] cur = arduino.get(k);
            int tmp_nr = -1, tmp_nc = -1;
            for(int d = 0; d < 9; d ++){
                int nr = cur[0] + dirs[d][0];
                int nc = cur[1] + dirs[d][1];
                if(oob(nr, nc)) continue;
                int tmp = Math.abs(nr - player[0]) + Math.abs(nc - player[1]);
                if(dist > tmp){
                    dist = tmp;
                    tmp_nr = nr;
                    tmp_nc = nc;
                }
            }

            map[cur[0]][cur[1]] --;
            map[tmp_nr][tmp_nc] ++;
            if(tmp_nr == player[0] && tmp_nc == player[1]){
                return;
            }
        }
    }

    public void movePlayer(char c){
        int nd = c - '0' - 1;
        int nr = player[0] + dirs[nd][0];
        int nc = player[1] + dirs[nd][1];
        if(oob(nr, nc)) return;
        player[0] = nr;
        player[1] = nc;

        if(map[nr][nc] > 0){
            return;
        }
    }

    public boolean oob(int r, int c){
        return (r < 0 || c < 0 || r >= R || c >= C);
    }
}