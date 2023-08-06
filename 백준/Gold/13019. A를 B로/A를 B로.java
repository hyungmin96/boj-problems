import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();        
        String s2 = br.readLine();        

        if(s1.equals(s2)){
            System.out.println(0);
            return;
        }

        int[] v = new int[26];
        for(int i = 0; i < s1.length(); i ++){
            v[s1.charAt(i) - 'A'] ++;
        }
        for(int i = 0; i < s2.length(); i ++){
            v[s2.charAt(i) - 'A'] --;
        }

        for(int i = 0; i < 26; i ++){
            if(v[i] != 0){
                System.out.println(-1);
                return;
            }
        }

        int cnt = 0, s2_idx = s2.length() - 1;
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        
        for(int i = c1.length - 1; i >= 0; i --){
            if(c1[i] == c2[s2_idx]){
                s2_idx --;
            }else{
                cnt ++;
            }
        }
        System.out.println(cnt);
    }
}