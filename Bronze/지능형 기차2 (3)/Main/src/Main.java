import java.io.*;
import java.util.StringTokenizer;
public class Main {

    private static int answer = 0;
    private static int curr = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 10; i ++){
            StringTokenizer stz = new StringTokenizer(br.readLine(), " ");
            int exit = Integer.parseInt(stz.nextToken());
            int board = Integer.parseInt(stz.nextToken());
            solution(exit, board);
        }
        System.out.println(answer);
    }

    public static int solution(int exit, int board){
        curr = (curr - exit) + board;
        answer = (curr > answer) ? curr : answer;
        return answer;
    }

}
