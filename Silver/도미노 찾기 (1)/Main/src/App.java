import java.io.*;
import java.util.Arrays;
public class App {

    private static final int row = 8;
    private static final int col = 7;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] board = new int[row][col];
        for(int i = 0; i < row; i ++)
            board[i] = Arrays.stream(br.readLine().split("")).mapToInt(x -> Integer.parseInt(x)).toArray();

        
    }

    public static int solution(){
        int answer = 0;

        return answer;
    }
}
