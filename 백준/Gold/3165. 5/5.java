import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int K;
    int[] arr = new int[16];
    StringBuilder sb = new StringBuilder();

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        Long N = Long.parseLong(st.nextToken()) + 1;
        K = Integer.parseInt(st.nextToken());

        int idx = 15;
        for(int i = (N + "").length() - 1; i >= 0; i --){
            arr[idx --] = (N + "").charAt(i) - '0';
        }

        if(count() >= K){
            System.out.println(N);
            return;
        }

        idx = 15;
        int cnt = 0;
        while(cnt < K){
            cnt = count();
            if(cnt >= K){
                break;
            }

            if(arr[idx] > 5){
                arr[idx - 1] ++;
            }
            for(int i = idx; i > 0; i --){
                if(arr[i] >= 10){
                    arr[i - 1] ++;
                    arr[i] %= 10;
                }
            }
            arr[idx] = 0;
            if(count() >= K){
                break;
            }
            arr[idx --] = 5;
        }

        int j = 0;
        for(j = 0; j < arr.length; j ++){
            if(arr[j] != 0){
                break;
            }
        }
        for(int i = arr.length - 1; i >= j; i --){
            sb.append(arr[i]);
        }

        System.out.println(sb.reverse().toString());
    }

    public int count(){
        int cnt = 0;
        for(int i = 0; i < arr.length; i ++){
            if(arr[i] == 5){
                cnt ++;
            }
        }
        return cnt;
    }
}
