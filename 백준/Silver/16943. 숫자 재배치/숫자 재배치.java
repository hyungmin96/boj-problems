import java.io.*;
import java.util.*;

public class Main {

    static int answer = -1;

    public static class Pair{
        int len;
        int[] arr;
        public Pair(int len, int[] arr) { this.len = len; this.arr = arr; }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());

        Pair p = getPair(n1);
        int len2 = getPair(n2).len;
        if(p.len > len2){
            System.out.println(-1);
            return;
        }
        dfs(p.len, 0, 0, n1, n2, p.arr);

        System.out.println(answer);
    }

    public static void dfs(int n, int depth, int cur, int n1, int n2, int[] arr){
        if(depth == n){
            answer = Math.max(answer, cur);
            return;
        }

        for(int i = 0; i < 10; i ++){
            if(cur * 10 + i > n2) continue;
            if(depth == 0 && i == 0) continue;
            if(arr[i] > 0){
                arr[i] --;
                dfs(n, depth + 1, cur * 10 + i, n1, n2, arr);
                arr[i] ++;
            }
        }
    }

    public static Pair getPair(int n){
        int len = 0;
        int[] temp = new int[10];
        while(n > 0){
            len ++;
            temp[n % 10] ++;
            n /= 10;
        }
        return new Pair(len, temp);
    }
}