
import java.io.*;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int m = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        
        solution(m, n);
    }

    public static void solution(int m, int n){

        ArrayList<Integer> items = new ArrayList<>();

        for(int i = m; i <= n; i ++){
            if(isPrime(i)) items.add(i);
        }

        int total = 0;
        for(int item : items) total += item;

        if(items.size() > 0){
            System.out.println(total);
            System.out.println(items.get(0));
        }
        else
            System.out.println("-1");

    }

    public static boolean isPrime(int n){
        
        if(n < 2) return false; // 0과 1은 소수가 아님
        if(n == 2) return true; // 2는 소수

        for(int i = 2; i <= Math.sqrt(n); i ++){
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }
}
