import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;
    private static int[][] dice = new int[4][3];
    private static int[] dx = new int[] { 0, 0, -1, 1 };
    private static int[] dy = new int[] { 1, -1, 0, 0 };
    private static int curr_dice_row;
    private static int curr_dice_col;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] board = new int[N][M];

        curr_dice_row = Integer.parseInt(st.nextToken());
        curr_dice_col = Integer.parseInt(st.nextToken());
        int nums = Integer.parseInt(st.nextToken());
        int[] commands = new int[nums];

        for (int i = 0; i < N; i++)
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < nums; i++)
            commands[i] = Integer.parseInt(st.nextToken());

        dice[1][1] = board[curr_dice_row][curr_dice_col];
        solution(board, commands);
    }

    public static void solution(int[][] board, int[] commands) {
        for (int command : commands) {
            int top_number = move_dice(command - 1);
            if (top_number != -1) {
                if(board[curr_dice_row][curr_dice_col] == 0){
                    board[curr_dice_row][curr_dice_col] = dice[1][1];
                }else{
                    dice[1][1] = board[curr_dice_row][curr_dice_col];
                    board[curr_dice_row][curr_dice_col] = 0;
                }
                System.out.println(top_number);
            }
        }
    }

    public static int move_dice(int command) {
        int n_row = curr_dice_row + dx[command];
        int n_col = curr_dice_col + dy[command];

        if (n_row >= 0 && n_col >= 0 && n_row < N && n_col < M) {
            curr_dice_row = n_row;
            curr_dice_col = n_col;
            int temp = 0;

            if(command == 0){ // 동쪽 이동
                temp = dice[1][0];
                dice[1] = new int[] { dice[1][1], dice[1][2], dice[3][1] };
                dice[3][1] = temp;
            }else if(command == 1){ // 서쪽 이동
                temp = dice[1][2];
                dice[1] = new int[] { dice[3][1], dice[1][0], dice[1][1] };
                dice[3][1] = temp;
            }else if(command == 2){ // 북쪽 이동
                temp = dice[3][1];
                dice[3][1] = dice[2][1];
                dice[2][1] = dice[1][1];
                dice[1][1] = dice[0][1];
                dice[0][1] = temp;
            }else{ // 남쪽 이동
                temp = dice[0][1];
                dice[0][1] = dice[1][1];
                dice[1][1] = dice[2][1];
                dice[2][1] = dice[3][1];
                dice[3][1] = temp;
            }
            
            return dice[3][1];
        }
        return -1;
    }
}
