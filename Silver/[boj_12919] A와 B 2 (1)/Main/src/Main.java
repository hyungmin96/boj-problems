import java.io.*;
public class Main {

    public static int answer = 0;
    public static boolean flag = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();
        dfs(s2.length(), 0, s1, s2);
        System.out.println(answer);
    }

    public static void dfs(int n, int depth, String begin, String target){
        if(flag) return;
        if(begin.length() == target.length()){
            if(begin.equals(target)){
                answer = 1;
                flag = true;
            } else answer = 0;
            return;
        }else{
            if(target.charAt(target.length() - 1) == 'A')
                dfs(n, depth + 1, begin, target.substring(0, target.length() - 1));
            if(target.charAt(0) == 'B')
                dfs(n, depth + 1, begin, new StringBuilder(target.substring(1)).reverse().toString());
        }
    }
}
