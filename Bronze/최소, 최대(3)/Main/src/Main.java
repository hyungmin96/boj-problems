import java.io.*;
import java.util.StringTokenizer;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] array = new int[n];
        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");
        int i = 0;
        while(stz.hasMoreTokens()){
            array[i++] = Integer.parseInt(stz.nextToken());
        }
        System.out.println(solution(n, array));
    }

    public static String solution(int n, int[] array){
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int item : array){
            max = (item > max) ? item : max;
            min = (item < min) ? item : min;
        }

        return min + " " + max;
    }
}
