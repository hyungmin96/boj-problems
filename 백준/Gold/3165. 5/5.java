import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException{
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

   
    long N;
    int K;

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Long.parseLong(st.nextToken()) + 1;
        K = Integer.parseInt(st.nextToken());
      
        int[] arr = initArr();
        if(getCount(arr)){
            System.out.println(getString(arr));
            return;
        }else{
            for(int i = arr.length - 1; i >= 0; i --){
                if(arr[i] > 5){
                    arr[i - 1] ++;
                    for(int j = i - 1; j > 0; j --){
                        if(arr[j] >= 10){
                            arr[j] %= 10;
                            arr[j - 1] ++;
                        }else{
                            break;
                        }
                    }
                }
                arr[i] = 0;
                if(getCount(arr)){
                    System.out.println(getString(arr));
                    return;
                }
                arr[i] = 5;
                if(getCount(arr)){
                    System.out.println(getString(arr));
                    return;
                }
            }
        }
    }

    public String getString(int[] arr){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < arr.length; i ++){
            if(i == 0 && arr[0] == 0){
                continue;
            }else{
                sb.append(arr[i]);
            }
        }

        return sb.toString();
    }
    public boolean getCount(int[] arr){
        int cnt = 0;
        for(int i = 0; i < arr.length; i ++){
            if(arr[i] == 5){
                cnt ++;
            }
        }
        return cnt >= K;
    }

    public int[] initArr(){
        long tmp = N;
        int len = 0;
        while(tmp > 0){
            tmp /= 10;
            len ++;
        }

        tmp = N;
        len = Math.max(K, len);
        int[] arr = new int[len + 1];
        for(int i = len; i >= 0 && tmp > 0; i --){
            arr[i] = (int)(tmp % 10);
            tmp /= 10;
        }

        return arr;
    }
}

