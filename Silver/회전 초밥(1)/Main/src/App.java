import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;
public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(stz.nextToken());
        int edge = Integer.parseInt(stz.nextToken());
        int k = Integer.parseInt(stz.nextToken());
        int coupon = Integer.parseInt(stz.nextToken());

        int[] arr = new int[n];

        for(int i = 0; i < n; i ++)
            arr[i] = Integer.parseInt(br.readLine());

        System.out.println(solution(n, edge, k, coupon, arr));

    }

    public static int solution(int n, int edge, int k, int coupon, int[] arr){
        
        int answer = Integer.MIN_VALUE;
        int cnt = 0;
        ArrayList<Integer> arrList = new ArrayList<>();

        for(int i = 0; i < n; i ++){
            cnt ++;
            if(arr[i] != coupon){
                arrList.add(arr[i]);
                if(cnt == arrList.size() + 1){
                    cnt --;
                    arrList.remove(0);
                    answer = (answer > arrList.size() + 1) ? answer : arrList.size() + 1;
                }
            }
        }
        
        return answer;
    }
}
