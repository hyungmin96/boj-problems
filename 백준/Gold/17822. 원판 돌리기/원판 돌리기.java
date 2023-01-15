import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static boolean isDelete = false, find = false;
    static int[][] dirs = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] commands = new int[K][3];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int nums = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int move = Integer.parseInt(st.nextToken());
            commands[i] = new int[] { nums, dir, move };
        }

        System.out.println(solution(commands, board));
    }

    public static int solution(int[][] commands, int[][] board) {
        for (int i = 0; i < K; i++) {
            int n = commands[i][0];
            int d = commands[i][1];
            int m = commands[i][2];

            for (int j = 1; j * n <= board.length; j++)
                spin(d, m, (j * n) - 1, board);

            isDelete = false;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    find = false;
                    if (board[r][c] != 0) {
                        delete(r, c, board[r][c], board);
                    }
                    if (find) {
                        isDelete = true;
                        board[r][c] = 0;
                    }
                }
            }

            if (!isDelete) {
                int sum = 0;
                int count = 0;
                for (int k = 0; k < board.length; k++) {
                    for (int j = 0; j < board[k].length; j++) {
                        if (board[k][j] != 0) {
                            sum += board[k][j];
                            count ++;
                        }
                    }
                }

                double aver = (float) sum / (float) count;
                for (int k = 0; k < board.length; k++) {
                    for (int j = 0; j < board[k].length; j++) {
                        if (board[k][j] == 0)
                            continue;
                        if (board[k][j] > aver) {
                            board[k][j]--;
                        } else if(board[k][j] < aver) {
                            board[k][j]++;
                        }
                    }
                }
            }
        }

        int answer = 0;
        for (int k = 0; k < board.length; k++) {
            for (int j = 0; j < board[k].length; j++) {
                answer += board[k][j];
            }
        }
        return answer;
    }

    public static void delete(int r, int c, int cur, int[][] board) {
        for (int d = 0; d < 4; d++) {
            int nr = r + dirs[d][0];
            int nc = (c + dirs[d][1]) % M;

            if (nc < 0) nc = M - 1;
            if (nr >= N || nr < 0 || nc < 0 || nc >= M) continue;
            if (board[nr][nc] != cur) continue;
            find = true;
            board[nr][nc] = 0;
            delete(nr, nc, cur, board);
        }
    }

    public static void spin(int dir, int m, int idx, int[][] board) {
        int[] temp = new int[M];
        for (int j = 0; j < m; j++) {
            if (dir == 0) { // 시계방향
                int t = board[idx][M - 1];
                for (int i = M - 1; i > 0; i--)
                    temp[i] = board[idx][i - 1];
                temp[0] = t;
            } else {
                int t = board[idx][0];
                for (int i = 0; i < M - 1; i++)
                    temp[i] = board[idx][i + 1];
                temp[M - 1] = t;
            }
            board[idx] = temp;
        }
    }
}