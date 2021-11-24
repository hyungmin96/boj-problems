import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());

        System.out.println(solution(n));
    }

    public static long solution(Long n){
        // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        // 15 == (mid * (mid + 1)) / 2
        long left = 0;
        long right = n;
        
        while(left <= right){
            long mid = (right + left) / 2;
            if(mid * (mid + 1) / 2 > n){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }

        return right;
    }
}
