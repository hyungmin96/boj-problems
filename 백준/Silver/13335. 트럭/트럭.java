import java.io.*;
import java.util.*;

public class Main {

    static int N, W, L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        Queue<Integer> wait = new LinkedList<>(); 
        Queue<Integer> bridge = new LinkedList<>();
        for(int i = 0; i < N; i ++)
            wait.offer(Integer.parseInt(st.nextToken()));

        int answer = 0, weight = 0, cnt = wait.size();
        while(cnt > 0 && !(wait.isEmpty() && bridge.isEmpty())){
            answer ++;
            if(bridge.size() >= W){
                if(bridge.peek() > 0) cnt --;
                weight -= bridge.poll();
            }
            if(!wait.isEmpty() && weight + wait.peek() <= L && bridge.size() < W) {
                int truck = wait.poll();
                bridge.offer(truck);
                weight += truck;
            }else{
                bridge.offer(0);
            }
        }

        System.out.println(answer);
    }
}