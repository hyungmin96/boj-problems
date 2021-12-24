import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();
        boolean[] visited = new boolean[n];
        int s = Integer.parseInt(br.readLine());

        // System.out.println(solution(n, s - 1, arr, visited));
        dfs(s - 1, n, arr, visited);
        System.err.println(ans);
    }

    public static int solution(int n, int s, int[] arr, boolean[] visited) {
        int answer = 1;
        Queue<Integer> que = new LinkedList<>();
        int[] moves = new int[] { -1, 1 };
        visited[s] = true;
        que.offer(s);

        while (!que.isEmpty()) {
            int index = que.poll();
            int value = arr[index];
            for (int move : moves) {
                int isMove = (move * value) + index;
                if (isMove >= 0 && isMove < arr.length && !visited[isMove]) {
                    visited[isMove] = true;
                    que.offer(isMove);
                    answer++;
                }
            }
        }
        return answer;
    }

    public static void dfs(int curr, int n, int[] arr, boolean[] visited){
        int[] moves = new int[]{-1, 1};
        
        if(n == 1) {
            ans ++;
            return;
        }
        if(curr < 0 || curr >= n || visited[curr]){
            return;
        }else{
            visited[curr] = true;
            ans ++;
            for(int move : moves){
                dfs((arr[curr] * move) + curr, n, arr, visited);
            }
        }
    }
}
