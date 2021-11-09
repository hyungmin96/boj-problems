
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
        int answer = 0;
        ArrayList<Integer> items = new ArrayList<>();
        
        for(int i = 1; i <= k; i ++){
            for(int j = 0; j < i; j ++){
                items.add(i);
            }
        }
        
        for(int i = n - 1; i < k; i ++) answer += items.get(i);

        return answer;
    }
}
