import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException{
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int[] map = new int[26]; // q, u, a, c, k are provided each index
    int[] cnt = new int[5];
    char[] ch = new char[] { 'q', 'u', 'a', 'c', 'k' };
    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        map['q' - 'a'] = 0;
        map['u' - 'a'] = 1;
        map['a' - 'a'] = 2;
        map['c' - 'a'] = 3;
        map['k' - 'a'] = 4;

        int answer = 0;
        for(int i = 0; i < s.length(); i ++){
            char c = s.charAt(i);
            int idx = map[c - 'a'];
            if(idx != 0 && cnt[idx - 1] <= cnt[idx]){
                System.out.println(-1);
                return;
            }

            cnt[idx] ++;
            answer = Math.max(answer, cnt[0]);

            if(c == 'k'){
                for(int j = 0; j < ch.length; j ++){
                    cnt[map[ch[j] - 'a']] --;
                    if(cnt[map[ch[j] - 'a']] < 0){
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        for(int i = 0; i < 5; i ++){
            if(cnt[i] > 0){
                System.out.println(-1);
                return;
            }
        }
        System.out.println(answer);
    }
}

