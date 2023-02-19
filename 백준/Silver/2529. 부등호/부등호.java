import java.io.*;
import java.util.*;

public class Main {

    static long min = Long.MAX_VALUE;
    static long max = Long.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[] ch = new char[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < n; i ++){
            ch[i] = st.nextToken().charAt(0);
        }

        for(int i = 0; i < 10; i ++){
            int[] arr = new int[n + 1];
            boolean[] check = new boolean[10];
            check[i] = true;
            arr[0] = i;
            dfs(n, 0, arr, ch, check);
            check[i] = false;
        }

        String minString = String.valueOf(min);
        while(minString.length() < n + 1){
            minString = "0" + minString;
        }
        System.out.println(max);
        System.out.println(minString);
    }

    public static void dfs(int n, int depth, int[] arr, char[] ch, boolean[] check){
        if(n == depth){
            long temp = 0;
            for(int i = 0; i < arr.length; i ++) temp = temp * 10 + arr[i];
            min = Math.min(min, temp);
            max = Math.max(max, temp);
            return;
        }

        for(int i = 0; i < 10; i ++){
            if(check[i]) continue;
            if(ch[depth] == '<' && arr[depth] > i) continue;
            if(ch[depth] == '>' && arr[depth] < i) continue;
            check[i] = true;
            arr[depth + 1] = i;
            dfs(n, depth + 1, arr, ch, check);
            check[i] = false;
        }
    }
}