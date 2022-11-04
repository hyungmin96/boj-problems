import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[][] dirs = {{ -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 }};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        int[][] add = new int[N][N];
        ArrayList<Integer>[][] trees = new ArrayList[N][N];
        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j ++){
                trees[i][j] = new ArrayList<>();
                map[i][j] = 5;
                add[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = M;
        for(int i = 0; i < M; i ++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());

            trees[x][y].add(z);
        }

        System.out.println(solution(cnt, map, add, trees));
    }

    public static int solution(int cnt, int[][] map, int[][] add, ArrayList<Integer>[][] trees){
        for(int i = 1; i <= K; i ++){
            ArrayList<int[]> dead = new ArrayList<>();
            for(int r = 0; r < N; r ++){
                for(int c = 0; c < N; c ++){
                    if(trees[r][c].size() > 0){
                        ArrayList<Integer> temp = new ArrayList<>();
                        Collections.sort(trees[r][c]);
                        for(int age : trees[r][c]){
                            if(map[r][c] >= age){
                                temp.add(age + 1);
                                map[r][c] -= age;
                            }else{
                                cnt --;
                                dead.add(new int[] { r, c, age });
                            }
                        }
                        trees[r][c] = temp;
                    }
                }
            }

            for(int[] d : dead){
                int r = d[0];
                int c = d[1];
                map[r][c] += d[2] / 2;
            }
            dead.clear();

            for(int r = 0; r < N; r ++){
                for(int c = 0; c < N; c ++){
                    if(trees[r][c].size() > 0){
                        for(int k = 0; k < trees[r][c].size(); k ++){
                            if(trees[r][c].get(k) % 5 == 0){
                                for(int d = 0; d < dirs.length; d ++){
                                    int nr = r + dirs[d][0];
                                    int nc = c + dirs[d][1];
                                    if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                                    trees[nr][nc].add(1);
                                    cnt ++;
                                }
                            }
                        }
                    }
                }
            }

            for(int r = 0; r < N; r ++){
                for(int c = 0; c < N; c ++){
                    map[r][c] += add[r][c];
                }
            }
        }

        return cnt;
    }
}
