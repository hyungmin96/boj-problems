import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {

    private static int answer = 0;

    static class Position{
        int x, y;
        public Position(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] dx = new int[] {-1, 0, 1, 0};
        int[] dy = new int[] { 0, 1, 0,-1};
        int[][] board = new int[n][m];
        boolean[][] visited = new boolean[n][m];

        for(int i = 0; i < k; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            board[y - 1][x - 1] = 1;
        }
        for(int i = 0; i < board.length; i ++)
            for(int j = 0; j < board[i].length; j ++)
                if(board[i][j] == 1) 
                    solution(i, j, n, m, board, visited, dx, dy);

        System.out.println(answer);
    }

    public static void solution(int row, int col, int n, int m, int[][] board, boolean[][] visited, int[] dx, int[] dy){
        
        Queue<Position> que = new LinkedList<>();
        int temp = 1;

        que.offer(new Position(row, col));
        visited[row][col] = true;

        while(!que.isEmpty()){
            Position curr = que.poll();
            for(int i = 0; i < dx.length; i ++){
                int nrow = curr.x + dx[i];
                int ncol = curr.y + dy[i];
                if(nrow >= 0 && ncol >= 0 && nrow < n && ncol < m && board[nrow][ncol] == 1 && visited[nrow][ncol] == false){
                    visited[nrow][ncol] = true;
                    board[nrow][ncol] = 0;
                    que.offer(new Position(nrow, ncol));
                    temp ++;
                }
            }
        }

        answer = Math.max(answer, temp);
    }
}
