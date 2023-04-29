import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < n; tc++) {
            String str = br.readLine();
            Stack<Character> left = new Stack<>();
            Stack<Character> right = new Stack<>();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c == '<') {
                    if (!left.isEmpty()) {
                        right.push(left.pop());
                    }
                } else if (c == '>') {
                    if (!right.isEmpty()) {
                        left.push(right.pop());
                    }
                } else if (c == '-') {
                    if (!left.isEmpty())
                        left.pop();
                } else {
                    left.push(c);
                }
            }

            String temp = "";
            while (!left.isEmpty())
                sb.append(left.pop());
            temp += sb.reverse().toString();
            sb = new StringBuilder();
            while (!right.isEmpty())
                sb.append(right.pop());
            temp += sb.toString();

            answer.append(temp + "\n");
        }
        System.out.println(answer);
    }
}