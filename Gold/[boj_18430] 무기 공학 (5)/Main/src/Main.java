import java.io.*;
import java.util.StringTokenizer;
public class Main {

    private static int N, M;
    private static int[][] board;
    private static boolean[][] visited;
    private static int answer = 0;
    private static int[][] figures = {
        { 1,-1 },
        {-1,-1 },
        {-1, 1 },
        { 1, 1 }
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int idx = 0;
            while(st.hasMoreTokens()){
                board[i][idx++] = Integer.parseInt(st.nextToken());
            }
        }

        solution(0, 0);
        System.out.println(answer);
    }

    public static void solution(int depth, int temp){
        if(depth == N * M){
            answer = (answer > temp) ? answer : temp;
            return;
        }

        int r = depth / M;
        int c = depth % M;

        for(int i = 0; i < 4; i ++){
            if(!visited[r][c] && isAvailableFigure(r, c, i)){
                int nr = r + figures[i][0];
                int nc = c + figures[i][1];
                solution(depth + 1, temp + (board[r][c] * 2) + board[r][nc] + board[nr][c]);
                visited[r][c] = false;
                visited[r][nc] = false;
                visited[nr][c] = false;
            }
        }
        solution(depth + 1, temp);
    }

    public static boolean isAvailableFigure(int r, int c, int idx){
        if(idx == 0 && r + 1 < N && c - 1 >= 0 && !visited[r + 1][c] && !visited[r][c - 1]){ // 1번 도형
            visited[r][c] = true;
            visited[r][c - 1] = true;
            visited[r + 1][c] = true;
            return true;
        }else if(idx == 1 && r - 1 >= 0 && c - 1 >= 0 && !visited[r - 1][c] && !visited[r][c - 1]){
            visited[r][c] = true;
            visited[r][c - 1] = true;
            visited[r - 1][c] = true;
            return true;
        }else if(idx == 2 && r - 1 >= 0 && c + 1 < M && !visited[r - 1][c] && !visited[r][c + 1]){
            visited[r][c] = true;
            visited[r][c + 1] = true;
            visited[r - 1][c] = true;
            return true;
        }else if(idx == 3 && r + 1 < N && c + 1 < M && !visited[r + 1][c] && !visited[r][c + 1]){
            visited[r][c] = true;
            visited[r][c + 1] = true;
            visited[r + 1][c] = true;
            return true;
        }

        return false;
    }
}
