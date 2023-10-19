import java.io.*;
import java.util.*;

public class Main {

    static boolean flag = false;
    static ArrayList<int[]> days;
    static boolean[][] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        days = new ArrayList<>();
        check = new boolean[n][10];
        for(int i = 0; i < n; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int num = Integer.parseInt(st.nextToken());
            int[] temp = new int[num];
            for(int j = 0; j < num; j ++){
                temp[j] = Integer.parseInt(st.nextToken());
            }
            
            days.add(temp);
        }

        dfs(0, new int[n]);
        if(!flag) System.out.println(-1);
    }

    public static void dfs(int depth, int[] rice){
        if(flag) return;
        if(days.size() == depth){
            flag = true;
            StringBuilder sb = new StringBuilder();
            for(int i : rice) sb.append(i + "\n");
            System.out.println(sb.toString());
            return;
        }

        for(int i = 0; i < days.get(depth).length; i ++){
            boolean find = false;
            if(depth > 0 && rice[depth - 1] == days.get(depth)[i]) continue;
            if(check[depth][days.get(depth)[i]]) continue;
            check[depth][days.get(depth)[i]] = true;
            rice[depth] = days.get(depth)[i];
            find = true;
            dfs(depth + 1, rice);
        }
    }
}