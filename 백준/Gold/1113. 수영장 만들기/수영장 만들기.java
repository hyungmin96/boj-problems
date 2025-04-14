import java.io.*;
import java.util.*;
public class Main {

    static int N, M;
    static int[][] map;
    static int[][] dirs = {
        {-1,0},{0,1},{1,0},{0,-1}
    };

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        int maxHeight = 0;
        for(int i = 0; i < N; i ++){
            String s = br.readLine();
            for(int j = 0; j < M; j ++){
                map[i][j] = s.charAt(j) - '0';
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }

        int answer = 0;
        for(int h = 1; h <= maxHeight; h ++){
            boolean[][] v = new boolean[N][M];
            for(int r = 1; r < N - 1; r ++){
                for(int c = 1; c < M - 1; c ++){
                    if(!v[r][c] && map[r][c] <= h){
                        int tmp = bfs(r, c, h, v);
                        answer += tmp;
                    }
                }
            }
        }
        System.out.println(answer);
    }

    public static int bfs(int r, int c, int h, boolean[][] v){
        Queue<int[]> q = new LinkedList<>();

        v[r][c] = true;
        q.offer(new int[] { r, c, h });

        int cnt = 0;    
        boolean flag = true;
        while(!q.isEmpty()){
            cnt ++;
            int[] cur = q.poll();
            for(int d = 0; d < 4; d ++){
                int nr = cur[0] + dirs[d][0];
                int nc = cur[1] + dirs[d][1];
                
                if(isOutRange(nr, nc)){
                    flag = false;
                    continue;
                }

                if(v[nr][nc]){
                    continue;
                }

                if(map[nr][nc] <= h){
                    v[nr][nc] = true;
                    q.offer(new int[] { nr, nc, h });
                }
            }
        }
        if(!flag) 
            return 0;
            
        return cnt;
    }

    public static boolean isOutRange(int r, int c){
        return r < 0 || c < 0 || r >= N || c >= M;
    }
}