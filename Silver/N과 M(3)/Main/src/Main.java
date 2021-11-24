import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {

    private static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(stz.nextToken());
        int m = Integer.parseInt(stz.nextToken());
        int[] array = Arrays.stream(br.readLine().split(" ")).mapToInt(item -> Integer.parseInt(item)).toArray();
        boolean[] visited = new boolean[n];

        Arrays.sort(array);
        solution(0, m, array, visited);
    }

    public static void solution(int depth, int m, int[] array, boolean[] visited){
        if(depth == m){
            System.out.println(sb.toString());
            return;
        }else{
            for(int i = 0; i < array.length; i ++){
                if(!visited[i]){
                    int l = (int)(Math.log10(array[i]) + 1);
                    visited[i] = true;
                    sb.append(array[i]).append(" ");
                    solution(depth + 1, m, array, visited);
                    sb.delete(sb.length() - (l + 1), sb.length());
                    visited[i] = false;
                }
            }
        }
    }
}
