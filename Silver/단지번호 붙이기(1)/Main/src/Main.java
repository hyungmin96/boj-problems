import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static class Position {
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int answer = 0;
    private static ArrayList<Integer> numbers = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] board = new int[n][n];
        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1};

        for (int i = 0; i < n; i++) {
            board[i] = Arrays.stream(br.readLine().split("")).mapToInt(item -> Integer.parseInt(item)).toArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1) {
                    solution(new Position(i, j), board, dx, dy);
                    answer ++;
                }
            }
        }
        Collections.sort(numbers);
        System.out.println(answer);
        for(int number : numbers) System.out.println(number);
    }

    public static void solution(Position position, int[][] board, int[] dx, int[] dy) {

        int count = 0;
        Queue<Position> que = new LinkedList<>();
        que.offer(position);
        
        while (!que.isEmpty()) {
            Position curr = que.poll();
            for (int i = 0; i < dx.length; i++) {
                int nrow = curr.x + dx[i];
                int ncol = curr.y + dy[i];
                if (nrow >= 0 && ncol >= 0 && nrow < board.length && ncol < board.length && board[nrow][ncol] != 0) {
                    board[nrow][ncol] = 0;
                    que.offer(new Position(nrow, ncol));
                    count ++;
                }
            }
        }
        count = (count == 0) ? 1 : count;
        numbers.add(count);
    }
}
