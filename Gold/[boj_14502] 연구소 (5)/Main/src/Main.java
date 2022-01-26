import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
   
    public static class Virus{
        int x; int y;
        public Virus(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    private static int N;
    private static int M;
    private static int[][] board;
    private static boolean[][] visited;
    private static int[] dx = new int[] {-1, 0, 1, 0};
    private static int[] dy = new int[] { 0, 1, 0,-1};
    private static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        ArrayList<Virus> virus_position = new ArrayList<>();
        for(int i = 0; i < N; i ++){
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(item -> Integer.parseInt(item)).toArray();
            for(int j = 0; j < input.length; j ++){
                if(input[j] == 2) virus_position.add(new Virus(i, j));
                board[i][j] = input[j];
            }
        }

        for (int a = 0; a < N; a++) {
            for (int b = 0; b < M; b++) {
                if (board[a][b] != 0) continue;
                board[a][b] = 1;
                for (int c = 0; c < N; c++) {
                    for (int d = 0; d < M; d++) {
                        if (board[c][d] != 0) continue;
                        board[c][d] = 1;
                        for (int e = 0; e < N; e++) {
                            for (int f = 0; f < M; f++) {
                                if (board[e][f] != 0) continue;
                                visited = new boolean[N][M];
                                board[e][f] = 1;
                                int temp = solution(virus_position, visited);
                                answer = (answer > temp) ? answer : temp;
                                board[a][b] = 1;
                                board[c][d] = 1;
                                board[e][f] = 0;
                            }
                        }
                        board[c][d] = 0;
                    }
                }
                board[a][b] = 0;
            }
        }

        System.out.println(answer);

    }

    public static int solution(ArrayList<Virus> viruses, boolean[][] visited){
        
        Queue<Virus> que = new LinkedList<>();

        for(Virus virus : viruses){
            que.offer(virus);
            while(!que.isEmpty()){
                Virus curr = que.poll();
                for(int i = 0; i < dx.length; i ++){
                    int next_row = curr.x + dx[i];
                    int next_col = curr.y + dy[i];
                    if(next_row >= 0 && next_col >= 0 && next_row < N && next_col < M && board[next_row][next_col] == 0 && !visited[next_row][next_col]){
                        visited[next_row][next_col] = true;
                        que.offer(new Virus(next_row, next_col));
                    }
                }
            }
        }

        return getSafeZoneCount(visited);
    }

    public static int getSafeZoneCount(boolean[][] visited){
        int result = 0;

        for(int i = 0; i < N; i ++){
            for(int j = 0; j < M; j ++){
                if(board[i][j] == 0 && !visited[i][j]){
                    result ++;
                }
            }
        }

        return result;
    }
}
