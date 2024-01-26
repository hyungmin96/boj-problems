import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int answer = 0;
    int[] pos_arr = new int[4];
    int[] level = new int[4];
    int[] dice = new int[10];
    boolean[][] v = new boolean[4][30];
    boolean[] isEnd = new boolean[4];

    int[][] map = {
        {0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40},
        {0,2,4,6,8,10,13,16,19,25,30,35,40},
        {0,2,4,6,8,10,12,14,16,18,20,22,24,25,30,35,40},
        {0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,28,27,26,25,30,35,40}
    };

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < 10; i ++){
            dice[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(dfs(0, 0));
    }

    public int dfs(int depth, int score){
        if(depth == 10){
            return score;
        }

        int ret = 0;
        for(int i = 0; i < 4; i ++){
            int nl = level[i];
            int move_cnt = dice[depth];
            int pl = level[i];
            int pp = pos_arr[i];
            
            if(pos_arr[i] >= map[nl].length){
                continue;
            }

            pos_arr[i] += move_cnt;
            if(level[i] == 0){
                if(pos_arr[i] == 5){
                    nl = 1;
                }else if(pos_arr[i] == 10){
                    nl = 2;
                }else if(pos_arr[i] == 15){
                    nl = 3;
                }
            }
            if(!isImpossible(i, nl, pos_arr[i])){
                level[i] = nl;
                v[pl][pp] = false;
                v[nl][pos_arr[i]] = true;
                
                int num = pos_arr[i] < map[nl].length ? map[nl][pos_arr[i]] : 0;
                ret = Math.max(ret, dfs(depth + 1, score + num));
                
                v[nl][pos_arr[i]] = false;
                v[pl][pp] = true;
                level[i] = pl;
            }
            pos_arr[i] = pp;
        }
        return ret;
    }

    public boolean isImpossible(int idx, int lv, int pos){
        if(pos >= map[lv].length) return false;

        if((lv == 1 && pos == 9) || (lv == 2 && pos == 13) || (lv == 3 && pos == 19)){
            //25
            return v[1][9] || v[2][13] || v[3][19];
        }else if((lv == 1 && pos == 10) || (lv == 2 && pos == 14) || (lv == 3 && pos == 20)){
            // 30
            return v[1][10] || v[2][14] || v[3][20];
        }else if((lv == 1 && pos == 11) || (lv == 2 && pos == 15) || (lv == 3 && pos == 21)){
            // 35
            return v[1][11] || v[2][15] || v[3][21];
        }else if((lv == 0 && pos == 20) || (lv == 1 && pos == 12) || (lv == 2 && pos == 16) || (lv == 3 && pos == 22)){
            // 40
            return v[0][20] || v[1][12] || v[2][16] || v[3][22];
        }

        for(int i = 0; i < 4; i ++){
            if(i == idx) continue;
            if(level[i] == lv && pos_arr[i] == pos){
                return true;
            }
        }
        return false;
    }
}
