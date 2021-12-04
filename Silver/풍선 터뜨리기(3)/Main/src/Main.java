import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static class Info {
        int order, move;

        public Info(int order, int move) {
            this.order = order;
            this.move = move;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Info[] numbers = new Info[n];
        boolean[] remove = new boolean[n];

        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            numbers[i] = new Info(i + 1, Integer.parseInt(stz.nextToken()));
        }
        solution(n, numbers, remove);
    }

    public static void solution(int n, Info[] numbers, boolean[] remove) {

        int curr = 0;
        int move = numbers[0].move;
        
        remove[curr] = true;
        System.out.println(numbers[0].order);
        
        for(int i = 1; i < n; i ++){
            int cnt = 0;
            move = numbers[curr].move;
            if(move > 0){
                for(int j = curr;; j ++){
                    if (j == numbers.length) j = 0;
                    if (!remove[j]) cnt++;
                    if (cnt == move) {
                        curr = j;
                        break;
                    }
                }
            }else{
                for(int j = curr;; j --){
                    if (j == -1) j = numbers.length - 1;
                    if (!remove[j]) cnt++;
                    if (cnt == Math.abs(move)) {
                        curr = j;
                        break;
                    }
                }
            }
            remove[curr] = true;
            System.out.println(numbers[curr].order);
        }
    }
}
