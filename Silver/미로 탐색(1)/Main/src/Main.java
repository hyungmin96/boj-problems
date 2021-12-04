import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {

    public static int answer = 0;

    static class Position{
        int row, col;
        public Position(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stz.nextToken());
        int m = Integer.parseInt(stz.nextToken());
        int[] dx = new int[] {-1, 0, 1, 0 };
        int[] dy = new int[] { 0, 1, 0,-1 };

        int[][] board = new int[n][m];
        boolean[][] visited = new boolean[n][m];

        for(int i = 0; i < n; i ++)
            board[i] = Arrays.stream(br.readLine().split("")).mapToInt(item -> Integer.parseInt(item)).toArray();

        solution(n, m, 0, 0, board, visited, dx, dy);
        System.out.println(board[n - 1][m - 1] + 1);
    }

    public static void solution(int n, int m, int row, int col, int[][] board, boolean[][] visited, int[] dx, int[] dy){
        
        Queue<Position> que = new LinkedList<>();

        board[0][0] = 0;
        visited[0][0] = true;
        que.offer(new Position(row, col));

        while(!que.isEmpty()){
            Position curr = que.poll();
            for(int i = 0; i < dx.length; i ++){
                int nrow = curr.row + dx[i];
                int ncol = curr.col + dy[i];
                if(nrow >= 0 && ncol >= 0 && nrow < n && ncol < m){
                    if(!visited[nrow][ncol] && board[nrow][ncol] == 1){
                        que.offer(new Position(nrow, ncol));
                        board[nrow][ncol] = board[curr.row][curr.col] + 1;
                        visited[nrow][ncol] = true;
                    }
                }
            }
        }
        
    }
}
