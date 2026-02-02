class Solution {
    public int solution(String s) {
        int ans = s.length();
        for (int i = 1; i <= s.length() / 2 + 1; i++) {
            int idx = 0;
            StringBuilder sb = new StringBuilder();
            while (idx < s.length()) {
                int cnt = 0, j = idx;
                if(idx + i < s.length()){
                    String cur = s.substring(idx, idx + i);
                    while(j < s.length()){
                        String next = s.substring(j, Math.min(j+i, s.length()));
                        if (cur.equals(next)){
                            cnt ++;
                        }else{
                            break;
                        }
                        j += i;
                    }
                    if(cnt > 1){
                        sb.append(cnt);
                    }
                    sb.append(cur);
                    idx = j;
                }else{
                    sb.append(s.substring(idx));
                    break;
                }
            }
            ans = Math.min(ans, sb.toString().length());
        }
        return ans;
    }
}