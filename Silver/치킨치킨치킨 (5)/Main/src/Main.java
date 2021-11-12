import java.io.*;
import java.util.StringTokenizer;
public class Main {

    private static final int SELECT_NUMBER = 3;
    private static int answer = 0;
    private static int result = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(stz.nextToken()); // 사람 수
        int m = Integer.parseInt(stz.nextToken()); // 각 치킨의 선호도

        int[][] board = new int[n][m];
        for(int i = 0; i < n; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int[] preference = new int[m];
            for(int j = 0; j < m; j ++){
                preference[j] = Integer.parseInt(st.nextToken());
            }
            board[i] = preference;
        }

        boolean[] visited = new boolean[m];
        int[] output = new int[SELECT_NUMBER];

        dfs(0, 0, n, m, board, output, visited);
        System.out.println(result);
    }

    public static void dfs(int depth, int index, int n, int m, int[][] board, int[] output, boolean[] visited){
        if(depth == SELECT_NUMBER){

            for(int i = 0; i < n; i ++){
                int max = Integer.MIN_VALUE;
                for(int item : output){
                    max = (board[i][item - 1] > max) ? board[i][item - 1] : max;
                }
                answer += max;
            }

            result = (answer > result) ? answer : result;
            answer = 0;
            return;
        }else{
            for(int i = index; i < m; i ++){
                if(!visited[i]){
                    visited[i] = true;
                    output[depth] = i + 1;
                    dfs(depth + 1, i + 1, n, m, board, output, visited);
                    visited[i] = false;
                }
            }
        }
    }
}
