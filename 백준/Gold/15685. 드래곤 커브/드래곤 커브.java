import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
public class Main {

    static boolean[][] v = new boolean[101][101];
    static int[][] dirs = {{0,1},{-1,0},{0,-1},{1,0}};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for(int i = 0; i < t; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            v[y][x] = true;
            ArrayList<Integer> cur = new ArrayList<>();
            
            int nr = y + dirs[d][0];
            int nc = x + dirs[d][1];
            v[nr][nc] = true;

            cur.add(d);
            for(int j = 1; j <= g; j ++){
                int size = cur.size() - 1;
                for(int k = size; k >= 0; k --){
                    int nd = (cur.get(k) + 1) % 4;

                    nr += dirs[nd][0];
                    nc += dirs[nd][1];

                    v[nr][nc] = true;
                    cur.add(nd);
                }
            }
        }

        System.out.println(getRectangleNums());
    }

    public static int getRectangleNums(){
        int ret = 0;
        for(int i = 0; i < 100; i ++){
            for(int j = 0; j < 100; j ++){
                if(v[i][j] && v[i + 1][j] && v[i][j + 1] && v[i + 1][j + 1]){
                    ret ++;
                }
            }
        }
        return ret;
    }
}