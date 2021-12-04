import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(stz.nextToken());
        int k = Integer.parseInt(stz.nextToken());

        int[] arr = new int[n + 1];
        stz = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < n; i ++)
            arr[i] = Integer.parseInt(stz.nextToken());

        System.out.println(solution(n, k, arr));
    }

    public static int solution(int n, int k, int[] arr){

        int left = Arrays.stream(arr).max().getAsInt();
        int right = Arrays.stream(arr).sum();
        int total = 0;
        
        while(left <= right){
            int cnt = 1;
            int sum = 0;
            total = (right + left) / 2;
            for(int i = 0; i < arr.length; i ++){
                sum += arr[i];
                if(total < sum){
                    sum = arr[i];
                    cnt ++;
                }
            }
            if(cnt <= k){
                right = total - 1;
            }else{
                left = total + 1;
            }
        }

        return left;
    }
}


