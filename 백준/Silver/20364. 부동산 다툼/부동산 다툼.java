import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        boolean[] check = new boolean[n + 1];

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < q; i ++){
            int answer = 0;
            int num = Integer.parseInt(br.readLine());
            for(int cur = num; cur > 0; cur /= 2){
                if(check[cur]){
                    answer = cur;
                }
            }    
            check[num] = true;
            sb.append(answer + "\n");
        }

        System.out.println(sb.toString());
    }
}