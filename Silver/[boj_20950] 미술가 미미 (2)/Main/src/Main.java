import java.io.*;
import java.util.StringTokenizer;
public class Main {

    private static int N;
    private static char[] operation;
    private static long min = Long.MAX_VALUE;
    private static long max = Long.MIN_VALUE;
    private static boolean[] visited;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        operation = new char[N];
        visited = new boolean[10];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i ++){
            operation[i] = st.nextToken().charAt(0);
        }
        solution(N, 0, 0, new int[N + 1]);
        int i = 1;
        for(int j = 0; j < N; j ++){
            i *= 10;
        }
        if(max < i){
            System.out.println("0" + max);
        }else{
            System.out.println(max);
        }
        if(min < i){
            System.out.println("0" + min);
        }else{
            System.out.println(min);
        }
    }

    public static void solution(int n, int depth, long curr, int[] tArr){
        if(n + 1 == depth){
            max = (max < curr) ? curr : max;
            min = (min > curr) ? curr : min;
            return;
        }

        for(int i = 0; i < 10; i ++){
            if(visited[i]) continue;
            if(depth > 0){
                if(operation[depth - 1] == '>'){
                    if(tArr[depth - 1] <= i) continue;
                }else{
                    if(tArr[depth - 1] >= i) continue;
                }
            }
            visited[i] = true;
            tArr[depth] = i;
            solution(n, depth + 1, curr * 10 + i, tArr);
            visited[i] = false;
            tArr[depth] = 0;
        }
  
    }
}
