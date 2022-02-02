import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static int[][] board;
    private static int[] dx = { 0,-1, 0, 1 };
    private static int[] dy = { 1, 0,-1, 0 };

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] curves = new int[N][4];
        board = new int[102][102];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            curves[i] = new int[] {
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
            };
        }

        solution(curves);
        System.out.println(getArea());
    }

    public static void solution(int[][] curves) {
        // X축, Y축, 이동방향, 세대 수
        for (int[] curve : curves) {
            ArrayList<Integer> arr = new ArrayList<>();
            int curr_row = curve[1];
            int curr_col = curve[0];
            int dir = curve[2];
            board[curr_row][curr_col] = 1;
            curr_row += dx[dir];
            curr_col += dy[dir];
            board[curr_row][curr_col] = 1;
            arr.add(dir);

            for(int i = 0; i < curve[3]; i ++){
                int size = arr.size() - 1;
                for(int j = size; j >= 0; j --){
                    dir = (arr.get(j) + 1) % dx.length;
                    curr_row += dx[dir];
                    curr_col += dy[dir];
                    board[curr_row][curr_col] = 1;
                    arr.add(dir);
                }
            }
        }
    }

    public static int getArea(){
        int result = 0;
        for(int i = 0; i < 100; i ++){
            for(int j = 0; j < 100; j ++){
                if(board[i][j] == 1 && board[i][j + 1] == 1 && board[i + 1][j] == 1 && board[i + 1][j + 1] == 1){
                    result ++;
                }
            }
        }
        return result;
    }

}

