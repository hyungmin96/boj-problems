import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        int answer = 0;
        int[] arr = new int[1_000_001];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i ++){
            int num = Integer.parseInt(st.nextToken());
            if(arr[num] == 0){
                answer ++;
            }else{
                arr[num] --;
            }
            arr[num - 1] ++;
        }

        System.out.println(answer);
    }
}