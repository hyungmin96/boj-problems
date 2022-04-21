import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
public class Main {

    private static int N;
    private static HashSet<Long> hash = new HashSet<>();
    private static ArrayList<Long> arrList = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i = 1; i <= 15; i ++){
            solution(0, 0L, i);
        }
        for(Long item : hash){
            arrList.add(item);
        }
        Collections.sort(arrList);
        System.out.println(arrList.size() < N ? -1 : arrList.get(N - 1));
    }

    public static void solution(int depth, Long curr, int n){
        if(depth == n){
            hash.add(curr);
            return;
        }
     
        for(int i = 0; i < 10; i ++){
            if(check(curr * 10 + i)){
                solution(depth + 1, curr * 10 + i, n);
            }
        }
    }

    public static boolean check(Long val){
        Long temp;
        while(true){
            if(val < 10) break;
            temp = val % 10;
            val /= 10;
            if(val % 10 <= temp) return false;
        }
        return true;
    }
}
