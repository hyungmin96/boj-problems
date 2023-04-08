import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int length = 0;
        int[] trees = new int[n];
        for(int i = 0; i < n; i ++){
            trees[i] = Integer.parseInt(br.readLine());
            length = Math.max(trees[i], length);
        }

        long answer = Integer.MIN_VALUE;
        for(int len = 1; len <= length; len ++){
            answer = Math.max(answer, check(c, w, len, trees));
        }

        System.out.println(answer);
    }

    public static long check(int c, int w, long len, int[] trees){
        long sum = 0;
        for(int i = 0; i < trees.length; i ++){
            long cut = 0;
            if(trees[i] >= len){
                cut = (trees[i] % len != 0) ? trees[i] / len : trees[i] / len - 1;
                if(w * len * (trees[i] / len) - (c * cut) > 0){
                    sum += w * len * (trees[i] / len) - (c * cut);
                }
            }
        }
        return sum;
    }
}