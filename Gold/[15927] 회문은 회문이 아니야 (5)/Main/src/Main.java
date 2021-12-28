import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        Set<Character> set = new HashSet<>();

        for (char c : s.toCharArray()) {
            set.add(c);
        }
        if (set.size() == 1)
            System.out.println(-1);
        else
            System.out.println(solution(s));
    }

    public static int solution(String s) {

        if (isPalindrome(s))
            return s.length() - 1;

        return s.length();
    }

    public static boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder(s).reverse();

        if (s.equals(sb.toString()))
            return true;

        return false;
    }

}
