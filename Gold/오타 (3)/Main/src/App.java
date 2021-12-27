import java.io.*;
import java.util.Stack;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        System.out.println(solution(str));
    }

    public static int solution(String str) {

        int answer = 0;

        if (str.charAt(0) == ')' || str.charAt(str.length() - 1) == '(')
            return 1;
        else {
            int cnt = 0;
            char isTarget = '!';
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '(')
                    cnt++;
            }
            isTarget = (str.length() - cnt < str.length() / 2) ? '(' : ')';

            Stack<Character> st = new Stack<>();
            st.push('(');

            for (int i = 1; i < str.length() - 1; i++) {
            Boolean flag = true;
                if (str.charAt(i) == isTarget) {
                    if (isTarget == ')')
                        st.push('(');
                    else {
                        if (!st.isEmpty())
                            st.pop();
                        else {
                            flag = false;
                            break;
                        }
                    }

                    if(flag){
                        for(int j = i + 1; j < str.length() - 1; j ++){
                            if(str.charAt(j) == '('){
                                st.push('(');
                            }else{
                                if(!st.isEmpty())
                                    st.pop();
                                else{
                                    flag = false;
                                    break;
                                }
                            }
                        }
                    }

                    if (flag)
                        answer++;
                }
            }
        }

        return answer;
    }
}
