import java.io.*;

public class Main {

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] testcase = new int[n];

        for (int i = 0; i < n; i++) {
            testcase[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < n; i++) {
            solution(testcase[i], 1, 1, 1, 0, "1");
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void solution(int n, int now, int num, int operation, int sum, String str) {

        if(now == n){
            sum = sum + (operation * num);
            if(sum == 0){
                sb.append(str + "\n");
            }
            return;
        }else{
            solution(n, now + 1, num * 10 + (now + 1), operation, sum, str + " " + String.valueOf(now + 1));
            solution(n, now + 1, now + 1, 1, sum + (num * operation), str + "+" + String.valueOf(now + 1));
            solution(n, now + 1, now + 1, -1, sum + (num * operation), str + "-" + String.valueOf(now + 1));
        }
        
    }
}
