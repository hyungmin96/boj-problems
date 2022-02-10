import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {

    private static int N, L, R;
    private static int[] dx = {-1, 0, 1, 0 };
    private static int[] dy = { 0, 1, 0,-1 };
    private static int[][] board;
    private static boolean[][] visited;
    private static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        board = new int[N][N];

        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j ++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            boolean flag = false;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && solution(i, j)) {
                        flag = true;
                    }
                }
            }
            if(flag) answer ++; else break;
        }

        System.out.println(answer);
    }

    public static boolean solution(int row, int col){

        Queue<int[]> que = new LinkedList<>();
        ArrayList<int[]> arrList = new ArrayList<>();
        boolean flag = false;
        int sum = board[row][col];
        que.offer(new int[] {row, col});
        arrList.add(new int[] {row, col, board[row][col]});
        visited[row][col] = true;

        while(!que.isEmpty()){
            int[] curr = que.poll();
            for(int i = 0; i < dx.length; i ++){
                int next_row = curr[0] + dx[i];
                int next_col = curr[1] + dy[i];
                if(next_row >= 0 && next_col >= 0 && next_row < N && next_col < N && !visited[next_row][next_col]){
                    if(Math.abs(board[next_row][next_col] - board[curr[0]][curr[1]]) >= L && Math.abs(board[next_row][next_col] - board[curr[0]][curr[1]]) <= R){
                        visited[next_row][next_col] = true;
                        flag = true;
                        que.offer(new int[] { next_row, next_col });
                        arrList.add(new int[] { next_row, next_col, board[next_row][next_col]});
                        sum += board[next_row][next_col];
                    }
                }
            }
        }

        if(!flag){
            return false;
        }else{
            sum /= arrList.size();
            for(int[] item : arrList){
                board[item[0]][item[1]] = sum;
            }
            return true;
        }
    }
}
