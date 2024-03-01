import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int N, K, height = 1;
    int[][] pos, map, dirs = {{0,1},{0,-1},{-1,0},{1,0}};
    ArrayList<Integer>[][] list;

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        pos = new int[K + 1][2];
        list = new ArrayList[N][N];

        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j ++){
                int n = Integer.parseInt(st.nextToken());
                map[i][j] = n;
                list[i][j] = new ArrayList<>();
            }
        }

        for(int i = 0; i < K; i ++){
            st = new StringTokenizer(br.readLine(), " ");

            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;

            pos[i + 1] = new int[] { r, c, d };
            list[r][c].add(i + 1);

            height = Math.max(height, list[r][c].size());
        }
        
        solve();
    }

    public void solve(){
        int cnt = 0;
        while(height < 4 && cnt ++ <= 1000){
            move();
        }

        System.out.println(cnt > 1000 ? -1 : cnt);
    }

    public void move(){
        for(int i = 1; i <= K; i ++){
            int[] p = pos[i];

            int r = p[0];
            int c = p[1];
            int d = p[2];

            // 현재 말의 높이, 0이 맨 아래의 말
            int cur_height = -1;
            for(int h = 0; h < list[r][c].size(); h ++){
                if(list[r][c].get(h) == i){
                    cur_height = h;
                    break;
                }
            }

            int nr = r + dirs[d][0];
            int nc = c + dirs[d][1];
            boolean flag = true;
            if(isOutRange(nr, nc) || map[nr][nc] == 2){
                d = d == 0 ? 1 : d == 1 ? 0 : d == 2 ? 3 : 2;
                nr = r + dirs[d][0];
                nc = c + dirs[d][1];
                if(isOutRange(nr, nc) || map[nr][nc] == 2){
                    flag = false;
                }
            }

            if(flag){
                if(map[nr][nc] == 0){
                    // 흰색인 경우
                    int size = list[r][c].size() - cur_height;
                    while(size -- > 0){
                        int[] tmp_p = pos[list[r][c].get(cur_height)];
                        pos[list[r][c].get(cur_height)] = new int[] { nr, nc, tmp_p[2] };
                        list[nr][nc].add(list[r][c].get(cur_height));
                        list[r][c].remove(cur_height);
                    }
                }else if(map[nr][nc] == 1){
                    // 빨강인 경우
                    int size = list[r][c].size() - cur_height;
                    while(size -- > 0){
                        int[] tmp_p = pos[list[r][c].get(list[r][c].size() - 1)];
                        pos[list[r][c].get(list[r][c].size() - 1)] = new int[] { nr, nc, tmp_p[2] };
                        list[nr][nc].add(list[r][c].get(list[r][c].size() - 1));
                        list[r][c].remove(list[r][c].size() - 1);
                    }
                }
            }else{
                nr = r;
                nc = c;
            }

            pos[i] = new int[] { nr, nc, d };
            height = Math.max(height, list[nr][nc].size());
        }
    }

    public boolean isOutRange(int r, int c){
        return r < 0 || c < 0 || r >= N || c >= N;
    }
}

