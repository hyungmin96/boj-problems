import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {
    
    public int N, M;
    public long answer = 0;
    public int[][] map;
    public boolean[][] v;
    public int[][] dirs = {
        { 0, -1 },
        { -1, -1 },
        { -1, 0 },
        { -1, 1 },
        { 0, 1 },
        { 1, 1 },
        { 1, 0 },
        { 1, -1 }
    };
    Queue<int[]> q = new LinkedList<>();

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        v = new boolean[N][N];

        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j ++){
                int num = Integer.parseInt(st.nextToken());
                answer += num;
                map[i][j] = num;
            }
        }

        ArrayList<int[]> pos = new ArrayList<>();
        q.offer(new int[] { N - 1, 0 });
        q.offer(new int[] { N - 1, 1 });
        q.offer(new int[] { N - 2, 0 });
        q.offer(new int[] { N - 2, 1 });

        for(int i = 0; i < M; i ++){
            st = new StringTokenizer(br.readLine(), " ");

            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            move(d, s); // 구름 이동 후 비를 1씩 내리고 구름 삭제(체크)
            duplicateWater();
            createCloud();
        }

        System.out.println(answer);
    }

    public void createCloud(){
        for(int r = 0; r < N; r ++){
            for(int c = 0; c < N; c ++){
                if(map[r][c] > 1 && !v[r][c]){
                    map[r][c] -= 2;
                    answer -= 2;
                    q.offer(new int[] { r, c });
                }
            }
        }
        v = new boolean[N][N];
    }

    public void move(int d, int s){
        for(int[] p : q){
            p[0] = (N + p[0] + (dirs[d][0] * (s % N))) % N;
            p[1] = (N + p[1] + (dirs[d][1] * (s % N))) % N;

            map[p[0]][p[1]] ++;
            answer ++;

            v[p[0]][p[1]] = true;
        }
    }

    public void duplicateWater(){
        while(!q.isEmpty()){
            int[] p = q.poll();
            int cnt = 0;
            for(int d = 1; d < 8; d += 2){
                int nr = p[0] + dirs[d][0];
                int nc = p[1] + dirs[d][1];
                if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                if(map[nr][nc] > 0){
                    cnt ++;
                }
            }

            answer += cnt;
            map[p[0]][p[1]] += cnt;
        }
    }
}