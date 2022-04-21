import java.io.*;
import java.util.StringTokenizer;
public class Main {

    private static int N, M;
    private static int[][] board;
    private static int answer = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        solution(0, N * M);
        System.out.println(answer);
    }

    public static void solution(int depth, int n){
        if(depth == n){
            answer ++;
            return;
        }

        int r = depth / M;
        int c = depth % M;

        if(r - 1 >= 0 && c - 1 >= 0 && board[r-1][c] == 1 && board[r][c-1] == 1 && board[r-1][c-1] == 1){
            solution(depth + 1, n);
        }else{
            board[r][c] = 1;
            solution(depth + 1, n);
            board[r][c] = 0;
            solution(depth + 1, n);
        }
    }
}
