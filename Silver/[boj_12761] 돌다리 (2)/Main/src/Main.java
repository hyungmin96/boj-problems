import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int[] dis = new int[100001];
        System.out.println(solution(a, b, start, end, dis));
    }

    public static int solution(int a, int b, int start, int end, int[] dis) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(start);
        dis[start] = 1;

        while (!que.isEmpty()) {
            int curr = que.poll();
            for(int i = 0; i < 8; i ++){
                int next = move(a, b, i, curr);
                if(next >= 0 && next <= 100000 && dis[next] == 0){
                    dis[next] = dis[curr] + 1;
                    que.offer(next);
                }
            }
        }
        return dis[end] - 1;
    }

    public static int move(int a, int b, int d, int curr){
        if(d == 0) return curr + 1;
        else if(d == 1) return curr - 1;
        else if(d == 2) return curr + a;
        else if(d == 3) return curr - a;
        else if(d == 4) return curr + b;
        else if(d == 5) return curr - b;
        else if(d == 6) return curr * a;
        else  return curr  * b;
    }

}
