import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        System.out.println(solution(s));
    }

    public static int solution(String s) {

        Stack<String> stack = new Stack<>();
        int temp = 0;

        for(int i = 0; i < s.length(); i ++){
            if(s.charAt(i) == '(' || s.charAt(i) == '['){
                stack.push(String.valueOf(s.charAt(i)));
            }else{
                if(s.charAt(i - 1) == '(' && s.charAt(i) == ')'){
                    stack.pop();
                    stack.push("2");
                }else{
                    if(stack.peek() == "[")
                        return 0;
                    else{
                        if(stack.peek().charAt(0) != '(' || stack.peek().charAt(0) != '['){
                            temp += Integer.parseInt(stack.peek());
                        }else if(stack.peek().charAt(0) == '('){
                            temp *= 2;
                        }else if(stack.peek().charAt(0) == '('){
                            temp *= 3;
                        }
                        stack.pop();
                        stack.push(String.valueOf(temp));
                        temp = 0;
                    }
                }
                
                if(s.charAt(i - 1) == '[' && s.charAt(i) == ']'){
                    stack.pop();
                    stack.push("3");
                }
            }

        }

        return 0;
    }
}
