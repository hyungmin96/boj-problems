import java.io.*;
import java.util.*;

public class Main {

    static int N, W, leaf;
    static int[] parents;
    static ArrayList<Integer>[] graph;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        parents = new int[N + 1];

        for(int i = 1; i <= N; i ++) graph[i] = new ArrayList<>();
        for(int i = 0; i < N - 1; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            graph[start].add(end);
            graph[end].add(start);
        }

        for(int i = 2; i <= N; i ++){
            if(graph[i].size() == 1){
                leaf ++;
            }
        }
        System.out.println(String.format("%.10f", (double)W/leaf));
    }
}