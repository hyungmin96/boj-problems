import java.io.*;
import java.util.*;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution(arr));
    }

    public static String solution(int[] arr) {
        StringBuilder sb = new StringBuilder();
        int[] max = new int[] { Integer.MIN_VALUE, -1 };
        Stack<int[]> st = new Stack<>();
        int[] answer = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if(max[0] <= arr[i]){
                st.clear();
                st.push(new int[] { arr[i], i });
                if(max[0] == arr[i]) answer[i] = max[1] + 1;
                max = new int[] { arr[i], i };
                continue;
            }
            while (!st.isEmpty() && st.peek()[0] < arr[i]) {
                if (!st.isEmpty()) st.pop();
                if (max[0] <= arr[i]) max = new int[] { arr[i], i };
            }
            if (st.peek()[0] >= arr[i])  answer[i] = st.peek()[1] + 1;
            st.push(new int[] { arr[i], i });
        }
        
        for(int i = 0; i < answer.length; i ++){
            sb.append(answer[i] + " ");
        }

        return sb.toString();
    }
}