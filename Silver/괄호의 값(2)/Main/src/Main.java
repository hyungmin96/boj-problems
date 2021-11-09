import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        System.out.println(solution(s));
    }

    public static int solution(String s) {
        int answer = 0;
        int temp = 0;
        Stack<Character> st = new Stack<Character>();

        for (Character c : s.toCharArray()) {
            if (c == '(' || c == '[') {
                st.push(c);
            } else if (c == ')') {
            
            } else if (c == ']') {
                
            }
        }
        return answer;
    }
}
