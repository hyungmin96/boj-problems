import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    
    private static ArrayList<Integer> values = new ArrayList<>();
    private static int answer = 0;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<int[]> arrList = new ArrayList<>();
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int[] arr = new int[st.countTokens()];
            for(int j = 0; j < arr.length; j ++)
                arr[j] = Integer.parseInt(st.nextToken());
            
            arrList.add(arr);
        }

        solution(0, n, 0, arrList);
        System.out.println(answer);
    }

    public static void solution(int depth, int n, int index, ArrayList<int[]> arrList){
        if(depth == n){
            int temp = 0;
            for (int item : values) {
                temp += item;
            }
            answer = Math.max(temp, answer);
            return;
        }else{
            values.add(arrList.get(depth)[index]);
            solution(depth + 1, n, index, arrList);
            solution(depth + 1, n, index + 1, arrList);
            values.remove(values.size() - 1);
        }
    }
}
