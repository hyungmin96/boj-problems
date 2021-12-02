import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {

    static class Charge implements Comparable<Charge>{
        int price, fee;
        public Charge(int price, int fee){
            this.price = price;
            this.fee = fee;
        }
        @Override
        public int compareTo(Main.Charge o) {
            if(this.price == o.price) return this.fee - o.fee;
            return this.price - o.price;
        }
    }

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Charge[] array = new Charge[n];

        for(int i = 0; i < n; i ++){
            StringTokenizer stz = new StringTokenizer(br.readLine(), " ");
            array[i] = new Charge(Integer.parseInt(stz.nextToken()), Integer.parseInt(stz.nextToken()));
        }

        Arrays.sort(array);
        System.out.println(solution(array));
    }

    public static int solution(Charge[] array){
        
        int answer = Integer.MIN_VALUE;
        int index = -1;

        for (int i = 0; i < array.length; i++) {
            int temp = array[i].price - array[i].fee;
            for (int j = i + 1; j < array.length; j++) {
                if (array[i].price - array[j].fee > 0) {
                    temp += (array[i].price - array[j].fee);
                }
            }
            if (temp > 0 && temp > answer) {
                answer = temp;
                index = i;
            }
        }

        return (index == - 1) ? 0 : array[index].price;
    }
}
