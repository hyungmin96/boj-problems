import java.io.*;
import java.util.*;

public class Main {

    static boolean flag = false;
    static int N, M, H, answer = Integer.MAX_VALUE;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][N];

        for(int i = 0; i < M; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;

            map[r][c] = 1;
        }


        if(!check()){
            for(int i = 0; i <= 3; i ++){
                backtracking(0, 0, i);
                if(flag) break;
            }
        }else answer = 0;
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);        
    }

    public static void backtracking(int depth, int r, int size){
        if(flag) return;
        if(depth == size){   
            if(check()){
                answer = Math.min(answer, depth);
                flag = true;
            }
            return;
        }

        for(int i = r; i < H; i ++){
            for(int j = 0; j < N - 1; j ++){
                if(map[i][j] == 1) continue;
                if(j + 1 < N && map[i][j + 1] == 1) continue;
                map[i][j] = 1;
                backtracking(depth + 1, i, size);
                map[i][j] = 0;
            }
        }
    }

    public static boolean check(){
        for(int c = 0; c < N; c ++){
            int cur = c;
            for(int r = 0; r < H; r ++){
                if(map[r][cur] == 1) cur ++;
                else if(cur - 1 >= 0 && map[r][cur - 1] == 1) cur --;
            }
            if(cur != c) return false;
        }
        return true;
    }
}