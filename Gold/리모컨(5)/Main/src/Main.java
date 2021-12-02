import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
public class Main {

    private static int answer = -1;
    private static int n = 0;
    private static ArrayList<Integer> nums = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int tmp = (n == 0) ? 1 : n;
        int k = Integer.parseInt(br.readLine());

        int[] numbers = new int[10];
        int[] array = new int[k];

        if(k != 0){
            StringTokenizer stz = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < k; i ++){
                array[i] = Integer.parseInt(stz.nextToken());
            }
        }

        for(int i = 0; i < numbers.length; i ++){
            numbers[i] = i;
        }

        for(int i = 0; i < numbers.length; i ++){
            for(int j = 0; j < array.length; j ++)
                if(numbers[i] == array[j])
                    numbers[i] = -1;
        }

        answer = Math.abs(n - 100);
        solution(0, (int)(Math.log10(tmp) + 1) + 1, 0, numbers);
        System.out.println(answer);
    }

    public static void solution(int depth, int level, int curr, int[] numbers){

        if(answer < 2) return;
        
        if(depth == level){
            if(curr != 0) nums.add(curr);
            for(int item : nums){
                int tmp = (item == 0) ? 1 : item;
                answer = (Math.abs(n - item) + (int)(Math.log10(tmp) + 1) < answer) ? Math.abs(n - item) + (int)(Math.log10(tmp) + 1) : Math.abs(answer);
            }
            curr = 0;
            nums.clear();
            return;
        }else{
            for(int i = 0; i < 10; i ++){
                if(numbers[i] != - 1){
                    if((level - 2 > 0 && depth == level - 2) || depth == level - 1){
                        nums.add(curr);
                    }
                    solution(depth + 1, level, curr * 10 + numbers[i], numbers);
                }
            }
        }

    }
}
