import java.io.*;
import java.util.*;

public class Main {

    static int N, K, P, X, answer = 0;
    static int[][] display = {
            { 1, 1, 1, 0, 1, 1, 1 },
            { 0, 0, 1, 0, 0, 0, 1 },
            { 0, 1, 1, 1, 1, 1, 0 },
            { 0, 1, 1, 1, 0, 1, 1 },
            { 1, 0, 1, 1, 0, 0, 1 },
            { 1, 1, 0, 1, 0, 1, 1 },
            { 1, 1, 0, 1, 1, 1, 1 },
            { 0, 1, 1, 0, 0, 0, 1 },
            { 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 0, 1, 1 }
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); // 사용가능 층 수
        K = Integer.parseInt(st.nextToken()); // 디스플레이 표시 수
        P = Integer.parseInt(st.nextToken()); // 반전 시킬 횟수
        X = Integer.parseInt(st.nextToken()); // 현재 층 수

        int[] x_digit = getDigit(X);
        solution(x_digit);
        System.out.println(answer);
    }

    public static void solution(int[] x_digit){
        for(int i = 1; i <= N; i ++){
            if(i == X) continue;

            int cnt = 0;
            boolean flag = true;
            for(int k = 0; k < K; k ++){
                for(int j = 0; j < 7; j ++){
                    int[] target = getDigit(i);
                    int n1 = x_digit[k];
                    int n2 = target[k];
                    if(display[n1][j] != display[n2][j]) cnt ++;
                    if(cnt > P){
                        flag = false;
                        break;
                    }
                }
                if(!flag) break;
            }
            if(flag) answer ++;
        }
    }

    public static int[] getDigit(int n){
        int[] temp = new int[K];
        for(int i = K - 1; i >= 0; i --){
            temp[i] = n % 10;
            n /= 10;
        }
        return temp;
    }
}