import java.io.*;
import java.util.Stack;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();
        int[] arr = new int[n];

        for(int i = 0; i < n; i ++)
            arr[i] = Integer.parseInt(br.readLine());

        System.out.println(solution(n, str, arr));
    }

    public static String solution(int n, String str, int[] arr){
        
        Stack<Double> st = new Stack<Double>();

        for(int i = 0; i < str.length(); i ++){
            if(str.charAt(i) >= 65 && str.charAt(i) <= 90){
                st.push((double)arr[str.charAt(i) - 65]);
            }else{
                double right = st.pop();
                double left = st.pop();
                double value = 0;
                switch (str.charAt(i)){
                    case '+':
                        value = left + right;
                        break;
                    case '-':
                        value = left - right;
                        break;
                    case '*':
                        value = left * right;
                        break;
                    case '/':
                        value = left / right;
                        break;
                }
                st.push(value);
            }
        }

        return String.format("%.2f", st.pop());
    }
}
