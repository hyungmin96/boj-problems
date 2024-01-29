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
    int[][] robot, condition;
    ArrayList<Integer>[][] trees, dead;


    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        robot = new int[N][N];
        condition = new int[N][N];
        trees = new ArrayList[N][N];
        dead = new ArrayList[N][N];

        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j ++){
                trees[i][j] = new ArrayList<>();
                dead[i][j] = new ArrayList<>();
                condition[i][j] = 5;
            }
        }
        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j ++){
                robot[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < M; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());

            trees[r][c].add(age);
        }

        System.out.println(solve());
    }

    public int solve(){
        for(int i = 0; i < K; i ++){
            spring();
            summer();
            fall();
            winter();
        }
        return M;
    }

    public void winter(){
        for(int r = 0; r < N; r ++){
            for(int c = 0; c < N; c ++){
                condition[r][c] += robot[r][c];
            }
        }
    }

    public void fall(){
        int[][] dirs = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
        for(int r = 0; r < N; r ++){
            for(int c = 0; c < N; c ++){
                for(int i = 0; i < trees[r][c].size(); i ++){
                    if(trees[r][c].get(i) % 5 == 0){
                        for(int d = 0; d < dirs.length; d ++){
                            int nr = r + dirs[d][0];
                            int nc = c + dirs[d][1];
                            if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                            M ++;
                            trees[nr][nc].add(1);
                        }
                    }
                }
            }
        }
    }

    public void summer(){
        for(int r = 0; r < N; r ++){
            for(int c = 0; c < N; c ++){
                if(dead[r][c].size() > 0){
                    int total = 0;
                    for(int i = 0; i < dead[r][c].size(); i ++){
                        total += dead[r][c].get(i) / 2;
                    }
                    condition[r][c] += total;
                    dead[r][c].clear();
                }
            }
        }
    }

    public void spring(){
        for(int r = 0; r < N; r ++){
            for(int c = 0; c < N; c ++){
                if(trees[r][c].size() > 0){
                    ArrayList<Integer> tmp = new ArrayList<>();
                    Collections.sort(trees[r][c]);

                    for(int i = 0; i < trees[r][c].size(); i ++){
                        if(condition[r][c] >= trees[r][c].get(i)){
                            condition[r][c] -= trees[r][c].get(i);
                            tmp.add(trees[r][c].get(i) + 1);
                        }else{
                            M --;
                            dead[r][c].add(trees[r][c].get(i));
                        }
                    }
                    trees[r][c] = new ArrayList<>(tmp);
                }
            }
        }
    }
}
