class Solution {
    public int solution(String s) {
        int answer = s.length();
        for(int i = 1; i <= s.length() / 2; i ++){
            answer = Math.min(answer, dfs(0, i, s).length());
        }
        return answer;
    }
    
    public String dfs(int idx, int len, String s){
        if(idx >= s.length()){
            return "";
        }
        
        int cnt = 0, i = idx;
        String prefix = "";
        if(idx + len < s.length()){
            prefix = s.substring(idx, idx + len);
        }else{
            prefix = s.substring(idx);
        }
        
        StringBuilder sb = new StringBuilder();
        for(i = idx; i < s.length(); i += len){
            String cur = "";
            if(i + len < s.length()){
                cur = s.substring(i, i + len);
            }else{
                cur = s.substring(i);
            }
            
            if(prefix.equals(cur)){
                cnt ++;
            }else{
                break;
            }
        }
        
        if(cnt == 1){
            sb.append(prefix);
        }else{
            sb.append(cnt + prefix);   
        }
        
        return sb.toString() + dfs(i, len, s);
    }
}