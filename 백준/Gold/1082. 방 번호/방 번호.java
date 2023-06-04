import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] price;
    static String[] dp = new String[51];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        price = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i ++){
            price[i] = Integer.parseInt(st.nextToken());
        }
        int asset = Integer.parseInt(br.readLine());
        solution(asset);
    }

    public static void solution(int a){
        Arrays.fill(dp, "");
        for(int i = 1; i <= a; i ++){
            for(int j = 0; j < N; j ++){
                if(i - price[j] < 0) continue;
                dp[i] = j + "";
            }
        }

        for(int i = 1; i <= a; i ++){
            for(int j = N - 1; j >= 0; j --){
                int p = price[j];
                if(i - p < 0) continue;

                String s1 = dp[i - p] + dp[p];
                String s2 = dp[p] + dp[i - p];

                dp[i] = compareStr(dp[i], compareStr(s1, s2));
            }
        }
        
        System.out.println(dp[a]);
    }

    public static String compareStr(String s1, String s2){
        while(s1.length() > 1 && s1.charAt(0) == '0')
            s1 = s1.substring(1);

        while(s2.length() > 1 && s2.charAt(0) == '0')
            s2 = s2.substring(1);

        if(s1.length() > s2.length()){
            return s1;
        }else if(s2.length() > s1.length()){
            return s2;
        }else{
            if(s1.compareTo(s2) <= 0){
                return s2;
            }else{
                return s1;
            }
        }
    }
}