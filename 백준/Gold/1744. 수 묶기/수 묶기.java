import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> minus = new PriorityQueue<>();
        PriorityQueue<Integer> positive = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < n; i ++){
            int num = Integer.parseInt(br.readLine());
            if(num <= 0) minus.offer(num);
            else positive.offer(num);
        }

        Stack<Long> st = new Stack<>();
        long n1 = -1001, n2 = 1001;
        while(!minus.isEmpty()){
            if(minus.size() >= 2){
                long t1 = minus.poll();
                long t2 = minus.poll();
                st.push(Math.max(t1 * t2, t1 + t2));
            }else{
                n1 = minus.poll();
            }
        }

        while(!positive.isEmpty()){
            if(positive.size() >= 2){
                long t1 = positive.poll();
                long t2 = positive.poll();
                st.push(Math.max(t1 * t2, t1 + t2));
            }else{
                n2 = positive.poll();
            }
        }

        if(n1 != -1001 && n2 != 1001){
            st.push(Math.max(n1 * n2, n1 + n2));
        }else if(n1 == -1001 && n2 != 1001){
            st.push(n2);
        }else if(n1 != -1001 && n2 == 1001){
            st.push(n1);
        }

        long answer = 0;
        while(!st.isEmpty()){ answer += st.pop(); }
        System.out.println(answer);
    }
}