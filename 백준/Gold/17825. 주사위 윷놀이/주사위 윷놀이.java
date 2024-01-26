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
    int[] dice = new int[10];
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

        dfs(0, new int[10]);
        System.out.println(answer);
    }

    public void dfs(int depth, int[] arr){
        if(depth == 10){
            int cnt = simulation(arr);
            answer = Math.max(answer, cnt);
            return;
        }

        for(int i = 0; i < 4; i ++){
            arr[depth] = i;
            dfs(depth + 1, arr);
        }
    }

    public int simulation(int[] arr){
        int ret = 0;
        int[] pos = new int[4];
        int[] level = new int[4];
        boolean[][] v = new boolean[4][30];
        boolean[] isEnd = new boolean[4];
        for(int i = 0; i < arr.length; i ++){
            int idx = arr[i]; // 현재 선택한 말의 idx
            int nl = level[idx];
            int move_cnt = dice[i];
            int next = pos[idx] + move_cnt;

            if(isEnd[idx]) continue;
            if(level[idx] == 0){
                if(next == 5){
                    nl = 1;
                }else if(next == 10){
                    nl = 2;
                }else if(next == 15){
                    nl = 3;
                }
            }
            if(!isImpossible(idx, nl, next, v, level, pos)){
                v[level[idx]][pos[idx]] = false;
                if(next < map[nl].length){
                    v[nl][next] = true;
                    level[idx] = nl;
                    pos[idx] = next;
                    ret += map[nl][next];
                }else{
                    isEnd[idx] = true;
                }
            }else{
                return -1;
            }
        }
        return ret;
    }

    public boolean isImpossible(int idx, int lv, int pos, boolean[][] v, int[] level, int[] pos_arr){
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
