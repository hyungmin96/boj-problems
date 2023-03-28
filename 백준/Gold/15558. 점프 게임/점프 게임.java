import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static int[][] map, move;
    static boolean[] is_delete;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[2][N];
        move = new int[][]{ { 0, 1 }, { 0, -1 }, { 1, K } };
        is_delete = new boolean[N];

        for(int j = 0; j < 2; j ++){
            String str = br.readLine();
            for(int i = 0; i < N; i ++){
                map[j][i] = str.charAt(i) - '0';
            }
        }

        System.out.println(bfs() ? "1" : "0");
    }

    public static boolean bfs(){
        boolean[][] check = new boolean[2][N];
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] { 0, 0 }); // row, col
        check[0][0] = true;

        int idx = 0;
        while(!que.isEmpty()){
            int size = que.size();
            for(int i = 0; i < size; i ++){
                int[] cur = que.poll();
                if(is_delete[cur[1]]) continue;
                for(int[] m : move){
                    int nr = (cur[0] + m[0]) % 2;
                    int nc = cur[1] + m[1];
                    if(nc >= N) return true;
                    if(nc < 0 || map[nr][nc] == 0 || check[nr][nc] || is_delete[nc]) continue;
                    check[nr][nc] = true;
                    que.offer(new int[] { nr, nc });
                }
            }
            is_delete[idx ++] = true;
        }
        return false;
    }
}