import java.io.*;
import java.util.*;
public class Main {

    static int t;
    public static void main(String[] args) throws IOException{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        while(t -- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            int min = Math.min(n1, n2);
            int max = Math.max(n1, n2);

            int gcd = gcd(max, min);
            int ret = (max * min) / gcd;
            sb.append(ret + "\n");
        }

        System.out.println(sb.toString());
    }

    public static int gcd(int n1, int n2){
        if(n1 % n2 == 0){
            return n2;
        }

        return gcd(n2, n1 % n2);
    }
}