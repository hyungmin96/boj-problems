import java.io.*;
import java.util.*;

public class Main {

    static int cnt = 0;
    static int[] nums = new int[27];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        for(int i = 0; i < str.length(); i ++){
            nums[str.charAt(i) - 'a'] ++;
        }

        for(int i = 0; i < 27; i ++){
            if(nums[i] > 0){
                nums[i] --;
                char[] temp = new char[str.length()];
                temp[0] = (char)(i + 'a');
                dfs(1, temp);
                nums[i] ++;
            }
        }

        System.out.println(cnt);
    }

    public static void dfs(int depth, char[] arr){
        if(depth == arr.length){
            cnt ++;
            return;
        }
        for(char a = 'a'; a <= 'z'; a ++){
            if(nums[a - 'a'] > 0){
                if(arr[depth - 1] == a) continue;
                nums[a - 'a'] --;
                arr[depth] = a;
                dfs(depth + 1, arr);
                nums[a - 'a'] ++;
            }
        }
    }
}