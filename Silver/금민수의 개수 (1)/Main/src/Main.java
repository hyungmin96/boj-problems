import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
public class Main {

    private static int answer = 0;
    private static ArrayList<Integer> arrList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        solution(0, 0);
        Collections.sort(arrList);

        for(int i = 1; i < arrList.size(); i ++){
            if(arrList.get(i) >= n && arrList.get(i) <= k)
                answer ++;
            else if(arrList.get(i) > k)
                break;
        }
        System.out.println(answer);
    }

    public static void solution(int depth, int curr){
        if(depth == 10){
            return;
        }else{
            arrList.add(curr);
            solution(depth + 1, curr * 10 + 4);
            solution(depth + 1, curr * 10 + 7);
        }
    }
}
