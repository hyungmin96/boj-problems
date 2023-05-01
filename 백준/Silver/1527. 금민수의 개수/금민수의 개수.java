import java.io.*;
import java.util.*;

public class Main {

    static int answer = 0;
    static int[] arr = { 4, 7 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());

        dfs(n1, n2, 0);

        System.out.println(answer);
    }

    public static void dfs(int n1, int n2, long cur){
        if(n2 < cur){
            return;
        }

        if(n1 <= cur && n2 >= cur){
            answer ++;
        }

        for(int i = 0; i < arr.length; i ++){
            dfs(n1, n2, cur * 10 + arr[i]);
        }
    }
}