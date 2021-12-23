import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(br.readLine());
        int t = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());

        System.out.println(solution(a, t, c));
    }

    public static int solution(int a, int t, int c) {
        int answer = 0;
        int cnt = 1;
        int target = 0;
        String str = (c == 0) ? "뻔" : "데기";

        ArrayList<String> arrList = new ArrayList<String>();
        arrList.add("뻔");
        arrList.add("데기");
        arrList.add("뻔");
        arrList.add("데기");
        arrList.add("뻔");
        arrList.add("뻔");
        arrList.add("데기");
        arrList.add("데기");

        while (true) {
            if (cnt != 1) {
                arrList.add(4, "뻔");
                arrList.add(arrList.size() - 1, "데기");
            }

            for (int i = 0; i < arrList.size(); i++) {
                if (arrList.get(i).equals(str)) {
                    target++;
                }
                if (answer == a)
                    answer = 0;
                if (target == t)
                    return answer;
                answer++;
            }
            cnt++;
        }
    }
}
