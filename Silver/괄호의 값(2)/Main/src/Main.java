import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        System.out.println(solution(s));
    }

    public static int solution(String s) {

        Stack<String> st = new Stack<>();
        int answer = 0;

        if(s.length() % 2 != 0) return 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[')
                st.push(s.charAt(i) + "");
            else {
                if(st.size() == 0){
                    if(s.charAt(i) == ')' || s.charAt(i) == ']') 
                        return 0;
                }

                if (s.charAt(i) == ')') {
                    if (s.charAt(i - 1) == '[') {
                        return 0;
                    } else {
                        if (s.charAt(i - 1) == '(') {
                            st.pop();
                            st.push("2");
                        } else {
                            int temp = 0;
                            while (!st.isEmpty() && !st.peek().equals("(")) {
                                String str = st.pop();
                                if (!str.equals("(") && !str.equals("[")) {
                                    temp += Integer.parseInt(str);
                                }else if(str.equals("[")) return 0;
                            }
                            if(!st.isEmpty())
                                st.pop();
                            st.push(String.valueOf(temp * 2));
                        }
                    }
                } else {
                    if (s.charAt(i - 1) == '(') {
                        return 0;
                    } else {
                        if (s.charAt(i - 1) == '[') {
                            st.pop();
                            st.push("3");
                        } else {
                            int temp = 0;
                            while (!st.isEmpty() && !st.peek().equals("[")) {
                                String str = st.pop();
                                if (!str.equals("(") && !str.equals("[")) {
                                    temp += Integer.parseInt(str);
                                }else if(str.equals("(")) return 0;
                            }
                            if(!st.isEmpty())
                                st.pop();

                            st.push(String.valueOf(temp * 3));
                        }
                    }
                }
            }
        }

        while (!st.isEmpty()) {
            if(st.peek().equals("(") || st.peek().equals("["))  
                return 0;
            answer += Integer.parseInt(st.pop());
        }

        return answer;
    }
}
