import java.io.*;
import java.util.Arrays;
public class Main {

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());

        for(int i = 0; i < testcase; i ++){
            int numbers = Integer.parseInt(br.readLine());
            String[] arr = new String[numbers];
            for(int j = 0; j < numbers; j ++){
                arr[j] = br.readLine();
            }
            // *
            // [1, 3]    
            // [1, 5]   
            // [1, 3, 5, 9]   
            // 이런식의 번호가 올 경우 각 배열을 길이가 가장 짧은 배열마다 비교해야한다.
            // 이러면 시복도가 올라가기에, 정렬을 통해 각 접두사가 인접하는 문자열끼지 정렬해주고
            // i번째 배열과 i + 1번째 배열의 접두사가 동일한지만 확인하면 O(n) 으로 처리가 가능
            
            Arrays.sort(arr);
            sb.append(solution(arr) + "\n");
        }
        System.out.println(sb.toString());
    }

    public static String solution(String[] arr){

        for(int i = 0; i < arr.length - 1; i ++){
            if(arr[i + 1].startsWith(arr[i]))
                return "NO";
        }

        return "YES";
    }
}
