import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class Main {

    private static int N;
    private static int M;
    private static int[][] board;
    private static boolean[][] visited;
    private static int[] dx = new int[] { -1, 0, 1, 0 };
    private static int[] dy = new int[] { 0, 1, 0, -1 };
    private static int answer = 1;
    private static boolean flag = false;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M];

        st = new StringTokenizer(br.readLine(), " ");
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());
        int[] status = new int[] { row, col, dir };

        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(item -> Integer.parseInt(item)).toArray();
        }

        solution(status);
        System.out.println(answer);
    }

    public static void solution(int[] status) {

        board[status[0]][status[1]] = 2;
        visited[status[0]][status[1]] = true;
        boolean is_work_available = false;
        int dir = status[2];

        for(int i = 0; i < dx.length; i ++){
            if(flag) continue;
            dir = (dir + 3) % dx.length;
            int next_row = status[0] + dx[dir];
            int next_col = status[1] + dy[dir];
            if (next_row >= 0 && next_col >= 0 && next_row < N && next_col < M && board[next_row][next_col] == 0 && !visited[next_row][next_col]) {
                answer ++;
                is_work_available = true;
                visited[next_row][next_col] = true;
                board[next_row][next_col] = 2;
                solution(new int[] { next_row, next_col, dir });
            }
        }

        if(!is_work_available){
            int curr_row = status[0];
            int curr_col = status[1];
            if(status[2] == 0) curr_row ++;
            else if(status[2] == 2) curr_row --;
            else if(status[2] == 1) curr_col --;
            else if(status[2] == 3) curr_col ++;
            if(board[curr_row][curr_col] != 1){
                solution(new int[] { curr_row, curr_col, status[2] });
            }else{
                flag = true;
            }
        }

    }
}
