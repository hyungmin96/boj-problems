import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
public class Main {

    private static int N = 9;
    private static int[][] board;
    private static ArrayList<int[]> arrList = new ArrayList<>();
    private static int[][] frame = new int[][] {
        { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }
    };
    private static StringBuilder sb = new StringBuilder();
    private static boolean flag = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new int[N][N];
        for(int i = 0; i < N; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int idx = 0;
            while(st.hasMoreTokens()){
                int in = Integer.parseInt(st.nextToken());
                if(in == 0) arrList.add(new int[] { i, idx });
                board[i][idx ++] = in;
            }
        }
        solution(0);
        System.out.println(sb.toString());
    }

    public static void solution(int depth){
        if(flag) return;
        if(depth == arrList.size()){
            for(int r = 0; r < 9; r ++){
                for(int c = 0; c < 9; c ++){
                    sb.append(board[r][c]).append(" ");
                }
                sb.append("\n");
            }
            flag = true;
            return;
        }

        for(int i = 1; i < 10; i ++){
            int r = arrList.get(depth)[0];
            int c = arrList.get(depth)[1];
            if(checkRow(r, c, i) && checkCol(r, c, i) && checkFrame(r, c, i)){
                board[r][c] = i;
                solution(depth + 1);
                board[r][c] = 0;
            }
        }
    }

    public static boolean checkRow(int r, int c, int val){
        for(int i = 0; i < board[r].length; i ++){
            if(val == board[r][i]){
                return false;
            }
        }
        return true;
    }

    public static boolean checkCol(int r, int c, int val){
        for(int i = 0; i < board.length; i ++){
            if(val == board[i][c]){
                return false;
            }
        }
        return true;
    }

    public static boolean checkFrame(int r, int c, int val){
        int x = r / 3;
        int y = c / 3;
        
        for(int i : frame[x]){
            for(int j : frame[y]){
                if(val == board[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}
