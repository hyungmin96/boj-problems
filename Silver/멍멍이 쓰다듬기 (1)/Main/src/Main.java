import java.io.*;
import java.util.StringTokenizer;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        System.out.println(solution(n, k));
    }

    public static long solution(int n, int k){
        long answer = 0;
        long temp = 0;
        int diff = k - n;
        
        if(diff <= 3) 
            return diff;
        else{
            while(temp * temp < diff){
                temp ++;
            }
            temp = (temp * temp == diff) ? temp : temp - 1;
            answer = temp * 2 - 1;
            diff -= temp * temp;

            while(diff != 0){
                diff -= Math.min(temp, diff);
                answer ++;
            }
        }

        return answer;
    }
}
