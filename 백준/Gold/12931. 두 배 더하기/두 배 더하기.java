import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < n; i ++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(arr));
    }

    public static int solution(int[] arr){
        int answer = 0;
        for(;;){
            int cnt = 0;
            boolean flag = false;
            boolean is_end = true;
            for(int i = 0; i < arr.length; i ++){
                if(arr[i] == 0) continue;
                is_end = false;
                if(arr[i] >= 2 && arr[i] % 2 == 0){
                    flag = true;
                }else{
                    arr[i] --;
                    cnt ++;
                }
            }

            if(flag){
                for(int i = 0; i < arr.length; i ++){
                    if(arr[i] >= 2){
                        arr[i] /= 2;
                    }
                }
                answer ++;
            }

            answer += cnt;
            if(is_end) break;
        }   
        return answer;     
    }
}