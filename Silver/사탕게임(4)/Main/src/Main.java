import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
        }

        System.out.println(solution(board));
    }

    public static int solution(char[][] board) {
        int answer = 0;

        answer = checkLength(board);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // 오른쪽, 아래 두 방향으로만 교환 가능
                // 맨 오른쪽에 있는 사탕은 오른쪽과 교환할 수 없으니 밑으로만 교환
                if (j + 1 < board[i].length && board[i][j] != board[i][j + 1]) {
                    // 오른쪽 사탕과 교환이 가능하고 다른 색의 사탕일 경우
                    swap(board, i, j, 1, 0);
                    answer = Math.max(answer, checkLength(board));
                    swap(board, i, j, 1, 0);
                }

                if (i + 1 < board.length && board[i][j] != board[i + 1][j]) {
                    // 아래와 교환이 가능하고 다른 색깔의 사탕일 경우
                    swap(board, i, j, 0, 1);
                    answer = Math.max(answer, checkLength(board));
                    swap(board, i, j, 0, 1);
                }
            }
        }

        return answer;
    }

    public static int checkLength(char[][] board){
        int length = Integer.MIN_VALUE;

        for(int x = 0; x < board.length; x ++){
            int count = 0;
            char current = board[x][0];
            for(int y = 0; y < board.length; y ++){
                if(board[x][y] == current){
                    count ++;
                    length = (length < count) ? count : length;
                    if(length == board.length) return board.length;
                }else{
                    current = board[x][y];
                    count = 1;
                }
            }
        }

        for(int y = 0; y < board.length; y ++){
            int count = 0;
            char current = board[0][y];
            for(int x = 0; x < board.length; x ++){
                if(board[x][y] == current){
                    count ++;
                    length = (length < count) ? count : length;
                    if(length == board.length) return board.length;
                }else{
                    current = board[x][y];
                    count = 1;
                }
            }
        }

        return length;
    }

    public static void swap(char[][] board, int i, int j, int right, int bellow) {
        char temp = board[i][j];
        board[i][j] = board[i + bellow][j + right];
        board[i + bellow][j + right] = temp;
    }
}
