import java.util.*;
class Solution {
    public String solution(long n, String[] bans) {
        Arrays.sort(bans, new Comparator<String>(){
            @Override
            public int compare(String s1, String s2){
                if(s1.length() == s2.length()){
                    return s1.compareTo(s2);
                }
                return s1.length() - s2.length();
            }
        });
        
        for(String s : bans){
            long numsOrder = getOrder(s);
            if(numsOrder <= n){
                n ++;
            }
        }
        
        return getAnswer(n);
    }
    
     public String getAnswer(long n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            long remainder = n % 26;
            if (remainder == 0) {
                sb.append('z');
                n = (n / 26) - 1;
            } else { 
                sb.append((char)('a' + remainder - 1));
                n = n / 26;
            }
        }
        return sb.reverse().toString();
    }
    
    public long getOrder(String s){
        long tmp = 0;
        for(int i = 0; i < s.length(); i ++){
            tmp += (s.charAt(i) - 'a' + 1) * Math.pow(26, s.length() - i - 1);
        }
        return tmp;
    }
}