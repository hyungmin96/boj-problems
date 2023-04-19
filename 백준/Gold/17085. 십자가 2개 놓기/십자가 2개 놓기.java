import java.io.*;
import java.util.*;

public class Main {

    static int N, M, answer = 1;
    static char[][] arr;
    static boolean[][] check;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new char[N][M];
        check = new boolean[N][M];

        for(int i = 0; i < N; i ++){
            String str = br.readLine();
            for(int j = 0; j < M; j ++){
                char c = str.charAt(j);
                arr[i][j] = c;
            }
        }

        dfs(0, 1, 0);
        System.out.println(answer);
    }

    public static void dfs(int depth, int cur, int cnt){
        if(cnt >= 2 || depth == (N * M) - 1){
            answer = Math.max(answer, cur);
            return;
        }

        int r = depth / M;
        int c = depth % M;

        for(int i = 1; i <= 15; i ++){
            if(installCross(r, c, i)){
                check(r, c, i, true);
                dfs(depth + 1, cur * ((i * 4) + 1), cnt + 1);
                check(r, c, i, false);
            }else break;
        }

        dfs(depth + 1, cur, cnt);
    }

    public static void check(int r, int c, int size, boolean val){
        for(int i = 0; i <= size; i ++){
            check[r + i][c] = val;
            check[r - i][c] = val;
            check[r][c + i] = val;
            check[r][c - i] = val;
        }
    }

    public static boolean installCross(int r, int c, int size){
        for(int i = 0; i <= size; i ++){
            if(r + i >= N || arr[r + i][c] == '.' || check[r + i][c]) return false;
            if(r - i < 0 || arr[r - i][c] == '.' || check[r - i][c]) return false;
            if(c + i >= M || arr[r][c + i] == '.' || check[r][c + i]) return false;
            if(c - i < 0 || arr[r][c - i] == '.' || check[r][c - i]) return false;
        }
        return true;
    }
}