import java.io.*;
import java.util.StringTokenizer;
public class Main {

    private static int N, S, answer;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        boolean[] visited = new boolean[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i ++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 1; i <= N; i ++){
            solution(i, 0, 0, 0, arr, visited);
        }
        System.out.println(answer);
    }

    public static void solution(int n, int depth, int idx, int curr, int[] arr, boolean[] visited){

        if(depth == n){
            if(curr == S){
                answer ++;
            }
            return;
        }
        
        for(int i = idx; i < N; i ++){
            if(!visited[i]){
                visited[i] = true;
                solution(n, depth + 1, i, curr + arr[i], arr, visited);
                visited[i] = false;
            }
        }
    }
}
