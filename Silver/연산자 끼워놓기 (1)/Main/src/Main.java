import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static ArrayList<String> items = new ArrayList<>();
    private static int max = Integer.MIN_VALUE;
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");
        int[] array = new int[n];
        int[] operations = new int[4];
        boolean[] visited = new boolean[n];

        for (int i = 0; i < array.length; i++)
            array[i] = Integer.parseInt(stz.nextToken());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 4; i++)
            operations[i] = Integer.parseInt(st.nextToken());

        int level = Arrays.stream(operations).sum();

        visited[0] = true;
        dfs(0, level, array, operations, visited);
        System.out.println(max);
        System.out.println(min);
    }

    public static void dfs(int depth, int level, int[] array, int[] operations, boolean[] visited) {
        if (depth == level) {
            int sum = array[0];
            int index = 1;
            for (String item : items) {
                if(item.equals("+")){
                    sum += array[index ++];
                }else if(item.equals("-")){
                    sum -= array[index ++];
                }else if(item.equals("*")){
                    sum *= array[index ++];
                }else if(item.equals("/")){
                    sum /= array[index ++];
                }
            }
            max = (sum > max) ? sum : max;
            min = (sum < min) ? sum : min;
            return;
        } else {
            for(int i = 0; i < operations.length; i ++){
                if(operations[i] > 0){
                    operations[i] -= 1;
                    if(i == 0){
                        items.add("+");
                    }
                    else if(i == 1){
                        items.add("-");
                    }
                    else if(i == 2){
                        items.add("*");
                    }
                    else if(i == 3){
                        items.add("/");
                    }

                    dfs(depth + 1, level, array, operations, visited);
                    operations[i] += 1;
                    items.remove(items.size() - 1);
                }
            }
        }
    }
}
