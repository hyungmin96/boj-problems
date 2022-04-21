import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {

    public static class Info{
        int r, c, val, time;
        public Info(int r, int c, int val, int time){
            this.r = r;
            this.c = c;
            this.val = val;
            this.time = time;
        }
    }

    private static int N, M, G, R;
    private static int[][][] board;
    private static int[] dx = new int[] {-1, 0, 1, 0 };
    private static int[] dy = new int[] { 0, 1, 0,-1 };
    private static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        board = new int[N][M][2];
        ArrayList<int[]> availableSpace = new ArrayList<>();
        
        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int idx = 0;
            while(st.hasMoreTokens()){
                int in = Integer.parseInt(st.nextToken());
                if(in == 2) availableSpace.add(new int[] { i, idx });
                board[i][idx++] = new int[] { in, 0 };
            }
        }
        int[] orders = new int[availableSpace.size()];
        solution(availableSpace.size(), 0, 0, 0, availableSpace, orders);
        System.out.println(answer);
    }

    public static int spread(ArrayList<int[]> availableSpace, int[] orders){
        // 0 : 호수, 1 : 땅, 2 : 빈 땅, 
        Queue<Info> que = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][5];
        int[][][] temp = copyBoard();
        int tempCnt = 0;

        for(int i = 0; i < orders.length; i ++){
            if(orders[i] > 0){
                int val = orders[i];
                int r = availableSpace.get(i)[0];
                int c = availableSpace.get(i)[1];
                que.offer(new Info(r, c, val, 0));
                temp[r][c] = new int[] { val, -1 };
                visited[r][c][val] = true;
            }
        }

        while(!que.isEmpty()){
            Info curr = que.poll();
            if(temp[curr.r][curr.c][0] != 7){
                for(int d = 0; d < dx.length; d ++){
                    int nr = curr.r + dx[d];
                    int nc = curr.c + dy[d];
                    if(nr < 0 || nc < 0 || nr >= N || nc >= M || temp[nr][nc][0] == 0 || visited[nr][nc][curr.val]){
                        continue;
                    }
                    visited[nr][nc][curr.val] = true;
                    if(visited[nr][nc][3] && visited[nr][nc][4] && temp[nr][nc][1] == curr.time){
                        temp[nr][nc][0] = 7;
                        temp[nr][nc][1] = -1;
                        tempCnt ++;
                    }else if((temp[nr][nc][0] <= 2) && temp[nr][nc][1] == 0){
                        temp[nr][nc][0] = curr.val;
                        temp[nr][nc][1] = curr.time;
                        que.offer(new Info(nr, nc, curr.val, curr.time + 1));
                    }
                }
            }
        }
        return tempCnt;
    }

    public static void solution(int n, int gCnt, int rCnt, int empty, ArrayList<int[]> availableSpace, int[] orders){
        if(gCnt > G) return;
        if(rCnt > R) return;
        if(empty > n - (G + R)) return;

        if(n == gCnt + rCnt + empty){
            answer = Math.max(answer, spread(availableSpace, orders));
            return;
        }

        int temp = gCnt + rCnt + empty;
        if(gCnt < G){
            orders[temp] = 3;
            solution(n, gCnt + 1, rCnt, empty, availableSpace, orders);
            orders[temp] = 0;
        }

        if(rCnt < R){
            orders[temp] = 4;
            solution(n, gCnt, rCnt + 1, empty, availableSpace, orders);
            orders[temp] = 0;
        }
        solution(n, gCnt, rCnt, empty + 1, availableSpace, orders);
    }

    public static int[][][] copyBoard(){
        int[][][] temp = new int[N][M][2];
        for(int r = 0; r < N; r ++){
            for(int c = 0; c < M; c ++){
                for(int j = 0 ; j < 2; j ++){
                    temp[r][c][j] = board[r][c][j];
                }
            }
        }
        return temp;
    }
}
