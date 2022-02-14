import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {

    private static int R, C, T;
    private static int[][] board;
    private static int[][] temp;

    private static int[] dx = new int[] {-1, 0, 1, 0 };
    private static int[] dy = new int[] { 0, 1, 0,-1 };

    public static class Dust{
        int row, col, value;
        public Dust(int row, int col, int value){
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        board = new int[R][C];

        ArrayList<Dust> list = new ArrayList<>();
        ArrayList<int[]> cleaner = new ArrayList<>();
        for(int i = 0; i < R; i ++){
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(item -> Integer.parseInt(item)).toArray();
            for(int j = 0; j < C; j ++){
                board[i][j] = arr[j];
                if(board[i][j] == -1){
                    cleaner.add(new int[] { i, j });
                }else if(board[i][j] != 0){
                    list.add(new Dust(i, j, board[i][j]));
                }
            }
        }
 
        System.out.println(solution(list, cleaner));
    }

    public static int solution(ArrayList<Dust> list, ArrayList<int[]> cleaner){
        for(int i = 0; i < T; i ++){
            spread();
            setSpreadResult();
            upperMoveDust(cleaner.get(0));
            downMoveDust(cleaner.get(1));
        }
        return getDustValue();
    }

    public static void upperMoveDust(int[] cleaner){
        int r = cleaner[0];

        int temp = board[r][C-1];
        for(int i = C-1; i > 1; i --){
            board[r][i] = board[r][i-1];
        }
        board[r][1] = 0;

        int temp2 = board[0][C-1];
        for(int i = 0; i < r; i ++){
            board[i][C-1] = board[i+1][C-1];
        }
        board[r-1][C-1] = temp;

        temp = board[0][0];
        for(int i = 0; i < C-1; i ++){
            board[0][i] = board[0][i+1];
        }
        board[0][C-2] = temp2;

        for(int i = r-1; i >= 1; i --){
            board[i][0] = board[i-1][0];
        }
        board[1][0] = temp;
    }

    public static void downMoveDust(int[] cleaner){
        int r = cleaner[0];
        int temp = board[r][C-1];
        for(int i = C-1; i > 1; i --){
            board[r][i] = board[r][i-1];
        }
        board[r][1] = 0;

        int temp2 = board[R-1][C-1];
        for(int i = R-1; i > r+1; i --){
            board[i][C-1] = board[i-1][C-1];
        }
        board[r+1][C-1] = temp;

        temp = board[R-1][0];
        for(int i = 0; i < C-1; i ++){
            board[R-1][i] = board[R-1][i+1];
        }
        board[R-1][C-2] = temp2;

        for(int i = r+1; i < R-2; i ++){
            board[i][0] = board[i+1][0];
        }
        board[R-2][0] = temp;
    }

    public static void spread() {
        temp = new int[R][C];
        for (int x = 0; x < R; x++) {
            for (int y = 0; y < C; y++) {
                if (board[x][y] > 0) {

                    int spread_count = 0;
                    int value = 0;

                    for (int i = 0; i < dx.length; i++) {
                        int next_row = x + dx[i];
                        int next_col = y + dy[i];
                        if (next_row < 0 || next_col < 0 || next_row >= R || next_col >= C || board[x][y] < 5 || board[next_row][next_col] == -1)
                            continue;
                        value = board[x][y] / 5;
                        ++spread_count;
                        temp[next_row][next_col] += value;
                    }
                    board[x][y] -= (value * spread_count);
                }
            }
        }
    }

    public static void setSpreadResult(){
        for(int i = 0; i < R; i ++){
            for(int j = 0; j < C; j ++){
                board[i][j] += temp[i][j];
            }
        }
    }

    public static int getDustValue(){
        int result = 0;
        for(int i = 0; i < R; i ++){
            for(int j = 0; j < C; j ++){
                if(board[i][j] > 0) result += board[i][j];
            }
        }
        return result;
    }
}
