import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    private static final int INDEX = 3;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Integer[][] array = new Integer[n][10];
        
        for (int i = 0; i < n; i++) {
            Integer[] testcase = new Integer[10];
            StringTokenizer stz = new StringTokenizer(br.readLine(), " ");
            int j = 0;
            while (stz.hasMoreTokens()) {
                testcase[j++] = Integer.parseInt(stz.nextToken());
            }
            Arrays.sort(testcase, Comparator.reverseOrder());
            array[i] = testcase;
        }
        solution(array);
    }

    public static void solution(Integer[][] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i][INDEX - 1]);
        }
    }
}
