import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");
        
        int n = Integer.parseInt(stz.nextToken());
        int k = Integer.parseInt(stz.nextToken());
        int[] array = new int[n];

        for(int i = 0; i < n; i ++)
            array[i] = Integer.parseInt(br.readLine());

        Arrays.sort(array);

        System.out.println(solution(n, k, array));
    }

    public static long solution(int n, int k, int[] array){
        long left = 1;
        long right = array[array.length - 1];

        while(left <= right){
            long sum = 0;
            long mid = (right + left) / 2;
            for(int i = 0; i < n; i ++){
                sum += (array[i] / mid);
            }
            if(sum >= k){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }

        return right;
    }
}
