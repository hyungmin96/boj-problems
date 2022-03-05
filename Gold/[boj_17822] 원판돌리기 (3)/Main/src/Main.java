import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
public class Main {

    private static int[][] board;
    private static int[] dx = new int[] { 0, 0,-1, 1 };
    private static int[] dy = new int[] {-1, 1, 0, 0 };
    private static int N, M, T;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        int[][] spins = new int[T][3];

        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int index = 0;
            while(st.hasMoreTokens()){
                board[i][index ++] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < T; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            spins[i] = new int[] { x, d, k };
        }

        System.out.println(solution(spins));
    }

    public static int solution(int[][] spins){
        for(int k = 0; k < T; k ++){
            spin(k, spins);
            boolean flag = false;

            for(int i = 0; i < N; i ++){
                for(int j = 0; j < M; j ++){
                    if(board[i][j] != 0){
                        if(j == 0){
                            if(board[i][j] == board[i][M - 1]){
                                board[i][j] = 0;
                                board[i][M - 1] = 0;
                                flag = true;
                            }
                        }else if(j == M - 1){
                            if(board[i][j] == board[i][0]){
                                board[i][j] = 0;
                                board[i][0] = 0;
                                flag = true;
                            }
                        }
                        for(int m = 0; m < dx.length; m ++){
                            int nr = i + dx[m];
                            int nc = j + dy[m];
                            if(nr < 0 || nc < 0 || nr >= N || nc >= M || board[nr][nc] == 0)
                                continue;
                            if(board[i][j] == board[nr][nc]){
                                flag = true;
                                board[i][j] = 0;
                                board[nr][nc] = 0;
                            }
                        }
                    }
                }
            }
            adjacentNumber(flag);
        }

        return getResult();
    }

    public static void adjacentNumber(boolean flag){
        if(!flag){
            int cnt = 0;
            int temp = 0;
            ArrayList<int[]> arr = new ArrayList<>();
            for(int i = 0; i < N; i ++){
                for(int j = 0; j < M; j ++){
                    if(board[i][j] != 0){
                        temp += board[i][j];
                        cnt ++;
                        arr.add(new int[] { i, j, board[i][j] });
                    }
                }
            }

            int average = temp / cnt;
            for(int[] item : arr){
                if(item[2] > average){
                    board[item[0]][item[1]] -= 1;
                }else{
                    board[item[0]][item[1]] += 1;
                }
            }
        }
    }

    public static void spin(int n, int[][] spins){
        for (int i = 0; i < N; i++) {
            if ((i + 1) % spins[n][0] == 0) {
                int dir = spins[n][1];
                int num = spins[n][2];
                int[] tempArr = board[i];
                for (int j = 0; j < num; j++) {
                    int temp = 0;
                    if (dir == 0) {
                        temp = tempArr[tempArr.length - 1];
                        for (int k = tempArr.length - 1; k > 0; k--) {
                            tempArr[k] = tempArr[k - 1];
                        }
                        tempArr[0] = temp;
                    } else {
                        temp = tempArr[0];
                        for (int k = 0; k < tempArr.length - 1; k++) {
                            tempArr[k] = tempArr[k + 1];
                        }
                        tempArr[tempArr.length - 1] = temp;
                    }
                }
                board[i] = tempArr;
            }
        }
    }

    public static int getResult(){
        int result = 0;
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < M; j ++){
                if(board[i][j] != 0){
                    result += board[i][j];
                }
            }
        }
        return result;
    }
}
