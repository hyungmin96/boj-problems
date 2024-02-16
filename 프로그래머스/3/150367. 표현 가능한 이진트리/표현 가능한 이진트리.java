import java.util.*;
class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for(int i = 0; i < numbers.length; i ++){
            long n = numbers[i];
            String s = getBinaryCode(n);
            
            if(dfs(0, 0, s.length(), s)){
                answer[i] = 1;
            }else{
                answer[i] = 0;
            }
            System.out.println();
        }
        return answer;
    }
    
    public boolean dfs(int depth, int l, int r, String s){
        if(l == r){
            return true;
        }
        int mid = (r + l) / 2;
        
        int nl = s.charAt((mid + l) / 2) - '0';
        int nr = s.charAt((r + mid) / 2) - '0';
        
        if(s.charAt(mid) - '0' == 0){
            if(nl== 1 || nr == 1){
                return false;
            }
        }
        return dfs(depth + 1, l, mid, s) && dfs(depth + 1, mid + 1, r, s);
    }
    
    public String getBinaryCode(long n){
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        while(n > 0){
            if(n % 2 == 0){
                sb.append("0");
            }else{
                sb.append("1");
            }
            n /= 2;
            cnt ++;
        }
        
        int total = 1, mul = 0;
        while(total < cnt){
            total = (int)Math.pow(2, mul++) - 1;
        }
        
        int len = sb.toString().length();
        for(int i = 0; i < total - len; i ++){
            sb.append("0");
        }
        
        return sb.reverse().toString();
    }
}