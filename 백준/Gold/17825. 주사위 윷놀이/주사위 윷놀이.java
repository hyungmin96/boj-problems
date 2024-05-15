import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int[] arr = new int[10];
    
    int[][] road = {{ 0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40 },
                    { 0,2,4,6,8,10,13,16,19,25,30,35,40 },
                    { 0,2,4,6,8,10,12,14,16,18,20,22,24,25,30,35,40 },
                    { 0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,28,27,26,25,30,35,40 }};
    
    boolean[][] v = new boolean[4][30];

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < 10; i ++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solve();
    }

    public void solve(){
        System.out.println(dfs(0, new int[4], new int[4]));
    }

    public int dfs(int depth, int[] road_idx, int[] pos){
        if(depth == 10){
            return 0;
        }

        int ret = 0;
        for(int i = 0; i < 4; i ++){
            int pre = pos[i];
            
            if(pos[i] >= road[road_idx[i]].length){
                continue;
            }
            pos[i] += arr[depth];
            int tmp_road_idx = road_idx[i];
            if(road_idx[i] == 0 && pos[i] == 5){
                // 10번 발판을 밟는 경우
                road_idx[i] = 1;
            }else if(road_idx[i] == 0 && pos[i] == 10){
                // 20번 발판을 밟는 경우
                road_idx[i] = 2;
            }else if(road_idx[i] == 0 && pos[i] == 15){
                // 30번 발판을 밟는 경우
                road_idx[i] = 3;
            }

            if(!is_invalid(i, road_idx, pos)){
                v[tmp_road_idx][pre] = false;
                v[road_idx[i]][pos[i]] = true;
                int s = pos[i] < road[road_idx[i]].length ? road[road_idx[i]][pos[i]] : 0;
                ret = Math.max(ret, dfs(depth + 1, road_idx, pos) + s);
                v[tmp_road_idx][pre] = true;
                v[road_idx[i]][pos[i]] = false;
            }

            pos[i] = pre;
            road_idx[i] = tmp_road_idx;
        }
        return ret;
    }

    public boolean is_invalid(int idx, int[] road_idx, int[] positions){
        int pos = positions[idx];
        int lv = road_idx[idx];
        if(pos >= road[lv].length){
            return false;
        }
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
            if(road_idx[i] == lv && positions[i] == pos){
                return true;
            }
        }

        return false;
    }
}