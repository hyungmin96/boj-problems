import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static class Pos{
        int x;
        int y;
        public Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    private static int answer = Integer.MIN_VALUE;
    private static int[] dx = new int[] {-1, 0, 1, 0 };
    private static int[] dy = new int[] { 0, 1, 0,-1 };
    private static boolean[][] visited;
    private static int[][] board;
    private static int N;
    private static int M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M];
        board = new int[N][M];

        for(int i = 0; i < N; i ++){
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(item -> Integer.parseInt(item)).toArray();
        }

        for(int i = 0; i < N; i ++){
            for(int j = 0; j < M; j ++){
                visited[i][j] = true;
                solution(0, board[i][j], i, j);
                visited[i][j] = false;
                int cnt = exception(i, j);
                answer = (answer > cnt) ? answer : cnt;
            }
        }
        System.out.println(answer);
    }

    public static void solution(int depth, int curr, int row, int col){
        if(depth == 3){
            answer = (answer > curr) ? answer : curr;
            return;
        }else{
            for(int i = 0; i < dx.length; i ++){
                int next_row = row + dx[i];
                int next_col = col + dy[i];
                if(next_row >= 0 && next_col >= 0 && next_row < N && next_col < M && !visited[next_row][next_col]){
                    visited[next_row][next_col] = true;
                    solution(depth + 1, curr + board[next_row][next_col], next_row, next_col);
                    visited[next_row][next_col] = false;
                }
            }
        }
    }

    public static int exception(int row, int col){
        
        int result = 0;
        int temp = 0;
        if(row + 1 < N && col + 2 < M){
            temp = 0;
            temp += board[row][col];
            temp += board[row][col + 1];
            temp += board[row + 1][col + 1];
            temp += board[row][col + 2];
        }
        result = (result > temp) ? result : temp;

        temp = 0;
        if(row + 2 < N && col + 1 < M){
            temp = 0;
            temp += board[row][col];
            temp += board[row + 1][col];
            temp += board[row + 1][col + 1];
            temp += board[row + 2][col];
        }
        result = (result > temp) ? result : temp;

        temp = 0;
        if(row + 2 < N && col - 1 >= 0){
            temp = 0;
            temp += board[row][col];
            temp += board[row + 1][col];
            temp += board[row + 1][col - 1];
            temp += board[row + 2][col];
        }
        result = (result > temp) ? result : temp;

        temp = 0;
        if(col - 1 >= 0 && row + 1 < N && col + 2 < M){
            temp = 0;
            temp += board[row][col];
            temp += board[row + 1][col - 1];
            temp += board[row + 1][col];
            temp += board[row + 1][col + 1];
        }
        result = (result > temp) ? result : temp;
        return result;
    }
}
