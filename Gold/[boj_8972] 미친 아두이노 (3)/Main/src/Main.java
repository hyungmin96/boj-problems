import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;

    private static int[] dx = new int[] { 1, 1, 1, 0, 0, 0, -1, -1, -1 };
    private static int[] dy = new int[] {-1, 0, 1,-1, 0, 1, -1, 0, 1 };
    private static int[] location = new int[2]; // 종수의 위치
    private static Queue<int[]> que = new LinkedList<>();
        
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        char[][] board = new char[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            if (str.contains("I")) {
                location[0] = i;
                location[1] = str.indexOf("I");
            }
            if (str.contains("R")) {
                for (int z = 0; z < str.length(); z++)
                    if (str.charAt(z) == 'R'){
                        int[] arr = new int[] { i, z };
                        que.offer(arr);
                    }
            }
            board[i] = str.toCharArray();
        }

        String direction = br.readLine().trim();
        System.out.println(solution(board, direction));
    }

    public static String solution(char[][] board, String direction) {
        
        int turn = 1;
        for (int i = 0; i < direction.length(); i++) {
            int cRow = location[0];
            int cCol = location[1];
            board[cRow][cCol] = '.';

            int x = direction.charAt(i) - 49;
            cRow += dx[x]; cCol += dy[x];

            if(board[cRow][cCol] == 'R'){
                return "kraj " + turn;
            }else{
                board[cRow][cCol] = 'I';
                location = new int[]{cRow, cCol};
            }

            if(!moveArdoino(cRow, cCol, board))            
                return "kraj " + turn;
            turn++;
        }
        return printBoard(board);
    }

    public static boolean moveArdoino(int cRow, int cCol, char[][] board){
        int nums = que.size();
        int[][] numbers = new int[N][M];

        for(int k = 0; k < nums; k ++){
            int[] currPoint = que.poll();
            int ardoinoR = currPoint[0];
            int ardoinoC = currPoint[1];

            board[ardoinoR][ardoinoC] = '.';

            if(ardoinoR > cRow) ardoinoR --;
            else if(ardoinoR < cRow) ardoinoR ++;
            
            if(ardoinoC > cCol) ardoinoC --;
            else if(ardoinoC < cCol) ardoinoC ++;

            if(board[ardoinoR][ardoinoC] == 'I') return false;
            numbers[ardoinoR][ardoinoC] ++;
        }

        for(int i = 0; i < N; i ++)
            for(int j = 0; j < M; j ++)
                if(numbers[i][j] == 1){
                    board[i][j] = 'R';
                    que.offer(new int[] {i, j});
                }

        return true;
    }

    public static String printBoard(char[][] board){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < board.length; i ++){
            for(char c : board[i]){
                sb.append(c);
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
