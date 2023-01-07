class Solution {
    
    int sub = 0;
    int price = 0;
    
    public int[] solution(int[][] users, int[] emoticons) {
        dfs(0, users, emoticons, new int[emoticons.length]);
        return new int[] { sub / 2, price };
    }
    
    public void dfs(int depth, int[][] users, int[] emoticons, int[] salse){
        if(depth == salse.length){
            int total_price = 0;
            int total_sub = 0;
            for(int u = 0; u < users.length; u ++){
                boolean isSub = false;
                int temp_price = 0;
                for(int i = 0; i < emoticons.length; i ++){
                    if(salse[i] * 10 >= users[u][0]){
                        temp_price += (emoticons[i] * (1 - (salse[i] * 0.1)));
                        if(temp_price >= users[u][1]){
                            isSub = true;
                            break;
                        }
                    }
                } 
                
                if(isSub){
                    total_sub ++;
                    temp_price = 0;
                    total_sub ++;
                }else{
                    total_price += temp_price;
                }
            }
            
            if(sub <= total_sub){
                if(sub < total_sub){
                    sub = total_sub;
                    price = total_price;
                }else if(sub == total_sub){
                    price = Math.max(price, total_price);
                }
            }
            return;
        }
        for(int i = 1; i <= 4; i ++){
            salse[depth] = i;
            dfs(depth + 1, users, emoticons, salse);
        }
    }
}