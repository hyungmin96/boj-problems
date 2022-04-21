import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int[][] board;
    private static int N;
    private static int[] dx = new int[] {-1, 0, 1, 0 };
    private static int[] dy = new int[] { 0, 1, 0,-1 };
    private static int[] orders;
    private static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        orders = new int[5];

        for(int i = 0; i < N; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int idx = 0;
            while(st.hasMoreTokens()){
                board[i][idx++] = Integer.parseInt(st.nextToken());
            }
        }
        solution(0);
        System.out.println(answer);
    }

    public static void solution(int depth){
        if(depth == 5){
            int[][] temp = copyBoard(board);
            check(temp);
            int val = findMaxValue(temp);
            answer = (val > answer) ? val : answer;
            return;
        }
        for(int i = 0; i < dx.length; i ++){
            orders[depth] = i;
            solution(depth + 1);
        }
    }

    public static void check(int[][] temp) {
        for (int d = 0; d < orders.length; d++) {
            boolean[][] isSum = new boolean[N][N];
            int r = 0;
            int row_val = 1;
            int col_val = 1;
            int row_last = N;
            int col_last = N;
            if(orders[d] == 2) {
                r = N - 1;
                row_last = -1;
                row_val = -1;
            }
            while (r != row_last) {
                int c = 0;
                if(orders[d] == 1){
                    c = N - 1;
                    col_last = -1;
                    col_val = -1;
                } 
                while(c != col_last) {
                    if (temp[r][c] != 0) {
                        int cr = r;
                        int cc = c;
                        int curr = temp[r][c];
                        while (true) {
                            int nr = cr + dx[orders[d]];
                            int nc = cc + dy[orders[d]];
                            if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
                                if (temp[nr][nc] == curr && !isSum[nr][nc]) {
                                    isSum[nr][nc] = true;
                                    temp[nr][nc] += curr;
                                    temp[cr][cc] = 0;
                                }else if(temp[nr][nc] == 0){
                                    temp[nr][nc] = curr;
                                    temp[cr][cc] = 0;
                                }else{
                                    break;
                                }
                            }else{
                                break;
                            }
                            cr = nr;
                            cc = nc;
                        }
                    }
                    c += col_val;
                }
                r += row_val;
            }
        }
    }

    public static int[][] copyBoard(int[][] t){
        int[][] temp = new int[N][N];
        for(int r = 0; r < N; r ++){
            for(int c = 0; c < N; c ++){
                temp[r][c] = t[r][c];
            }
        }
        return temp;
    }

    public static int findMaxValue(int[][] temp){
        int ret = 0;
        for(int r = 0; r < N; r ++){
            for(int c = 0; c < N; c ++){
                if(temp[r][c] != 0){
                    ret = (ret < temp[r][c]) ? temp[r][c] : ret;
                }
            }
        }
        return ret;
    }
}
