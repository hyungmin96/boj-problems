import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Long> arr = new ArrayList<>();

        if (n > 1022)
            System.out.println(-1);
        else {
            for (Long i = 0L; i < 10; i++)
                solution(0, i, arr);

            Collections.sort(arr);
            System.out.println(arr.get(n));
        }
    }

    public static void solution(int depth, Long curr, ArrayList<Long> arr) {

        if (depth > 10)
            return;
        else {
            arr.add(curr);
            for (int i = 0; i < curr % 10; i++) {
                solution(depth + 1, (curr * 10) + i, arr);
            }
        }
    }
}
