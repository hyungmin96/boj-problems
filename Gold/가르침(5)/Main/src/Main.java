import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(stz.nextToken());
        int k = Integer.parseInt(stz.nextToken());

        // a, n, t, c, i
        // 97, 110, 116, 99, 105
        String[] array = new String[n];
        boolean[] visited = new boolean[26];
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            if (input.substring(0, 4).equals("anta") && input.substring(input.length() - 4).equals("tica")) {
                input = input.substring(4, input.length() - 4);
            } else {
                input = "NONE";
            }

            array[i] = input;
        }

        visited[0] = true;
        visited[2] = true;
        visited[8] = true;
        visited[13] = true;
        visited[19] = true;

        dfs(0, 0, k - 5, "antic", array, visited);
        System.out.println((answer <= 0) ? 0 : answer);

    }

    public static void dfs(int depth, int index, int level, String curr, String[] array, boolean[] visited) {

        if(level < 0) return;

        if(depth == level){
            int sum = 0;
            for(String item : array){
                boolean isLearned = true;
                for(char c : item.toCharArray()){
                    if(!curr.contains(String.valueOf(c))){
                        isLearned = false;
                        break;
                    }
                }
                if(isLearned) sum++;
            }
            answer = (sum > answer) ? sum : answer;
            return;
        }else{
            for(int i = index; i < 26; i ++){
                if(!visited[i]){
                    visited[i] = true;
                    dfs(depth + 1, i + 1, level, curr + (char)(i + 97), array, visited);
                    visited[i] = false;
                }
            }
        }

    }
}
