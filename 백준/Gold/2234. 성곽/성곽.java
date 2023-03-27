import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static int[][] dir = {{ 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 }};
    static int max_num = -1, max_size = -1, max_wall_size = -1;
    static HashMap<Integer, boolean[]> hash = new HashMap<>();
    static HashMap<Integer, Integer> hash_size = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); // 7
        M = Integer.parseInt(st.nextToken()); // 4
        map = new int[M][N];

        for(int r = 0; r < M; r ++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int c = 0; c < N; c ++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        hashInit();

        int idx = 1;
        int[][] group = new int[M][N];
        boolean[][] check = new boolean[M][N];
        for(int r = 0; r < M; r ++){
            for(int c = 0; c < N; c ++){
                if(!check[r][c]){
                    int temp_size = dfs(r, c, idx, check, group);
                    hash_size.put(idx ++, temp_size);
                    max_size = Math.max(max_size, temp_size);
                }
            }
        }

        max_wall_size = max_size;
        for(int r = 0; r < M; r ++){
            for(int c = 0; c < N; c ++){
                int cur_idx = group[r][c];
                if(r + 1 < M && cur_idx != group[r + 1][c]){
                    max_wall_size = Math.max(max_wall_size, hash_size.get(cur_idx) + hash_size.get(group[r + 1][c]));
                }
                if(c + 1 < N && cur_idx != group[r][c + 1]){
                    max_wall_size = Math.max(max_wall_size, hash_size.get(cur_idx) + hash_size.get(group[r][c + 1]));
                }
            }
        }

        max_num = hash_size.size();

        StringBuilder sb = new StringBuilder();
        sb.append(max_num + "\n" + max_size + "\n" + max_wall_size);
        System.out.println(sb.toString());
    }

    public static int dfs(int r, int c, int idx, boolean[][] check, int[][] group){
        if(check[r][c]) return 1;
        group[r][c] = idx;
        check[r][c] = true;
        boolean[] is_wall = hash.get(map[r][c]);
        int temp = 1;
        for(int d = 0; d < 4; d ++){
            if(!is_wall[d]){
                int nr = r + dir[d][0];
                int nc = c + dir[d][1];

                if(!isRange(nr, nc) || check[nr][nc]) continue;
                if(!hash.get(map[nr][nc])[(d + 2) % 4]){
                    temp += dfs(nr, nc, idx, check, group);
                }
            }
        }
        return temp;
    }

    public static boolean isRange(int r, int c){
        return !(r < 0 || c < 0 || r >= M || c >= N);
    }

    public static void hashInit(){
        hash.put(0, new boolean[] { false, false, false, false });
        hash.put(1, new boolean[] { true, false, false, false });
        hash.put(2, new boolean[] { false, true, false, false });
        hash.put(3, new boolean[] { true, true, false, false });
        hash.put(4, new boolean[] { false, false, true, false });
        hash.put(5, new boolean[] { true, false, true, false });
        hash.put(6, new boolean[] { false, true, true, false });
        hash.put(7, new boolean[] { true, true, true, false });
        hash.put(8, new boolean[] { false, false, false, true });
        hash.put(9, new boolean[] { true, false, false, true });
        hash.put(10, new boolean[] { false, true, false, true });
        hash.put(11, new boolean[] { true, true, false, true });
        hash.put(12, new boolean[] { false, false, true, true });
        hash.put(13, new boolean[] { true, false, true, true });
        hash.put(14, new boolean[] { false, true, true, true });
        hash.put(15, new boolean[] { true, true, true, true });
    }
}