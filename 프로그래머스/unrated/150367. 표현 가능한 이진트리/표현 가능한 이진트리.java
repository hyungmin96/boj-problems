class Solution {
    static int node_cnt = 0;
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for(int i = 0; i < numbers.length; i ++){
            String str = getBinary(numbers[i]);
            
            int cnt = 0;
            long total = 0;
            while(total < str.length()){
                total += (int)Math.pow(2, cnt ++);
            }
            
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < total - str.length(); j ++){
                sb.append("0");
            }
            
            str = sb.toString() + str;
            
            node_cnt = 0;
            dfs(0, cnt, 0, str.length(), str);
            
            if(node_cnt != (int)Math.pow(2, cnt) - 1){
                answer[i] = 0;
            }else{
                answer[i] = 1;
            }
        }
        return answer;
    }
    
    public static void dfs(int depth, int n, int l, int r, String str){
        
        if(depth == n){
            return;
        }
        
        node_cnt ++;
        int idx = (l + r) / 2;
        char c = str.charAt(idx);
        
        if(c == '0'){
            if(depth + 1 < n){
                if(str.charAt((l + idx) / 2) == '1' || str.charAt((r + idx) / 2) == '1'){
                    return;
                }
            }
        }
        dfs(depth + 1, n, l, idx, str);
        dfs(depth + 1, n, idx, r, str);
    }
    
    public String getBinary(long num){
        StringBuilder sb = new StringBuilder();
        while(num > 0){
            if(num % 2 == 0) sb.append("0");
            else sb.append("1");
            
            num /= 2;
        }
        
        return sb.reverse().toString();
    }
}