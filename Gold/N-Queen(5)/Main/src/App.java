import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        int[] board = new int[n];
        Arrays.fill(board, - 1);

        solution(0, n, board);
    }

    public static void solution(int depth, int n, int[] board){
        if(depth == n){
            return;
        }else{
            for(int row = 0; row < board.length; row ++){
                if(isAvailable(row, depth, board)){
                    board[depth] = depth;
                    solution(depth + 1, n, board);
                    board[depth] = -1;
                }
            }
        }
    }

    public static boolean isAvailable(int row, int col, int[] board){
        
        for(int i = 0; i < board.length; i ++){
            if(board[row] == row) 
                return false;
            else if(Math.abs(row - board[i]) == Math.abs(col - i))
                return false;
        }

        return true;
    }

}