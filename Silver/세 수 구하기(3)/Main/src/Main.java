import java.io.*;
import java.util.StringTokenizer;
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(stz.nextToken());
        int s = Integer.parseInt(stz.nextToken());

        boolean[] set = new boolean[1002];

        if(s > 0){
            stz = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < s; i ++){
                set[Integer.parseInt(stz.nextToken())] = true;
            }
        }

        System.out.println(solution(n, set));
    }

    public static int solution(int n, boolean[] set){
        int answer = Integer.MAX_VALUE;

        for(int x = 1; x <= 1000; x ++){
            for(int y = 1; y <= 1000; y ++){
                for(int z = 1; z <= 1001; z ++){
                    int xyz = x * y * z;
                    if(!set[x] && !set[y] && !set[z]){
                        answer = (answer > Math.abs(n - xyz)) ? Math.abs(n - xyz) : answer;
                    }
                }
            }
        }

        return answer;
    }

}
