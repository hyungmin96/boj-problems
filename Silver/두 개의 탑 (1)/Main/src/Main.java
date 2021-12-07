import java.io.*;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        
        for(int i = 0; i < n; i ++)
            arr[i] = Integer.parseInt(br.readLine());

        System.out.println(solution(n, arr));
    }

    public static int solution(int n, int[] arr){
        int answer = Integer.MIN_VALUE;
        int t1 = 0; // 1번째 tower
        int t2 = 0; // 2번째 tower
        int dis = Arrays.stream(arr).sum();
        int tmp = arr[t1];

        // arr[] = 1, 2, 3, 4, 5
        while(t1 <= t2 && t2 < n){
            int min = Math.min(tmp, dis - tmp);
            answer = (answer > min) ? answer : min;

            if(tmp == min){
                tmp += arr[++t2];
            }else{
                tmp -= arr[t1++];
            }
        }

        return answer;
    }
}
