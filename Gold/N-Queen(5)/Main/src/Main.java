import java.io.*;
import java.util.Arrays;
public class Main {

    private static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] board = new int[n + 1];
        
        Arrays.fill(board, 0);

        solution(1, 1, n + 1, board);
        System.out.println(answer);
    }

    public static void solution(int row, int col, int n, int[] board){
        if(col == n){
            answer ++;
            return;
        }else{
            for(int i = 1; i < board.length; i ++){
                board[col] = i;
                if(isAvailable(i, col, board)){
                    solution(i, col + 1, n, board);
                }
                board[col] = 0;
            }
        }
    }

    public static boolean isAvailable(int row, int col, int[] board){
        for(int i = 1; col != i && i < board.length; i ++){
            if(board[col] == board[i])
                return false;
            else if(Math.abs(board[i] - row) == Math.abs(col - i)){
                return false;
            }
        }

        return true;
    }
}
