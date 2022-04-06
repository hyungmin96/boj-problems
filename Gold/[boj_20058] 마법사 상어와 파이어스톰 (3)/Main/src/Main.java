import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.*;
public class Main {

    private static int N, Q, sum;
    private static int[][] board;
    private static int[] dx = new int[] {-1, 0, 1, 0 };
    private static int[] dy = new int[] { 0, 1, 0,-1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = (int)Math.pow(2, Integer.parseInt(st.nextToken()));
        Q = (int)Integer.parseInt(st.nextToken());
        int[] size = new int[Q];
        board = new int[N][N];

        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int index = 0;
            while(st.hasMoreTokens()){
                int input = Integer.parseInt(st.nextToken());
                board[i][index++] = input;
                sum += input;
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < Q; i ++){
            size[i] = (int)Math.pow(2, Integer.parseInt(st.nextToken()));
        }
        solution(size);
    }

    public static void solution(int[] size){
        for(int i = 0; i < size.length; i ++){
            rotate(size[i]);
            meltIce();
        }
        int[] temp = getIce();
        System.out.println(temp[0] + "\n" + temp[1]);
    }

    public static void rotate(int l){
        int[][] temp = new int[N][N];
        for(int i = 0; i < N; i += l){
            for(int j = 0; j < N; j += l){
                for(int m = 0; m < l; m ++){
                    for(int n = 0; n < l; n ++){
                        temp[i + m][j + n] = board[l + i - n - 1][j + m];
                    }
                }
            }
        }
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j ++){
                board[i][j] = temp[i][j];
            }
        }
    }

    public static void meltIce(){
        ArrayList<int[]> temp = new ArrayList<>();
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j ++){
                if(board[i][j] > 0){
                    int num = 4;
                    for(int k = 0; k < dx.length; k ++){
                        int nr = i + dx[k];
                        int nc = j + dy[k];
                        if(nr < 0 || nc < 0 || nr >= N || nc >= N || board[nr][nc] == 0){
                            sum --;
                            num --;
                            if(num < 3){
                                temp.add(new int[] { i, j });
                                break;
                            }
                        }
                    }
                }
            }
        }

        for(int i = 0; i < temp.size(); i ++){
            board[temp.get(i)[0]][temp.get(i)[1]] --;
        }
    }

    public static int[] bfs(int r, int c){
        int ret = board[r][c];
        int cnt = 1;
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> que = new LinkedList<>();

        que.offer(new int[] { r, c });
        visited[r][c] = true;
        board[r][c] = 0;

        while(!que.isEmpty()){
            int[] curr = que.poll();
            for(int i = 0; i < dx.length; i ++){
                int nr = curr[0] + dx[i];
                int nc = curr[1] + dy[i];
                if(nr < 0 || nc < 0 || nr >= N || nc >= N || board[nr][nc] <= 0 || visited[nr][nc]){
                    continue;
                }
                ret += board[nr][nc];
                visited[nr][nc] = true;
                board[nr][nc] = 0;
                que.offer(new int[] { nr, nc });
                cnt ++;
            }
        }

        return new int[] { ret, cnt};
    }

    public static int[] getIce(){
        int total = 0;
        int big = 0;
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j ++){
                if(board[i][j] > 0){
                    int[] temp = bfs(i, j);
                    total += temp[0];
                    big = Math.max(temp[1], big);
                }
            }
        }
        big = (big == 1) ? 0 : big;
        return new int[] { total, big };
    }
}
