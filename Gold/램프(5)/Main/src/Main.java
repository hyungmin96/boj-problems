<<<<<<< HEAD
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {

    public static int answer = Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(stz.nextToken());
        int m = Integer.parseInt(stz.nextToken());

        int[][] board = new int[n][m];

        for(int i = 0; i < n; i ++){
            String s = br.readLine();
                for(int j = 0; j < s.length(); j ++)
                    board[i][j] = Integer.parseInt(s.charAt(j) + "");
        }

        int k = Integer.parseInt(br.readLine());

        solution(0, n, m, k, board);
        System.out.println(answer);
    }

    public static void solution(int depth, int n, int m, int k, int[][] board){
        
        for(int i = 0; i < n; i ++){

            int zeroCount = m - Arrays.stream(board[i]).sum();

        }

    }
}

=======
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
>>>>>>> 77ff6f90bf59a68c2a7440d4307e8f8d736ac580
