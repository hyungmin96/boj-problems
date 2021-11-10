import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        System.out.println(solution(s));
    }

    public static int solution(String s) {

        int mul = 1; // 'multiplication' 곱셈
        int sum = 0;

        Stack<Character> st = new Stack<>();

        for(Character c : s.toCharArray()){
            if(c == '(' || c == '['){
                st.push(c);
                if(c == '('){
                    mul *= 2;
                }else{
                    mul *= 3;
                }
            }else{
                Character c1 = st.pop();
                
                if(st.isEmpty() || c == ')' && c1 == '[') return 0;
                if(st.isEmpty() || c == ']' && c1 == '(') return 0;

                if(c1 == '(' && c == ')'){
                    st.push((char)2);
                }else if(c1 == '[' && c == ']'){
                    st.push((char)3);
                }
            }
        }
        return sum;
    }
}
