import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        for(int i = 0; i < tc; i ++){
            int n = Integer.parseInt(br.readLine());
            int[][] scores = new int[n][2];
            for(int j = 0; j < n; j ++){
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int s1 = Integer.parseInt(st.nextToken());
                int s2 = Integer.parseInt(st.nextToken());

                scores[j] = new int[] { s1, s2 };
            }

            Arrays.sort(scores, new Comparator<>(){
                @Override
                public int compare(int[] o1, int[] o2){
                    return o1[0] - o2[0];
                }                
            });

            int answer = 1;
            int max_score = scores[0][1];
            for (int idx1 = 1; idx1 < n; idx1 ++){
                if(max_score > scores[idx1][1]){
                    answer ++;
                    max_score = scores[idx1][1];
                }
            }

            sb.append(answer + "\n");
        }
        System.out.println(sb.toString());
    }
}