import java.io.*;
public class Main {

    private static boolean flag = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[] {1, 2, 3};
        int n = Integer.parseInt(br.readLine());
        solution(n, 0, "", arr);
    }

    public static void solution(int n, int depth, String str, int[] arr){
        if(flag) return;
        if(depth == n){
            flag = true;
            System.out.println(str);
        return;
        }else{
            for(int i = 0; i < arr.length; i ++)
                if(isSequence(str + arr[i]))
                    solution(n, depth + 1, str + arr[i], arr);
        }
    }

    public static boolean isSequence(String s){
        int len = s.length();
        int start = len - 1;

        for(int i = 1; i <= len / 2; i ++){
            String t1 = s.substring(start - i, len - i);
            String t2 = s.substring(start, len);
            if(t1.equals(t2))
                return false;
            start -= 1;
        }

        return true;
    }
}
