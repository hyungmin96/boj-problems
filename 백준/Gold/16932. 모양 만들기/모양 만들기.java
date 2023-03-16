import java.io.*;
import java.util.*;

public class Main {

    static int n, m, cnt, idx = 1;
    static int[][] map, index;
    static boolean[][] check;
    static int[][] dirs = {{ -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }};
    static HashMap<Integer, Integer> hash = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        index = new int[n][m];
        check = new boolean[n][m];

        ArrayList<int[]> pos = new ArrayList<>();
        for(int i = 0; i < n; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < m; j ++){
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
                if(num == 1){
                    pos.add(new int[] { i, j });
                }
            }
        }

        for(int[] p : pos){
            if(!check[p[0]][p[1]]){
                cnt = 0;
                check[p[0]][p[1]] = true;
                dfs(p[0], p[1]);
                hash.put(idx ++, cnt);
            }
        }

        int answer = 0;
        for(int r = 0; r < n; r ++){
            for(int c = 0; c < m; c ++){
                if(map[r][c] == 0){
                    int temp = 1;
                    HashSet<Integer> visited = new HashSet<>();
                    for(int[] d : dirs){
                        if(isRange(r + d[0], c + d[1]) && map[r + d[0]][c + d[1]] == 1){
                            if(!visited.contains(index[r + d[0]][c + d[1]])){
                                visited.add(index[r + d[0]][c + d[1]]);
                                temp += hash.get(index[r + d[0]][c + d[1]]);
                            }
                        }
                    }
                    answer = Math.max(answer, temp);
                }
            }
        }

        System.out.println(answer);
    }

    public static void dfs(int r, int c){
        cnt ++;
        index[r][c] = idx;
        for(int[] d : dirs){
            int nr = r + d[0];
            int nc = c + d[1];
            if(!isRange(nr, nc) || check[nr][nc] || map[nr][nc] == 0) continue;
            check[nr][nc] = true;
            dfs(nr, nc);
        }
    }

    public static boolean isRange(int r, int c){
        return !(r < 0 || c < 0 || r >= n || c >= m);
    }
}