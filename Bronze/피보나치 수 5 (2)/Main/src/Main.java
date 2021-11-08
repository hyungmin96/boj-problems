import java.io.*;
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(solution(n));
    }

    public static int solution(int n){
        int answer = 0;
        if(n == 0) return 0;
        if(n == 1) return 1;
        answer += solution(n - 1) + solution(n - 2);
        return answer;
    }
}
