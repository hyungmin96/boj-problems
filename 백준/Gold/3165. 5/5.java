import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    public String s;
    public int K;

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        

        long n = Long.parseLong(st.nextToken()) + 1;
        K = Integer.parseInt(st.nextToken());
        s = String.valueOf(n);
        
        long[] arr = new long[s.length() + 1];
        for(int i = 1; i < arr.length; i ++){
            arr[i] = s.charAt(i - 1) - '0';
        }

        change(arr);
        for(int i = 0; i < K - arr.length; i ++){
            System.out.print(5);
        }
        for(int i = 0; i < arr.length; i ++){
            if(i == 0 && arr[0] == 0) continue;
            System.out.print(arr[i]);
        }
    }

    public void change(long[] arr){
        for(int i = arr.length - 1; i >= 0; i --){
            if(count(arr) >= K) break;
            if(arr[i] > 5){
                arr[i] = 0;
                arr[i - 1] ++;
                int j = i - 1;
                while(j > 0 && arr[j] >= 10){
                    arr[j] %= 10;
                    arr[j - 1] ++;
                    j --;
                }
            }
            if(count(arr) >= K) break;
            arr[i] = 5;
        }
    }

    public int count(long[] arr){
        int cnt = 0;
        for(int k = 0; k < arr.length; k ++){
            if(arr[k] == 5){
                cnt ++;
            }
        }
        return cnt;
    }
}