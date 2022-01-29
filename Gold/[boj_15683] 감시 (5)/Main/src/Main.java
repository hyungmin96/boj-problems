import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;
    private static int[][] board;
    private static int[] dx = new int[] { -1, 0, 1, 0 };
    private static int[] dy = new int[] { 0, 1, 0, -1 };
    private static int answer = Integer.MAX_VALUE;

    private static int[][][] cameras = {
            { { 0 } },
            { { 1 }, { 2 }, { 3 }, { 0 } },
            { { 1, 3 }, { 0, 2 } },
            { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 0 } },
            { { 0, 1, 3 }, { 0, 1, 2 }, { 1, 2, 3 }, { 2, 3, 0 } },
            { { 1, 2, 3, 0 } }
    };

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        ArrayList<int[]> pos = new ArrayList<>();
        int blind_spot_count = 0;
        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(item -> Integer.parseInt(item)).toArray();
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j] == 0) blind_spot_count++;
                if (board[i][j] >= 1 && board[i][j] <= 5)
                    pos.add(new int[] { board[i][j], i, j });
            }
        }

        solution(0, blind_spot_count, pos, cameras);
        System.out.println(answer);
    }

    public static void solution(int depth, int blind_spot_count, ArrayList<int[]> pos, int[][][] cameras) {

        if (pos.size() == depth) {
            answer = Math.min(answer, getBlindArea(board));
            return;
        }

        ArrayList<int[]> spot = new ArrayList<>();
        for (int i = 0; i < cameras[pos.get(depth)[0]].length; i++) {
            for (int j = 0; j < cameras[pos.get(depth)[0]][i].length; j++) {
                int next_row = pos.get(depth)[1];
                int next_col = pos.get(depth)[2];
                while (true) {
                    next_row += dx[cameras[pos.get(depth)[0]][i][j]];
                    next_col += dy[cameras[pos.get(depth)[0]][i][j]];
                    if (next_row < 0 || next_col < 0 || next_row >= N || next_col >= M || board[next_row][next_col] == 6)
                        break;

                    if (board[next_row][next_col] == 0){
                        board[next_row][next_col] = 7;
                        spot.add(new int[] { next_row, next_col });
                    }
                }
            }
            solution(depth + 1, blind_spot_count, pos, cameras);
            for(int[] item : spot)
                board[item[0]][item[1]] = 0;
        }
    }

    public static int getBlindArea(int[][] temp_board){
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(temp_board[i][j] == 0) result ++;
            }
        }
        return result;
    }

}
