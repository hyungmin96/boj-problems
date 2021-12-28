import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    private static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        if (n != 1) {
            solution(n, arr, 0, 0);
        } else {
            set.add(arr[0]);
        }

        for(int i = 1; i < 1000000; i ++){
            if(!set.contains(i)){
                System.out.println(i);
                break;
            }
        }
    }

    public static void solution(int n, int[] arr, int depth, int curr) {
        if (depth == n) {
            if(curr > 0)
                set.add(curr);
            return;
        } else {
            solution(n, arr, depth + 1, curr + arr[depth]);
            solution(n, arr, depth + 1, curr);
        }
    }
}
