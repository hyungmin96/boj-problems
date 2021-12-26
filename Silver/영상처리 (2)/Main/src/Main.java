import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int answer = 0;
    private static int[] dx = new int[] {-1, 0, 1, 0};
    private static int[] dy = new int[] { 0, 1, 0,-1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] board = new int[n][k * 3];
        for (int i = 0; i < n; i++)
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();

        int t = Integer.parseInt(br.readLine());

        int[][] newBoard = new int[n][k];

        for (int i = 0; i < n; i++) {
            for(int j = 0; j < k; j ++){
                int temp = 0;
                for(int z = 0; z < 3; z ++){
                    temp += board[i][j * 3 + z];
                }
                if(temp / 3 >= t) newBoard[i][j] = 1;
            }
        }

        for(int i = 0; i < n; i ++)
            for(int j = 0; j < k; j ++)
                if(newBoard[i][j] == 1){
                    answer ++;
                    solution(n, k, t, i, j, newBoard);
                }

        System.out.println(answer);
    }

    public static int solution(int n, int k, int t, int row, int col, int[][] newBoard) {
        int answer = 0;
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] {row, col});
        newBoard[row][col] = 0;
        
        while(!que.isEmpty()){
            int[] curr = que.poll();
            for(int i = 0; i < 4; i ++){
                int nRow = curr[0] + dx[i];
                int nCol = curr[1] + dy[i];
                if(nRow >= 0 && nCol >= 0 && nRow < n && nCol < k && newBoard[nRow][nCol] == 1){
                    newBoard[nRow][nCol] = 0;
                    que.offer(new int[]{nRow, nCol});
                }
            }
        }

        return answer;
    }
}
