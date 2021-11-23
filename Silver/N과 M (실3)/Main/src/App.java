import java.io.*;
import java.util.StringTokenizer;
public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");
        
        int n = Integer.parseInt(stz.nextToken());
        int m = Integer.parseInt(stz.nextToken());

        int[] array = new int[n];
        stz = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < n; i ++){
            array[i] = Integer.parseInt(stz.nextToken());
        }

        

    }
}
