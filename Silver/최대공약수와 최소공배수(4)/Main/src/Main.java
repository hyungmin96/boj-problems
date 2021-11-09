import java.io.*;
import java.util.StringTokenizer;
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(stz.nextToken());
        int k = Integer.parseInt(stz.nextToken());
        int GCM = solution(n, k);
        int LCD = n * k / GCM;
        System.out.println(GCM);
        System.out.println(LCD);
    }

    public static int solution(int n, int k){
        while(k > 0){
            int temp = n;
            n = k;
            k = temp % k;
        }
        return n;
    }
}
