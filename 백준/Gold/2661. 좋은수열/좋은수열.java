import java.io.*;
import java.util.*;

public class Main {

    static boolean flag = false;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        backtracking(n, 0);
    }

    public static void backtracking(int n, int depth){
        if(flag) return;
        if(n == depth){
            flag = true;
            System.out.println(sb.toString());
            return;
        }

        for(char i = '1'; i <= '3'; i ++){
            if(!check(depth, sb.toString() + i)) continue;
            sb.append(i);
            backtracking(n, depth + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static boolean check(int len, String temp){
        for(int i = 1; i <= temp.length() / 2; i ++){
            String s1 = temp.substring(temp.length() - i);
            String s2 = temp.substring(temp.length() - i * 2, temp.length() - i);
            if(s1.equals(s2)) return false;
        }
        return true;
    }
}