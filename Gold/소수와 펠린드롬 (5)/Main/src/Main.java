
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.println(solution(n));
    }

    public static int solution(int n){
        for(int i = n; i <= 2000000; i ++){
            if(isPrime(i) && isPalindrome(i)){
                return i;
            }   
        }
        return -1;
    }

    public static boolean isPrime(int n){
        if(n == 1) return false;
        for(int i = 2; i <= Math.sqrt(n); i ++){
            if(n % i == 0) return false;
        }
        return true;
    }

    public static boolean isPalindrome(int n){
        StringBuilder sb = new StringBuilder(String.valueOf(n)).reverse();
        if(String.valueOf(n).equals(sb.toString()))
            return true;
        else
            return false;
    }
}
