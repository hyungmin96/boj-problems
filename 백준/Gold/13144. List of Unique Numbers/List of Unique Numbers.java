import java.util.*;
import java.io.*;

class Main {

    static int N;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        int[] arr = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i <= N; i ++) arr[i] = Integer.parseInt(st.nextToken());

        int right = 0;
        long answer = 0;
        boolean[] check = new boolean[100001];
        for(int i = 1; i <= N; i ++){
            while(right + 1 <= N && !check[arr[right + 1]]){
                right ++;
                check[arr[right]] = true;
            }
            answer += (right - i) + 1;
            check[arr[i]] = false;
        }

        System.out.println(answer);
    }
}