import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int N, M, K;
    int[][] map, smell, idx;
    int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    int[][][] priority;
    HashMap<Integer, int[]> sharks = new HashMap<>();

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        idx = new int[N][N];
        smell = new int[N][N];
        priority = new int[M][4][4];

        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j ++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] > 0){
                    sharks.put(map[i][j], new int[] { i, j, -1 });
                }
            }
        }  

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < M; i ++){
            int d = Integer.parseInt(st.nextToken()) - 1;
            int[] t = sharks.get(i + 1);
            t[2] = d;
            sharks.put(i + 1, t);
        }

        for(int i = 0; i < M; i ++){
            for(int d = 0; d < 4; d ++){
                st = new StringTokenizer(br.readLine(), " ");
                for(int j = 0; j < 4; j ++){
                    priority[i][d][j] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }
        solve();
    }

    public void solve(){
        int cnt = -1;
        while(cnt ++ <= 1000 && M > 1){
            updateSmell();
            putSmell();
            moveSharks();
        }

        System.out.println(cnt > 1000 ? -1 : cnt);
    }

    public void moveSharks(){
        // 이동결과저장
        int[][] tmp = new int[N][N];
        for(int n : sharks.keySet()){
            int[] arr = sharks.get(n);
            if(arr[2] == -1){
                // 쫒겨난 상어
                continue;
            }

            int r = arr[0];
            int c = arr[1];
            int d = arr[2];

            boolean isSmell = true;
            int tmp_r = r, tmp_c = c;
            for(int s : priority[n - 1][d]){
                int nr = r + dirs[s][0];
                int nc = c + dirs[s][1];
                if(isOutRange(nr, nc)){
                    continue;
                }
                if(map[nr][nc] == 0 && smell[nr][nc] == 0){
                    // 냄새가 없는 빈 칸일 경우
                    arr[2] = s;
                    tmp_r = nr;
                    tmp_c = nc;
                    break;
                }else if(isSmell && smell[nr][nc] > 0 && idx[nr][nc] == n){
                    arr[2] = s;
                    tmp_r = nr;
                    tmp_c = nc;
                    isSmell = false;
                }
            }

            sharks.put(n, new int[] { tmp_r, tmp_c, arr[2] });
            if(tmp[tmp_r][tmp_c] > 0){
                if(n < tmp[tmp_r][tmp_c]){
                    sharks.put(tmp[tmp_r][tmp_c], new int[] { -1, -1, -1 });
                    tmp[tmp_r][tmp_c] = n;
                }else{
                    sharks.put(n, new int[] { -1, -1, -1 });
                }
                M --;
            }else{
                tmp[tmp_r][tmp_c] = n;
            }
        }

        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j ++){
                map[i][j] = tmp[i][j];
            }
        }
    }

    public void putSmell(){
        for(int n : sharks.keySet()){
            int[] arr = sharks.get(n);
            int r = arr[0];
            int c = arr[1];
            int d = arr[2];
            if(d == -1){
                continue;
            }
            smell[r][c] = K;
            idx[r][c] = n;
        }
    }

    public void updateSmell(){
        for(int r = 0; r < N; r ++){
            for(int c = 0; c < N; c ++){
                if(smell[r][c] > 0){
                    smell[r][c] --;
                    if(smell[r][c] == 0){
                        idx[r][c] = 0;
                    }
                }
            }
        }
    }

    public boolean isOutRange(int r, int c){
        return r < 0 || c < 0 || r >= N || c >= N;
    }
}

