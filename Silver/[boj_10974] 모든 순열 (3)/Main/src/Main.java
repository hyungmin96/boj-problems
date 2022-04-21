import java.io.*;
public class Main {

    private static int N;
    private static boolean[] visited;
    private static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N];
        solution(0, "");
        System.out.println(sb.toString());
    }

    public static void solution(int depth, String str){
        if(depth == N){
            sb.append(str).append("\n");
            return;
        }

        for(int i = 0; i < N; i ++){
            if(visited[i]) continue;
            visited[i] = true;
            solution(depth + 1, str + (i + 1) + " ");
            visited[i] = false;
        }
    }
}
