import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Integer>[] vertex;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        vertex = new ArrayList[n + 1];

        int[] nums = new int[n + 1];
        char[] nodes = new char[n + 1];

        for(int i = 1; i <= n; i ++) vertex[i] = new ArrayList<>();
        for(int i = 2; i <= n; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            char c = st.nextToken().charAt(0);
            int num = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());
            nodes[i] = c;
            nums[i] = c == 'W' ? num * -1 : num;
            vertex[next].add(i);
        }

        System.out.println(dfs(1, nums, nodes));
    }

    public static long dfs(int node, int[] nums, char[] nodes){
        long sum = 0;
        for(int next : vertex[node]){
            sum += dfs(next, nums, nodes);
        }

        if(nodes[node] == 'S') {
            return sum + nums[node];
        }else{
            return sum + nums[node] > 0 ? sum + nums[node] : 0;
        }
    }
}