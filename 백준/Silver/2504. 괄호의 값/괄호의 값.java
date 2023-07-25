import java.io.*;
import java.util.*;
public class Main {


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        Stack<String> stk = new Stack<>();
        System.out.println(solution(str, stk));
    }

    public static long solution(String str, Stack<String> stk){
        long answer = 0;
        for(int i = 0; i < str.length(); i ++){
            char c = str.charAt(i);
            char next = '\0';
            if(i + 1 < str.length())
                next = str.charAt(i + 1);

            if(c == '(' && next == ')'){
                i ++;
                stk.push("2");
            }else if(c == '[' && next == ']'){
                i ++;
                stk.push("3");
            }else if(c == ')'){
                boolean flag = false;
                long temp = 0;
                while(!stk.isEmpty()){
                    String s = stk.pop();
                    if(s.equals("[")){
                        return 0;
                    }else if(s.equals("(")){
                        flag = true;
                        temp *= 2;
                        break;
                    }else{
                        if(!s.equals("(") && !s.equals("[")){
                            temp += Integer.parseInt(s);
                        }
                    }
                }
                if(!flag) return 0;
                stk.push(temp + "");
            }else if(c == ']'){
                boolean flag = false;
                long temp = 0;
                while(!stk.isEmpty()){
                    String s = stk.pop();
                    if(s.equals("(")){
                        return 0;
                    }else if(s.equals("[")){
                        flag = true;
                        temp *= 3;
                        break;
                    }else{
                        if(!s.equals("(") && !s.equals("[")){
                            temp += Integer.parseInt(s);
                        }
                    }
                }
                if(!flag) return 0;
                stk.push(temp + "");
            }else{
                stk.push(c + "");
            }
        }

        while(!stk.isEmpty()){
            String s = stk.pop();
            if(s.equals("(") || s.equals("[")) return 0;
            answer += Integer.parseInt(s);
        }

        return answer; 
    }
}