import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class App {

    private static int N;
    private static int[][] board;
    private static int answer = Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][2];

        for(int i = 0; i < N; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int idx = 0;
            while(st.hasMoreTokens()){
                board[i][idx++] = Integer.parseInt(st.nextToken());
            }
        }
        solution(0, 0);
        System.out.println(answer);
    }

    public static void solution(int start, int cnt){
        if(start == N){
            answer = (answer > cnt) ? answer : cnt;
            return;
        }

        if(board[start][0] <= 0 || cnt == N - 1){
            solution(start + 1, cnt);
            return;
        }

        for(int i = 0; i < N; i ++){
            if(i == start || board[i][0] <= 0) continue;
            board[start][0] -= board[i][1];
            board[i][0] -= board[start][1];
            if(board[start][0] <= 0) cnt ++;
            if(board[i][0] <= 0) cnt ++;
            solution(start + 1, cnt);
            if(board[start][0] <= 0) cnt --;
            if(board[i][0] <= 0) cnt --;
            board[start][0] += board[i][1];
            board[i][0] += board[start][1];
        }
    }
}
