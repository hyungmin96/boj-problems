import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        boolean[] isEmpty = new boolean[n + 1];
        for(int i = 0; i < m; i ++){
            isEmpty[Integer.parseInt(br.readLine())] = true;
        }

        int max = 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = (isEmpty[1]) ? 0 : 1;
        for(int i = 2; i <= n; i ++){
            if(isEmpty[i])
                dp[i] = 0;
            else{
                int temp = 0;
                for(int j = i - 2; j >= 0; j --){
                    if(!isEmpty[j]) {
                        temp = dp[j];
                        break;
                    }
                }
                dp[i] = dp[i - 1] + temp;
                max = Math.max(dp[i], max);
            }
        }

        System.out.println(max);
    }
}