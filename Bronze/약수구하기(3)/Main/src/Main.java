import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(stz.nextToken());
        int k = Integer.parseInt(stz.nextToken());
        System.out.println(solution(n, k));
    }

    public static int solution(int n, int k){
        ArrayList<Integer> list = new ArrayList<>();

        for(int i = 1; i <= n; i ++){
            if(n % i == 0){
                list.add(i);
            }
        }

        if(list.size() >= k)
            return list.get(k - 1);
        else
            return 0;
    }
}
