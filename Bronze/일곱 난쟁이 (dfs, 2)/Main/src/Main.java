import java.io.*;
import java.util.Arrays;

public class Main {

    static int[] array;
    static int[] output;
    static boolean[] visited;
    static boolean flag = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        array = new int[9];
        output = new int[7];
        visited = new boolean[9];

        for (int i = 0; i < 9; i++) {
            array[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(array);
        dfs(0, 0, 0);
    }

    public static void dfs(int idx, int depth, int curr) {
        if(flag) return;

        if(depth == 7){
            if(curr == 100){
                flag = true;
                for(int i = 0; i < 7; i ++) {
                    System.out.println(output[i]);
                }
            }
        }else{
            for(int i = idx; i < 9; i ++){
                if(!visited[i]){
                    visited[i] = true;
                    output[depth] = array[i];
                    dfs(i, depth + 1, curr + array[i]);
                    visited[i] = false;
                    output[depth] = 0;
                }
            }
        }
    }
}
