import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    public int N, W, L;

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");

        Queue<Integer> trucks = new LinkedList<>();
        for(int i = 0; i < N; i ++){
            int num = Integer.parseInt(st.nextToken());
            trucks.offer(num);
        }

        int time = 0;
        long cur_weight = 0;
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < W; i ++) q.offer(0);
        while(!(trucks.isEmpty() && q.isEmpty())){
            time ++;
            if(!q.isEmpty()){
                cur_weight -= q.poll();
            }

            if(trucks.isEmpty()) continue;
            if(cur_weight + trucks.peek() <= L && q.size() < W){
                int cur = trucks.poll();
                cur_weight += cur;
                q.offer(cur);
            }else{
                q.offer(0);
            }
        }

        System.out.println(time);
    }
}