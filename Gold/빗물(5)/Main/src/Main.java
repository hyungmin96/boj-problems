import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");

        int height = Integer.parseInt(stz.nextToken());
        int width = Integer.parseInt(stz.nextToken());

        int[] block = new int[width];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < width; i++) {
            block[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(height, block));
    }

    public static int solution(int height, int[] block) {

        int answer = 0;
        int leftPilarHeight = -1;

        for (int i = 1; i < block.length - 1; i++) {
            int selectPilarHeight = 0;
            int rightPilarHeight = -1;

            leftPilarHeight = (leftPilarHeight > block[i - 1]) ? leftPilarHeight : block[i - 1];
            for (int j = i + 1; j < block.length; j++) {
                // i 번째 기준 오른쪽에서 가장 높은 기둥의 높이를 저장
                rightPilarHeight = (rightPilarHeight < block[j]) ? block[j] : rightPilarHeight;
            }
            selectPilarHeight = (rightPilarHeight < leftPilarHeight) ? rightPilarHeight : leftPilarHeight;
            
            if (selectPilarHeight > 0 && selectPilarHeight - block[i] > 0)
                answer += selectPilarHeight - block[i];
        }

        return (answer < 1) ? 0 : answer;
    }
}
