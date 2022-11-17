import java.io.*;
import java.util.*;

public class Main {

    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < n; i ++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        boolean[] check = new boolean[n];
        dfs(0, 0, new int[n], arr, check);
        System.out.println(answer);
    }

    public static void dfs(int depth, int cur, int[] temp, int[] arr, boolean[] check){
        if(depth == arr.length){
            for(int i = 0; i < temp.length - 1; i ++){
                cur += Math.abs(temp[i] - temp[i + 1]);
            }
            answer = Math.max(answer, cur);
            return;
        }

        for(int i = 0; i < arr.length; i ++){
            if(check[i]) continue;
            check[i] = true;
            temp[depth] = arr[i];
            dfs(depth + 1, cur, temp, arr, check);
            check[i] = false;
        }
    }
}