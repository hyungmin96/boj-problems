import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(stz.nextToken());
        int k = Integer.parseInt(stz.nextToken());

        // a, n, t, c, i
        // 97, 110, 116, 99, 105
        String[] array = new String[n];
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            if (input.substring(0, 4).equals("anta") && input.substring(input.length() - 4).equals("tica")) {
                input = input.substring(4, input.length() - 4);
            } else {
                input = "NONE";
            }

            array[i] = input;
        }

        for (int i = 97; i < 121; i++) {
            dfs(0, i, k, "", array);
        }
        System.out.println((answer <= 0) ? 0 : answer);
    }

    public static void dfs(int depth, int index, int level, String curr, String[] array) {

        if (level < 0)
            return;

        if (depth == level) {
            int sum = 0;

            for (String item : array) {
                boolean isLearned = true;
                if (item.equals("NONE")) {
                    isLearned = false;
                } else {
                    for (int i = 0; i < item.length(); i++) { // 내가 배운 단어들
                        if (!curr.contains(String.valueOf(item.charAt(i)))) { // 배우지 않은 단어가 포함된 단어일 경우
                            isLearned = false;
                            System.out.println(String.valueOf(item.charAt(i)));
                            break;
                        }
                    }
                }

                if (isLearned) sum ++;
            }

            answer = (sum > answer) ? sum : answer;
            return;
        } else {
            curr += (char) (index);
            dfs(depth + 1, index + 1, level, curr, array);
        }
    }
}
