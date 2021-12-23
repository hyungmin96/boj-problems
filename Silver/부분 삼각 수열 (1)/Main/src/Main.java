import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < arr.length; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);
        System.out.println(solution(n, arr));
    }

    public static int solution(int n, int[] arr) {
        int answer = 0;

        for(int i = 0; i < arr.length - 1; i ++){
            for(int j = arr.length - 1; j >= 0; j --){
                if(arr[i] + arr[i + 1] > arr[j]){
                    answer = Math.max(answer, j - i + 1);
                    break;
                }
            }
        }

        if(arr.length < 2) return 1;
        return answer;
    }
}
