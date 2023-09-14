import java.util.*;
class Solution {
    
    int answer_score = 0;
    int[] answer = new int[] { -1 };
    public int[] solution(int n, int[] info) {
        dfs(n, 0, 0, new int[11], info);
        return answer;
    }
    
    public void dfs(int n, int depth, int idx, int[] arr, int[] info){
        if(depth == n){
            int appeach_score = 0, rion_score = 0;
            for(int i = 0; i < info.length; i ++){
                int appeach_cnt = info[i];
                int rion_cnt = arr[i];
                
                if(info[i] == 0 && arr[i] == 0) continue;
                if(appeach_cnt >= rion_cnt){
                    appeach_score += (10 - i);
                }else{
                    rion_score += (10 - i);
                }
            }
            
            int diff = rion_score - appeach_score;
            if(answer_score <= diff && diff > 0){
                if(answer[0] == -1 || diff > answer_score){
                    answer = copy(arr);
                }else{
                    for(int i = 10; i >= 0; i --){
                        if(arr[i] > answer[i]){
                            answer = copy(arr);
                            break;
                        }else if(answer[i] > arr[i]){ 
                            break;
                        }
                    }
                }
                answer_score = diff;
            }
            return;
        }
        
        for(int i = idx; i < 11; i ++){
            arr[i] ++;
            dfs(n, depth + 1, i, arr, info);
            arr[i] --;
        }
    }
    
    public int[] copy(int[] arr){
        int[] tmp = new int[11];
        for(int i = 0; i < arr.length; i ++) tmp[i] = arr[i];
        return tmp;
    }
}