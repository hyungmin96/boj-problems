import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(stz.nextToken());
        int m = Integer.parseInt(stz.nextToken());
        
        int[][] board = new int[n][m];

        for(int i = 0; i < n; i ++){
            board[i] = Arrays.stream(br.readLine().split("")).mapToInt(item -> Integer.parseInt(item)).toArray();
        }

        int k = Integer.parseInt(br.readLine()) + 1;

        solution(0, 0, n, m, k, true, board);
        System.out.println(answer);
    }

    public static void solution(int depth, int col, int n, int m, int k, boolean press, int[][] board){

        if(depth == k || depth == Math.pow(2, m)){
            
            return;
        }else{
            for(int i = 0; i < n; i ++)
                if(press)
                    board[i][col] = (board[i][col] == 1) ? 0 : 1;

            solution(depth + 1, col + 1, n, m, k, true, board);

            for(int i = 0; i < n; i ++)
                if(press)
                    board[i][col] = (board[i][col] == 1) ? 0 : 1;
        }
    }
}
