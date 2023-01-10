import java.io.*;
import java.util.*;

public class Main {

    static int answer = 0;
    static HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        boolean[] check = new boolean[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < arr.length; i ++){
            arr[i] = Integer.parseInt(st.nextToken());
            answer += arr[i];
        }

        for(int i = 0; i < n; i ++){
            int nums = Integer.parseInt(br.readLine());
            if(nums > 0){
                HashMap<Integer, Integer> info = new HashMap<>();
                for(int j = 0; j < nums; j ++){
                    st = new StringTokenizer(br.readLine(), " ");
                    int target = Integer.parseInt(st.nextToken());
                    int discount = Integer.parseInt(st.nextToken());
                    
                    info.put(target, discount);
                }
                map.put(map.size() + 1, info);
            }else{
                map.put(map.size() + 1, new HashMap<>());
            }
        }

        dfs(n, 0, 0, arr, check);
        System.out.println(answer);
    }

    public static void dfs(int n, int depth, int price, int[] arr, boolean[] check){
        if(price >= answer) return;
        if(n == depth){
            answer = Math.min(answer, price);
            return;
        }

        for(int i = 0; i < n; i ++){
            if(check[i]) continue;
            check[i] = true;
            
            for(int k : map.get(i + 1).keySet())
                arr[k - 1] -= map.get(i + 1).get(k);

            dfs(n, depth + 1, price + Math.max(arr[i], 1), arr, check);
            
            for(int k : map.get(i + 1).keySet())
                arr[k - 1] += map.get(i + 1).get(k);

            check[i] = false;
        }
    }
}