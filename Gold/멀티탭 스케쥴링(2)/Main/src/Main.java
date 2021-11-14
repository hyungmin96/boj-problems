import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(stz.nextToken());
        int k = Integer.parseInt(stz.nextToken());
        int[] array = new int[k];
        boolean[] use = new boolean[101];
        stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            array[i] = Integer.parseInt(stz.nextToken());
        }
        System.out.println(solution(n, array, use));
    }

    public static int solution(int n, int[] array, boolean[] use) {

        int answer = 0;
        ArrayList<Integer> consent = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            if (!use[array[i]]) {
                if (consent.size() == n) {
                     // 사용가능한 콘센트 구멍이 없을 때,
                    // 콘센트에 꽂혀있는 가전제품이 아닐 경우
                    // 즉, 새로운 제품을 사용하기 위해 기존의 제품을 빼야하는 경우
                    ArrayList<Integer> order = new ArrayList<>();
                    answer++;
                    for (int j = i; j < array.length; j++) {
                        // 현재 사용중인 제품과 i번째 이후로 다시 사용될 가전제품을 구함
                        if (use[array[j]] && !order.contains(array[j])) {
                            order.add(array[j]);
                        }
                    }

                    if (order.size() == n) {
                        // 모두 다 사용될 경우
                        int remove = order.get(order.size() - 1);
                        use[remove] = false;
                    } else {
                        // 현재 사용중인 제품중에 다시 사용되지 않는 제품이 있음
                        for (int k = 0; k < use.length; k++) {
                            if (use[k] && !order.contains(k)) {
                                use[k] = false;
                                break;
                            }
                        }
                    }
                    use[array[i]] = true;
                } else {
                    // 콘센트에 n개 이하의 제품이 꽂혀있을 때
                   consent.add(array[i]);
                   use[array[i]] = true;
                }
            }
        }
        return answer;
    }
}
