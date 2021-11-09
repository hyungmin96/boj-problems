import java.io.*;
import java.util.StringTokenizer;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");
        
        int[] array = new int[n];
        int j = 0;
        while(stz.hasMoreTokens()){
            array[j ++] = Integer.parseInt(stz.nextToken());
        }
        
        System.out.println(solution(array));
    }

    public static int solution(int[] array){
        int answer = 0;
        
        for(int item : array){
            boolean isPrime = true;
            if(item > 1){
                for(int i = 2; i <= Math.sqrt(item); i ++){
                    if(item % i == 0){
                        isPrime = false;
                        break;
                    }
                }
                if (isPrime) answer ++;
            }
        }
        return answer;
    }
}
