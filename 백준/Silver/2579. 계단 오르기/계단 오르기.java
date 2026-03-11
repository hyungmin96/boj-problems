import java.io.*;
import java.util.*;

public class Main {

    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        dp = new int[n + 1];
        int[] arr = new int[n + 1];
        for(int i = 1; i <= n; i ++){
            int num = Integer.parseInt(br.readLine());
            arr[i] = num;
        }

        if(n == 1){
            System.out.println(arr[1]);
        }else if(n == 2){
            System.out.println(arr[1] + arr[2]);
        }
        
        if(n < 3) return;
        dp[1] = arr[1];
        dp[2] = arr[1] + arr[2];
        dp[3] = Math.max(arr[2], arr[1]) + arr[3];
        for(int i = 4; i <= n; i ++){
            dp[i] = Math.max(dp[i - 3] + arr[i - 1], dp[i - 2]) + arr[i];
        }
        System.out.println(dp[n]);
    }
}
