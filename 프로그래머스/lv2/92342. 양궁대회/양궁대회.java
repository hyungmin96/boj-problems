import java.util.*;
class Solution {
    
    int diff = 0;
    ArrayList<int[]> list = new ArrayList<>();
    
    public int[] solution(int n, int[] info) {
        int[] answer = new int[11];
        dfs(n, 0, 0, new int[info.length], info);
        if(list.size() > 0){
            ArrayList<Integer> sub = new ArrayList<>();
            while(list.size() != 1){
                for(int k = 10; k >= 0; k --){
                    int num = 1;
                    for(int j = 0; j < list.size(); j ++){
                        if(list.get(j)[k] >= num){
                            if(list.get(j)[k] > num){
                                sub.clear();
                            }
                            num = list.get(j)[k];
                            sub.add(j);
                        }
                    }
                    if(sub.size() > 0){
                        ArrayList<int[]> temp = new ArrayList<>();
                        for(int s : sub){
                            temp.add(list.get(s).clone());
                        }
                        list.clear();
                        list.addAll(temp);
                    }
                    if(sub.size() == 1) break; else sub.clear();
                }
            }
            for(int j = 0; j < 11; j ++){
                answer[j] = list.get(0)[j];
            }
        }else answer = new int[] { -1 };
        return answer;
    }
    
    public void dfs(int n, int depth, int idx, int[] arr, int[] info){
        if(depth == n){
            int apeach = 0;
            int rion = 0;
            
            for(int i = 0; i < info.length; i ++){
                int score = 10 - i;
                if(info[i] > 0 && info[i] >= arr[i]){
                    apeach += score;
                }else if(arr[i] > info[i] && arr[i] > 0){
                    rion += score;
                }
            }
            
            if(rion - apeach > 0){
                if(diff <= rion - apeach){
                    if(diff < rion - apeach){
                        diff = rion - apeach;
                        list.clear();
                    }
                    list.add(arr.clone());
                }
            }
            return;
        }
        
        for(int i = idx; i <= 10; i ++){
            arr[i] ++;
            dfs(n, depth + 1, i, arr, info);
            arr[i] --;
        }
    }
}