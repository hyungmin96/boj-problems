import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {

    private static int N;
    private static int[][] board;
    private static int answer = Integer.MAX_VALUE;
    private static int[] dy = new int[] { -1, 0, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N + 1][N + 1];

        for(int i = 1; i <= N; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int index = 1;
            while(st.hasMoreTokens()){
                board[i][index++] = Integer.parseInt(st.nextToken());
            }
        }

        int[] temp = new int[4];
        solution(0, temp);
        System.out.println(answer);
    }

    public static void solution(int depth, int[] temp){
        if(depth == 4){
            int x = temp[0];
            int y = temp[1];
            int d1 = temp[2];
            int d2 = temp[3];
            if(isAvailableSection(x, y, d1, d2)){
                int[][] tempBoard = copyBoard();
                int result = makeSection(x, y, d1, d2, tempBoard);
                answer = answer > result ? result : answer;
            }
            return;
        }

        for(int i = 1; i <= N; i ++){
            temp[depth] = i;
            solution(depth + 1, temp);
        }
    }

    public static int makeSection(int x, int y, int d1, int d2, int[][] tempBoard){
        int[] sections = new int[5];
        sections[4] = makeBoudary(x, y, d1, d2, tempBoard);
        for(int i = 1; i <= N; i ++){
            for(int j = 1; j <= N; j ++){
                if(i < x + d1 && j <= y){
                    sections[0] += tempBoard[i][j];
                }
                if(i <= x + d2 && j > y && j <= N){
                    sections[1] += tempBoard[i][j];
                }
                if(x + d1 <= i && i <= N && j < y - d1 + d2){
                    sections[2] += tempBoard[i][j];
                }
                if(x + d2 < i && i <= N && y - d1 + d2 <= j && j <= N){
                    sections[3] += tempBoard[i][j];
                }
            }
        }
        Arrays.sort(sections);
        return sections[4] - sections[0];
    }

    public static int makeBoudary(int x, int y, int d1, int d2, int[][] tempBoard) {
        Queue<int[]> que = new LinkedList<>();
        boolean[][] visited = new boolean[N + 1][N + 1];
        que.offer(new int[] { x, y });
        visited[x][y] = true;
        int result = 0;
        int max_left = y - d1;
        int max_right = y + d2;

        while (!que.isEmpty()) {
            int size = que.size();
            int row = 0; int col = 0;
            boolean flag = true;
            for(int i = 0; i < size; i ++){
                int[] curr = que.poll();
                row = curr[0];
                col = curr[1];
                if (col >= max_left && col <= max_right) {
                    result += tempBoard[row][col];
                    tempBoard[row][col] = 0;
                    for (int j = 0; j < dy.length; j++) {
                        int nr = row + 1;
                        int nc = col + dy[j];
                        if (nr < 0 || nc < 0 || nr > N || nc > N || nc < max_left || nc > max_right || visited[nr][nc])
                        continue;
                        if (max_left == max_right) return result;
                        que.offer(new int[] { nr, nc });
                        visited[nr][nc] = true;
                    }
                }
                if (flag && col == max_left) {
                    max_left += dy[2];
                    flag = false;
                }
                if (col == max_right) max_right += dy[0];
            }
        }

        return result;
    }

    public static boolean isAvailableSection(int x, int y, int d1, int d2){
        if(x + d1 > N || y - d1 < 0 || x + d2 > N || y + d2 > N) return false;
        if(x + d1 + d2 > N || y - d1 + d2 < 0 || y - d1 + d2 > N) return false;
        if(x + d2 + d1 > N || y + d2 - d1 < 0 || y + d2 - d1 > N) return false;
        return true;
    }

    public static int[][] copyBoard(){
        int[][] temp = new int[N+1][N+1];
        for(int i = 1; i <= N; i ++){
            for(int j = 1; j <= N; j ++){
                temp[i][j] = board[i][j];
            }
        }
        return temp;
    }
}
