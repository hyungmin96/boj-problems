import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(stz.nextToken());
        int k = Integer.parseInt(stz.nextToken());

        int[] arr = new int[n + 1];
        stz = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < n; i ++)
            arr[i] = Integer.parseInt(stz.nextToken());

        System.out.println(solution(n, k, arr));

    }

    public static int solution(int n, int k, int[] arr){
        int answer = 0;

        int left = 1;
        int right = 10001;
        int total;

        while(left <= right){
            total = (right + left) / 2;
            
        }

        return answer;
    }

}


