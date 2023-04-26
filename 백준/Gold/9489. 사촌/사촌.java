import java.io.*;
import java.util.*;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            if(n == 0 && k == 0) break;

            int[] arr = new int[n + 1];
            int[] parents = new int[n + 1];

            parents[0] = -1;
            parents[1] = 0;
            int target_idx = -1;
            st = new StringTokenizer(br.readLine(), " ");
            for(int i = 1; i <= n; i ++){
                int num = Integer.parseInt(st.nextToken());
                arr[i] = num;
                if(arr[i] == k) target_idx = i;
            }

            int idx = 1;
            for(int i = 2; i <= n; i ++){
                parents[i] = idx;
                if(i < n && arr[i] + 1 != arr[i + 1]) idx ++;
            }

            int answer = 0;
            for(int i = 1; i <= n; i ++){
                if(parents[parents[i]] == parents[parents[target_idx]]){
                    if(parents[i] != parents[target_idx]){
                        answer ++;
                    }
                }
            }

            sb.append(answer + "\n");
        }
        System.out.println(sb.toString());
    }
}