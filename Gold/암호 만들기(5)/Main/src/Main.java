import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");
        
        int n = Integer.parseInt(stz.nextToken());
        int k = Integer.parseInt(stz.nextToken());

        String[] array = new String[k];
        boolean[] visited = new boolean[k];

        stz = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < k; i ++){
            array[i] = stz.nextToken();
        }

        Arrays.sort(array);
        solution(0, n, 0, "", 0, 0, array, visited);
    }

    public static void solution(int depth, int level, int index, String curr, int cnt1, int cnt2, String[] array, boolean[] visited){

        if(depth == level){
            if(cnt1 >= 1 && cnt2 >= 2)
                System.out.println(curr);
            curr = "";
            cnt1 = 0;
            cnt2 = 0;
            return;
        } else {
            for (int i = index; i < array.length; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    if (array[i].contains("a") || array[i].contains("e") || array[i].contains("i")
                            || array[i].contains("o") || array[i].contains("u"))
                        cnt1++;
                    else
                        cnt2++;

                    solution(depth + 1, level, i, curr + array[i], cnt1, cnt2, array, visited);
                    
                    if (array[i].contains("a") || array[i].contains("e") || array[i].contains("i")
                            || array[i].contains("o") || array[i].contains("u"))
                        cnt1--;
                    else
                        cnt2--;
                    visited[i] = false;
                }
            }
        }
    }
}
