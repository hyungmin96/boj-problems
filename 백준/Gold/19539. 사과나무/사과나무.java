import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        long total = 0, cnt = 0;
        for(int i = 0; i < n; i ++){
            int num = Integer.parseInt(st.nextToken());
            total += num;
            cnt += num / 2;
        }

        if(total % 3 == 0 && total / 3 <= cnt){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }
    }
}