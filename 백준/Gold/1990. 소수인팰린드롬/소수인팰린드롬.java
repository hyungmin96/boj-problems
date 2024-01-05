import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {
    
    int a, b;

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        
        for(int num : getPrimeNums(b)){
            if(num < a) continue;
            if(isPalindrome(num + "")){
                sb.append(num + "\n");
            }
        }
        sb.append("-1");

        System.out.println(sb.toString());
    }

    public boolean isPalindrome(String n){
        for(int i = 0; i <= n.length() / 2; i ++){
            if(n.charAt(i) != n.charAt(n.length() - 1 - i)) return false;
        }
        return true;
    }

    public ArrayList<Integer> getPrimeNums(int n){
        boolean[] isNotPrimeNums = new boolean[100000010];
        ArrayList<Integer> nums = new ArrayList<>();

        nums.add(2);
        for(int i = 3; i <= Math.sqrt(n); i += 2){
            if(isNotPrimeNums[i]) continue;
            int base = i + i;
            while(base <= n){
                isNotPrimeNums[base] = true;
                base += i;
            }
        }

        for(int i = 3; i <= n; i += 2){
            if(!isNotPrimeNums[i]){
                nums.add(i);
            }
        }

        return nums;
    }
}