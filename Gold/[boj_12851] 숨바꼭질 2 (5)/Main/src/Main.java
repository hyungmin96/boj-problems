import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int[] dis = new int[100001];
        int[] cnt = new int[100001];
        Arrays.fill(dis, Integer.MAX_VALUE);
        boolean[] visited = new boolean[100001];

        System.out.println(solution(start, end, dis, cnt, visited));
    }

    public static String solution(int start, int end, int[] dis, int[] cnt, boolean[] visited) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(start);
        dis[start] = 0;
        visited[start] = true;
        cnt[start] = 1;

        while (!que.isEmpty()) {
            int curr = que.poll();
            for (int i = 0; i < 3; i++) {
                int next = move(i, curr);
                if (next >= 0 && next <= 100000) {
                    if(!visited[next]){
                        visited[next] = true;
                        dis[next] = dis[curr] + 1;
                        cnt[next] = cnt[curr];
                        que.offer(next);
                    }else if(dis[next] == dis[curr] + 1){
                        cnt[next] += cnt[curr];
                    }
                }
            }
        }

        return new StringBuilder(dis[end] + "\n" + cnt[end]).toString();
    }

    public static int move(int d, int curr) {
        if (d == 0)
            return curr - 1;
        else if (d == 1)
            return curr + 1;
        else
            return curr * 2;
    }
}
