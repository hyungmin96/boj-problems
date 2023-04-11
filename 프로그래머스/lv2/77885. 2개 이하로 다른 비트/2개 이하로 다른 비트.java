import java.util.*;
class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        for(int i = 0; i < numbers.length; i ++){
            if(numbers[i] == 0){
                answer[i] = 1;
            }else{
                long temp = 0, ret = 1;
                String s = getBinaryCode(numbers[i]);
                for(int j = s.length() - 1; j >= 0; j --){
                    if(s.charAt(j) == '1'){
                        temp += ret;
                    }
                    ret *= 2;
                }
                answer[i] = temp;
            }
        }
        return answer;
    }
    
    public String getBinaryCode(long n){
        StringBuilder sb = new StringBuilder();
        while(n > 0){
            sb.append(n % 2);
            n /= 2;
        }
        sb.reverse();
        int idx = sb.toString().length();
        for(int i = 0 ; i < sb.toString().length(); i ++){
            if(sb.toString().charAt(i) == '0'){
                idx = i;
            }
        }
        
        if(idx == sb.toString().length()){
            return "10" + sb.toString().substring(1);
        }else{
            if(idx == sb.toString().length() - 1){
                return sb.toString().substring(0, sb.toString().length() - 1) + "1";
            }else{
                String s1 = sb.toString().substring(0, idx);
                String s2 = sb.toString().substring(idx + 2);
                return s1 + "10" + s2;
            }
        }
    }
}