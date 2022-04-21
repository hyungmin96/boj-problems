import java.io.*;
import java.util.Arrays;
public class Main {

    private static int N;
    private static boolean[] visited;
    private static StringBuilder sb = new StringBuilder();
    private static char[] pre;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i ++){
            char[] temp = br.readLine().toCharArray();
            pre = new char[temp.length];
            Arrays.sort(temp);
            visited = new boolean[temp.length];
            solution(temp, 0, "");
        }
        System.out.println(sb.toString());
    }

    public static void solution(char[] temp, int depth, String curr){
        if(temp.length == depth){
            sb.append(curr + "\n");
            return;
        }

        pre[depth] = 0;
        for(int i = 0; i < temp.length; i ++){
            if(visited[i]) continue;
            if(pre[depth] >= temp[i]) continue;
            pre[depth] = temp[i];
            visited[i] = true;
            solution(temp, depth + 1, curr + temp[i] + "");
            visited[i] = false;
        }
    }
}
