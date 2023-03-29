import java.io.*;
import java.util.*;

public class Main {

    static int N, minus, zero, one;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i = 0; i < N; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j ++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        for(int i = 0; i <= 7; i ++){
            if((int)Math.pow(3, i) == N) break;
            cnt ++;
        }

        dfs(0, 0, cnt, (int)Math.pow(3, cnt));
        System.out.println(minus);
        System.out.println(zero);
        System.out.println(one);
    }

    public static void dfs(int r, int c, int k, int size){
        if(k == 0){
            count(map[r][c]);
            return;
        }

        if(!check(map[r][c], r, c, size)){
            for(int i = 0; i < size; i += (int)Math.pow(3, k - 1)){
                for(int j = 0; j < size; j += (int)Math.pow(3, k - 1)){
                    dfs(r + i, c + j, k - 1, (int)Math.pow(3, k - 1));
                }
            }
        }else{
            count(map[r][c]);
            return;
        }
    }

    public static void count(int cur){
        if(cur == -1) minus ++;
        else if(cur == 0) zero ++;
        else if(cur == 1) one ++;
    }

    public static boolean check(int cur, int r, int c, int size){
        for(int r1 = r; r1 < r + size; r1 ++){
            for(int c1 = c; c1 < c + size; c1 ++){
                if(cur != map[r1][c1]){
                    return false;
                }
            }
        }
        return true;
    }
}