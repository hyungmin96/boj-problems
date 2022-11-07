import java.io.*;
import java.util.*;

public class Main {

    static class Pair{
        int x, y, d, g;
        public Pair(int x, int y, int d, int g){ this.x = x; this.y = y; this.d = d; this.g = g; }
    }

    static int[][] dirs = {
        { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 }
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean[][] map = new boolean[150][150];
        for(int i = 0; i < n; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            map[y][x] = true;
            ArrayList<Integer> list = new ArrayList<>();
            list.add(d);
            y += dirs[d][0];
            x += dirs[d][1];
            map[y][x] = true;
            for(int j = 0; j < g; j ++){
                int size = list.size() - 1;
                for(int k = size; k >= 0; k --){
                    int cd = (list.get(k) + 1) % 4;
                    y += dirs[cd][0];
                    x += dirs[cd][1];
                    map[y][x] = true;
                    list.add(cd);
                }
            }
        }
        
        int answer = 0;
        for(int r = 0; r < 149; r ++){
            for(int c = 0; c < 149; c ++){
                if(map[r][c] && map[r + 1][c] && map[r][c + 1] && map[r + 1][c + 1]){
                    answer ++;
                }
            }
        }
        System.out.println(answer);
    }
}