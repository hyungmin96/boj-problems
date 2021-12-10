import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {

    static class Score implements Comparable<Score>{
        int report, interview;
        public Score(int report, int interview){
            this.report = report;
            this.interview = interview;
        }
        @Override
        public int compareTo(Main.Score o) {
            if(o.report == this.report) 
                return o.interview - this.interview;
            return this.report - o.report;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        for(int i = 0; i < test; i ++){
            int n = Integer.parseInt(br.readLine());
            Score[] arr = new Score[n];
            for(int j = 0; j < n; j ++){
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                arr[j] = new Score(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            Arrays.sort(arr);
            System.out.println(solution(arr));
        }
    }

    public static int solution(Score[] arr){
        int answer = 1;
        int compare = arr[0].interview;

        for(int i = 1; i < arr.length; i ++){
            if(compare > arr[i].interview){
                answer ++;
                compare = arr[i].interview;
            }
        }

        return answer;
    }
}
