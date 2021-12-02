import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(solution(n));
    }

    public static long solution(int n){
        long answer = 1;
        if (n == 0) return 0;
        for(int i = 1; i <= n; i ++){
            answer *= i;
            while(answer % 10 == 0)
                answer /= 10;
            if(answer > 1000000)
                answer %= 1000000;
        }

        return answer % 10;
    }
}
