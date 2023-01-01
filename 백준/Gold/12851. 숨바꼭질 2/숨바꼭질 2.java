import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        bfs(s, e);
    }

    public static void bfs(int s, int e){
        int[] dis = new int[100001];
        int cnt = 0;
        int time = Integer.MAX_VALUE;
        Queue<int[]> que = new LinkedList<>();

        Arrays.fill(dis, 987654321);
        que.offer(new int[] { s, 0 });
        dis[s] = 0;

        while(!que.isEmpty()){
            int[] cur = que.poll();
            if(cur[0] == e){
                if(time > cur[1]){
                    cnt = 1;
                    time = cur[1];
                }else if(time == cur[1]){
                    cnt ++;
                }
                continue;
            }
            if(check(cur[0] - 1, cur[1], dis))
                que.offer(new int[] { cur[0] - 1, cur[1] + 1});

            if(check(cur[0] + 1, cur[1], dis))
                que.offer(new int[] { cur[0] + 1, cur[1] + 1});

            if(check(cur[0] * 2, cur[1], dis))
                que.offer(new int[] { cur[0] * 2, cur[1] + 1});
        }

        System.out.println(time);
        System.out.println(cnt);
    }

    public static boolean check(int move, int cnt, int[] dis){
        cnt ++;
        if(move < 0 || move >= dis.length) return false;
        if(dis[move] < cnt) return false;
        dis[move] = cnt;
        return true;
    }
}