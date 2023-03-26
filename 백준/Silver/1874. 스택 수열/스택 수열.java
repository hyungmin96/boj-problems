import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < n; i ++){
            int num = Integer.parseInt(br.readLine());
            arr[i] = num;
        }

        int idx = 0, cnt = 1;
        while(idx < arr.length){
            if(cnt >= n && !st.isEmpty() && st.peek() > arr[idx]){
                System.out.println("NO");
                return;
            }
            if(!st.isEmpty() && st.peek() == arr[idx]){
                idx ++;
                st.pop();
                sb.append("-\n");
            }else{
                st.push(cnt ++);
                sb.append("+\n");
            }
        }

        System.out.println(sb.toString());        
    }
}