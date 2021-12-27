import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        long[] arr = new long[(int)n];
        for(int i = 0; i < n; i ++)
            arr[i] = Long.parseLong(br.readLine());

        for(int i = 0; i < n; i ++)
            System.out.println(solution(arr[i]));
    }

    public static long solution(long num){
        if (num == 2) return num;
        if(num == 0 || num == 1) return 2;
        while(true){
            boolean isPrime = true;
            for(int i = 2; i <= Math.sqrt(num); i ++){
                if(num % i == 0){
                    num ++;
                    isPrime = false;
                    break;
                }
            }
            if(isPrime) return num;
        }
    }
}
