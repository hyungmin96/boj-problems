import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    public class Shark {
        int idx, r, c, s, d, z;

        public Shark(int idx, int r, int c, int s, int d, int z) {
            this.idx = idx;
            this.r = r;
            this.c = c;
            this.s = s; // 속도
            this.d = d; // 방향
            this.z = z; // 크기
        }
    }

    public int R, C, M;
    public int[][] dirs = {
            { -1, 0 },
            { 1, 0 },
            { 0, 1 },
            { 0, -1 }
    };
    public ArrayList<Integer>[][] map;
    public HashMap<Integer, Shark> sharks = new HashMap<>();

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new ArrayList[R][C];

        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++)
                map[i][j] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());

            map[r][c].add(z);
            sharks.put(z, new Shark(z, r, c, s, d, z));
        }

        solve();
    }

    public void move(Shark s) {
        int tmp_s = s.s;
        if(s.d == 0 || s.d == 1){
            tmp_s = s.s % (2 * (R - 1));
        }else{
            tmp_s = s.s % (2 * (C - 1));
        }

        if(tmp_s == 0){
            s.d = (s.d == 0) ? 1 : (s.d == 1) ? 0 : (s.d == 2) ? 3 : 2;
        }

        while(tmp_s -- > 0){
            int nr = s.r + dirs[s.d][0];
            int nc = s.c + dirs[s.d][1];

            if(nr < 0 || nc < 0 || nr >= R || nc >= C){
                s.d = (s.d == 0) ? 1 : (s.d == 1) ? 0 : (s.d == 2) ? 3 : 2;
                
                nr = s.r + dirs[s.d][0];
                nc = s.c + dirs[s.d][1];
            }

            s.r = nr;
            s.c = nc;
        }
    }

    public void solve(){
        long answer = 0;
        for (int i = 0; i < C; i++) {
            for (int r = 0; r < R; r++) {
                if (map[r][i].size() > 0) {
                    Shark s = sharks.get(map[r][i].get(0));
                    answer += s.z;
                    sharks.remove(map[r][i].get(0));
                    map[r][i].clear();
                    break;
                }
            }

            for (int k = 0; k < R; k ++)
                for (int j = 0; j < C; j++)
                    map[k][j].clear();

            for (int k : sharks.keySet()) {
                Shark s = sharks.get(k);
                move(s);
                map[s.r][s.c].add(k);
            }

            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (map[r][c].size() > 1) {
                        Collections.sort(map[r][c]);

                        int size = map[r][c].get(map[r][c].size() - 1);
                        for (int k = 0; k < map[r][c].size() - 1; k ++)
                            if(sharks.containsKey(map[r][c].get(k)))
                                sharks.remove(map[r][c].get(k));

                        map[r][c].clear();
                        map[r][c].add(size);
                    }
                }
            }
        }
        System.out.println(answer);
    }
}