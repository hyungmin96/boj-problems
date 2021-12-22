import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int index = 0;
        int[] arr = new int[n + 1];
        while (st.hasMoreTokens()) {
            arr[index++] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(n, m, arr));
    }

    public static int solution(int n, int m, int[] arr) {
        int answer = 0;

        int left = 0;
        int right = 0;
        int sum = 0;

        while(true){
            if(sum >= m){
                sum -= arr[left++];
            }else if(right >= arr.length){
                break;
            }else{
                sum += arr[right++];
            }
            if(sum == m)  answer ++;
        }

        return answer;
    }
}
