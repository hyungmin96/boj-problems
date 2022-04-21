import java.io.*;
import java.util.StringTokenizer;
public class Main {

    private static int[][] dice = new int[][]{
        {0, 2, 0},
        {4, 1, 3},
        {0, 5, 0},
        {0, 6, 0},
    };
    private static int[] pos = new int[2];
    private static int N, M, K;
    private static int[][] board;
    private static int[] dx = new int[] {-1, 0, 1, 0 };
    private static int[] dy = new int[] { 0, 1, 0,-1 };
    private static int answer = 0;
    private static int temp = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        boolean[][] visited = new boolean[N][N];

        for(int i = 0; i < N; i ++){
            int idx = 0;
            st = new StringTokenizer(br.readLine(), " ");
            while(st.hasMoreTokens()){
                board[i][idx++] = Integer.parseInt(st.nextToken());
            }
        }
        solution(visited);
    }

    public static void solution(boolean[][] visited){
        int d = 1;
        for(int k = 0; k < K; k ++){
            temp = 1;
            visited = new boolean[N][M];
            d = moveDice(d);
            changeDice(d);
            d = checkDirection(d);
            visited[pos[0]][pos[1]] = true;
            getScore(board[pos[0]][pos[1]], pos[0], pos[1], visited);
            answer += board[pos[0]][pos[1]] * temp;
        }
        System.out.println(answer);
    }

    public static void getScore(int cur, int r, int c, boolean[][] visited){
        for(int i = 0; i < 4; i ++){
            int nr = r + dx[i];
            int nc = c + dy[i];
            if(nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc] || board[nr][nc] != cur){
                continue;
            }
            temp ++;
            visited[nr][nc] = true;
            getScore(cur, nr, nc, visited);
        }
    }

    public static int checkDirection(int d){
        if(dice[3][1] > board[pos[0]][pos[1]])
            return d = ((d + 1) % dx.length);
        else if(dice[3][1] < board[pos[0]][pos[1]])
            return d = ((d + 3) % dx.length);
        else 
            return d;
    }

    private static int moveDice(int d){
        int nr = pos[0] + dx[d];
        int nc = pos[1] + dy[d];
        if(nr < 0 || nc < 0 || nr >= N || nc >= M){
            d = ((d + 2) % dx.length);
            nr = pos[0] + dx[d];
            nc = pos[1] + dy[d];
        }
        pos[0] = nr;
        pos[1] = nc;
        return d;
    }

    private static void changeDice(int d){
        int temp = -1;
        switch(d){
            // 위, 오, 아, 왼
            case 0:
                temp = dice[0][1];
                dice[0][1] = dice[1][1];
                dice[1][1] = dice[2][1];
                dice[2][1] = dice[3][1];
                dice[3][1] = temp;
                break;
            case 1:
                temp = dice[1][2];
                dice[1][2] = dice[1][1];
                dice[1][1] = dice[1][0];
                dice[1][0] = dice[3][1];
                dice[3][1] = temp;
                break;
            case 2:
                temp = dice[3][1];
                dice[3][1] = dice[2][1];
                dice[2][1] = dice[1][1];
                dice[1][1] = dice[0][1];
                dice[0][1] = temp;
                break;
            case 3:
                temp = dice[1][0];
                dice[1][0] = dice[1][1];
                dice[1][1] = dice[1][2];
                dice[1][2] = dice[3][1];
                dice[3][1] = temp;
                break;
        }
    }
}
