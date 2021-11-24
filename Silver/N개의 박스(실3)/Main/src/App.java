import java.io.*;
import java.util.StringTokenizer;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");
        int[] array = new int[stz.countTokens()];
        for(int i = 0; i < 4; i ++){
            array[i] = Integer.parseInt(stz.nextToken());
        }
        System.out.println(solution(array));
    }

    public static int solution(int[] array){
        int answer = 0;

        // * params
        // array[0] : box capacity
        // array[1] : Length
        // array[2] : width
        // array[3] : height

        

        return answer;
    }
}
