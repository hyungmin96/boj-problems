import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {

    private static int N;
    private static int[][] board;
    private static boolean[] visited;
    private static int[] orders;
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for(int i = 0; i < N; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int idx = 0;
            while(st.hasMoreTokens()){
                board[i][idx++] = Integer.parseInt(st.nextToken());
            }
        }

        orders = new int[N + 1];
        visited = new boolean[N];
        dfs(0, 0);
        System.out.println(answer);
    }

    public static void dfs(int depth, int index){
        if(depth == N){
            orders[orders.length - 1] = orders[0];
            check();
            return;
        }
        for(int i = index; i < N; i ++){
            if(!visited[i]){
                visited[i] = true;
                orders[depth] = i;
                dfs(depth + 1, index);
                orders[depth] = 0;
                visited[i] = false;
            }
        }
    }

    public static void check(){
        int temp = 0;
        for(int i = 0; i < orders.length - 1; i ++){
            if(board[orders[i]][orders[i + 1]] != 0)
                temp += board[orders[i]][orders[i + 1]];
            else{
                temp = Integer.MAX_VALUE;
                break;
            }
        }
        answer = answer > temp ? temp : answer;
    }
}
