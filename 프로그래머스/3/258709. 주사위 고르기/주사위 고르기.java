import java.util.*;
class Solution {
    
    int[] answer;
    int cur_win = -987654321;
    boolean[] v;
    ArrayList<int[]> a_dice = new ArrayList<>();
    ArrayList<int[]> b_dice = new ArrayList<>();
    
    public int[] solution(int[][] dice) {
        v = new boolean[dice.length];
        answer = new int[dice.length / 2];
        dfs(0, 0, dice);
        
        for(int i = 0; i < a_dice.size(); i ++){
            int[] aa = a_dice.get(i);
            int[] bb = b_dice.get(i);
            ArrayList<Integer> a_comb = getAllNums(0, 0, aa, new ArrayList<>(), dice);
            ArrayList<Integer> b_comb = getAllNums(0, 0, bb, new ArrayList<>(), dice);    
            
            Collections.sort(a_comb);
            Collections.sort(b_comb);
            
            int win_cnt = 0;
            for(int a_num : a_comb){
                win_cnt += lowerBound(a_num, b_comb);
            }
            
            if(cur_win < win_cnt){
                cur_win = win_cnt;
                for(int k = 0; k < dice.length / 2; k ++){
                    answer[k] = aa[k] + 1;
                }
            }
        }
        
        return answer;
    }
    
    public int lowerBound(int val, ArrayList<Integer> list){
        int l = 0, r = list.size() - 1;
        while(l <= r){
            int mid = (r + l) / 2;
            if(list.get(mid) >= val){
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        return l;
    }
    
    public ArrayList<Integer> getAllNums(int depth, int cur, int[] arr, ArrayList<Integer> list, int[][] dice){
        if(depth == arr.length){
            list.add(cur);
            return list;
        }
        
        for(int i = 0; i < 6; i ++){
            getAllNums(depth + 1, cur + dice[arr[depth]][i], arr, list, dice);
        }
        return list;
    }

    public void dfs(int depth, int idx, int[][] dice) {
        if (depth == dice.length / 2) {
            int a_idx = 0, b_idx = 0;
            int[] a_arr = new int[dice.length / 2];
            int[] b_arr = new int[dice.length / 2];
            for(int i = 0; i < v.length; i ++){
                if(v[i]){
                    a_arr[a_idx++] = i;
                }else{
                    b_arr[b_idx++] = i;
                }
            }
            a_dice.add(a_arr);
            b_dice.add(b_arr);
            return;
        }

        for (int i = idx; i < dice.length; i++) {
            v[i] = true;
            dfs(depth + 1, i + 1, dice);
            v[i] = false;
        }
    }
}