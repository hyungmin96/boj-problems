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

