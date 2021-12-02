import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        solution(0, n, 0);
    }


    public static void solution(int depth, int level, int curr){

        if(depth == level){
            if(isPrime(curr))
                System.out.println(curr);
            curr = 0;
            return;
        }else{
            for(int i = 1; i < 10; i ++){
                int n = curr * 10 + i;
                if(isPrime(n))
                    solution(depth + 1, level, curr * 10 + i);
            }
        }
    }

    public static boolean isPrime(int n){
        if(n == 0 || n == 1) return false;
        for(int i = 2; i <= Math.sqrt(n); i ++){
            if(n % i == 0) return false;
        }
        return true;
    }

}
