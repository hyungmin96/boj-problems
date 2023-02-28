import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isEquals(arr[i], arr[j])) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }

    public static boolean isEquals(String cur, String target) {
        boolean[] check = new boolean[26];
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < cur.length(); i++) {
            char c = cur.charAt(i);
            char t = target.charAt(i);

            if(check[t - 'a'] && !map.containsKey(c)){
                return false;
            }
            if (!check[t - 'a'] && !map.containsKey(c)) {
                map.put(c, t);
                check[t - 'a'] = true;
            } else if (map.get(c) != t) {
                return false;
            }
        }
        return true;
    }
}