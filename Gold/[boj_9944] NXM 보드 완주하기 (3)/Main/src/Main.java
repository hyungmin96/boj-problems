import java.io.*;

public class Main {

    private static int[] dx = new int[] { -1, 0, 1, 0 };
    private static int[] dy = new int[] { 0, 1, 0, -1 };
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        int t = 0;

        while ((input = br.readLine()) != null) {
            int n = Integer.parseInt(input.split(" ")[0]);
            int m = Integer.parseInt(input.split(" ")[0]);
            int cells = 0;
            char[][] board = new char[n][m];
            answer = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                char[] arr = br.readLine().toCharArray();
                for (int j = 0; j < m; j++) {
                    board[i][j] = arr[j];
                    if (board[i][j] == '.')
                        cells++;
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == '.') {
                        board[i][j] = '*';
                        solution(n, m, i, j, board, 0, 1, cells);
                        board[i][j] = '.';
                    }
                }
            }
            if (answer == Integer.MAX_VALUE)
                System.out.println("Case " + ++t + ": " + -1);
            else
                System.out.println("Case " + ++t + ": " + answer);
        }
    }

    public static void solution(int n, int m, int row, int col, char[][] board, int depth,
            int move, int cells) {

        if (cells == move) {
            answer = Math.min(answer, depth);
            return;
        } else {

            for (int i = 0; i < dx.length; i++) {
                int next_row = row + dx[i];
                int next_col = col + dy[i];
                int cnt = 0;

                if (isRange(next_row, next_col, n, m) && board[next_row][next_col] == '.') {
                    while (true) {
                        if (isRange(next_row, next_col, n, m) && board[next_row][next_col] == '.') {
                            board[next_row][next_col] = '*';
                            cnt++;
                        } else break;
                        next_row += dx[i];
                        next_col += dy[i];
                    }

                    next_row -= dx[i];
                    next_col -= dy[i];
                    solution(n, m, next_row, next_col, board, depth + 1, move + cnt, cells);

                    for (int j = 0; j < cnt; j++) {
                        board[next_row][next_col] = '.';
                        next_row -= dx[i];
                        next_col -= dy[i];
                    }
                }
            }

        }
    }

    public static boolean isRange(int next_row, int next_col, int n, int m) {
        if (next_row >= 0 && next_col >= 0 && next_row < n && next_col < m)
            return true;
        return false;
    }

}
