import java.io.*;
import java.util.StringTokenizer;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(stz.nextToken());
        int k = Integer.parseInt(stz.nextToken());

        int[] array = new int[n];
        for(int i = 0; i < n; i ++)
            array[i] = i + 1;

        solution(0, k, 0, new StringBuilder(), array);
    }

    public static void solution(int depth, int level, int index, StringBuilder sb, int[] array){
        if(depth == level){
            System.out.println(sb.toString());
            return;
        }else{
            for(int i = index; i < array.length; i ++){
                sb.append(array[i] + " ");
                solution(depth + 1, level, i, sb, array);
                sb.delete(sb.toString().length() - 2, sb.toString().length());
            }
        }
    }
}
