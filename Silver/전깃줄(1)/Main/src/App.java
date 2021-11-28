import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
public class App {

    static class Position implements Comparable<Position>{
        int left, right;
        public Position(int left, int right){
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(App.Position o) {
            return this.left - o.left;
        }
    }

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Position[] array = new Position[n];
        for(int i = 0; i < n; i ++){
            StringTokenizer stz = new StringTokenizer(br.readLine(), " ");
            array[i] = new Position(Integer.parseInt(stz.nextToken()), Integer.parseInt(stz.nextToken()));
        }

        Arrays.sort(array);
        System.out.println(solution(array));
    }

    public static int solution(Position[] array){
        int answer = 0;
        int[] cross = new int[array.length];

        for(int i = 0; i < array.length; i ++){
            for(int j = i + 1; j < array.length; j ++){
                if(array[j].right < array[i].right){
                    cross[i] += 1;
                }
            }
        }

        return answer;
    }
}
