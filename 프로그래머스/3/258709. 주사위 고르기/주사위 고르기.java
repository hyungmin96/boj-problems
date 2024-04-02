import java.util.*;
class Solution {
    boolean[] v;
    int[] answer;
    int win_cnt = 0;
    public int[] solution(int[][] dice) {
        v = new boolean[dice.length];
        answer = new int[dice.length / 2];
        combi(0, 0, dice);
        return answer;
    }
    
    public void combi(int depth, int idx, int[][] dice){
        if(depth == dice.length / 2){
            get_sum(dice);
            return;
        }
        for(int i = idx; i < dice.length; i ++){
            v[i] = true;
            combi(depth + 1, i + 1, dice);
            v[i] = false;
        }
    }
    
    public void get_sum(int[][] dice){
        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();
        
        int a_idx = 0, b_idx = 0;
        int[] a_dice = new int[dice.length / 2];
        int[] b_dice = new int[dice.length / 2];
        for(int i = 0; i < dice.length; i ++){
            if(v[i]){
               a_dice[a_idx++] = i; 
            }else{
                b_dice[b_idx++] = i;
            }
        }
        
        a_dfs(0, 0, a, a_dice, dice);
        b_dfs(0, 0, b, b_dice, dice);
        
        Collections.sort(a);
        Collections.sort(b);
        
        int cnt = 0;
        for(int i = 0; i < a.size(); i ++){
            int a_cur = a.get(i);
            cnt += lower_bound(a_cur, b);
        }
        
        if(win_cnt < cnt){
            win_cnt = cnt;
            for(int j = 0; j < a_dice.length; j ++){
                answer[j] = a_dice[j] + 1;
            }
        }
    }
    
    public int lower_bound(int cur, ArrayList<Integer> b){
        int l = 0, r = b.size() - 1;
        while(l <= r){
            int mid = (r + l) / 2;
            if(b.get(mid) >= cur){
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        
        return l;
    }
    
    public void a_dfs(int depth, int cur, ArrayList<Integer> list, int[] a_dice, int[][] dice){
        if(depth == a_dice.length){
            list.add(cur);
            return;
        }
        
        for(int i = 0; i < 6; i ++){
            a_dfs(depth + 1, cur + dice[a_dice[depth]][i], list, a_dice, dice);
        }
    }
    
     public void b_dfs(int depth, int cur, ArrayList<Integer> list, int[] b_dice, int[][] dice){
        if(depth == b_dice.length){
            list.add(cur);
            return;
        }
        
        for(int i = 0; i < 6; i ++){
            b_dfs(depth + 1, cur + dice[b_dice[depth]][i], list, b_dice, dice);
        }
    }
}

// A와 B가 주사위를 가지는 경우의 수 => n! / n! * (n - r)! => 최대 252가지.
// 주사위를 5개씩 나눠 가졌을 때 나올 수 있는 경우의 수 => 15,625

// 주사위의 조합을 구하기
// 구한 주사위에서 나올 수 있는 모든 경우의 수 구하기
// 

