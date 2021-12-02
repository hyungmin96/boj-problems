import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {

    static class Position implements Comparable<Position>{
        int left, right;
        public Position(int left, int right){
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Main.Position o) {
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
        System.out.println(solution(n, array));
    }

    public static int solution(int n, Position[] array){
        int[] cross = new int[array.length];

        for(int i = 0; i < array.length; i ++){
            cross[i] = 1;
            for(int j = 0; j < i; j ++){
                if(array[i].right > array[j].right){
                    cross[i] = Math.max(cross[i], cross[j] + 1);
                }
            }
        }

        return n - Arrays.stream(cross).max().getAsInt();
    }
}
