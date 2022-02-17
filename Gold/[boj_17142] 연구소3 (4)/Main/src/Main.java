import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int N, M;
    private static int[][] board;
    private static boolean[][] visited;
    private static int[] dx = new int[] {-1, 0, 1, 0 };
    private static int[] dy = new int[] { 0, 1, 0,-1 };
    private static ArrayList<int[]> pos;
    private static int virus_cnt = 0;
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pos = new ArrayList<>();
        board = new int[N][N];
        int empty_cnt = 0;
        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j ++){
                int v = Integer.parseInt(st.nextToken());
                board[i][j] = v;
                if(v == 0) empty_cnt ++;
                else if(v == 2){
                    pos.add(new int[] { i, j });
                    virus_cnt ++;
                } 
            }
        }

        if(empty_cnt != 0){
            boolean[] use = new boolean[virus_cnt];
            int[] arr = new int[M];
            generate(0, 0, empty_cnt, use, arr);
            System.out.println(answer == Integer.MAX_VALUE ? -1 : answer + 1);
        }else{
            System.out.println(0);
        }
    }

    public static int solution(int[] selected, int empty_cnt){
        Queue<int[]> que = new LinkedList<>();
        int[][] tempBoard = copyBoard();
        int temp = empty_cnt;
        visited = new boolean[N][N];

        for(int i : selected){
            que.offer(new int[] { pos.get(i)[0], pos.get(i)[1], 0 });
            visited[pos.get(i)[0]][pos.get(i)[1]] = true;
        }

        while (!que.isEmpty()) {
            int[] curr = que.poll();
            for (int i = 0; i < dx.length; i ++) {
                int nr = curr[0] + dx[i];
                int nc = curr[1] + dy[i];
                if (nr < 0 || nc < 0 || nr >= N || nc >= N || tempBoard[nr][nc] == 1 || visited[nr][nc])
                    continue;

                    que.offer(new int[] { nr, nc, curr[2] + 1 });
                    visited[nr][nc] = true;
                    temp--;
            }
            if (temp == 0) return curr[2];
        }
        return Integer.MAX_VALUE;
    }

    public static void generate(int depth, int index, int empty_cnt, boolean[] use, int[] arr){
        if(depth == M){
            int temp = solution(arr, empty_cnt);
            answer = answer > temp ? temp : answer;
            return;
        }

        for(int i = index; i < use.length; i ++){
            if(!use[i]){
                use[i] = true;
                arr[depth] = i;
                generate(depth + 1, i, empty_cnt, use, arr);
                use[i] = false;
            }
        }
    }

    public static int[][] copyBoard(){
        int[][] temp = new int[N][N];
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j ++){
                temp[i][j] = board[i][j];
            }
        }
        return temp;
    }
}
