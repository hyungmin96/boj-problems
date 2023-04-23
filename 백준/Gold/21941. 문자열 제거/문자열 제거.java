import java.io.*;
import java.util.*;

public class Main {

    public static class Pair{
        int score;
        String str;
        public Pair(String str, int score) { this.str = str; this.score = score; }
    }
    static int N;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int m = Integer.parseInt(br.readLine());

        Pair[] p = new Pair[m];
        int[][] dp = new int[2][str.length() + 1];
        ArrayList<int[]>[] scores = new ArrayList[str.length() + 1];

        for(int i = 1; i <= str.length(); i ++) {
            dp[0][i] = dp[0][i - 1] + 1;
            scores[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String t = st.nextToken();
            int s = Integer.parseInt(st.nextToken());
            p[i] = new Pair(t, s);

            int idx = 0;
            while(idx < str.length()){
                int cur = str.indexOf(t, idx);
                if(cur != -1){
                    idx = cur + 1;
                    scores[cur + t.length()].add(new int[] { t.length(), s });
                }else break;
            }
        }

        int i = 1;
        for(int j = 1; j <= str.length(); j ++){
            dp[i][j] = Math.max(dp[i][j - 1] + 1, dp[i - 1][j]);
            for(int[] s : scores[j]){
                int add = 0;
                if(j - s[0] >= 0){
                    add = Math.max(add, dp[i][j - s[0]] + s[1]);
                }
                dp[i][j] = Math.max(dp[i][j], add);
            }
        }

        System.out.println(dp[1][str.length()]);
    }
}