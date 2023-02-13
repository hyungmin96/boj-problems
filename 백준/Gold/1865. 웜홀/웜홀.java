import java.io.*;
import java.util.*;

public class Main {

    static int N, M, W;
    static ArrayList<int[]>[] vertex;
    static int[] dis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        for(int i = 0; i < tc; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            dis = new int[N + 1];
            vertex = new ArrayList[N + 1];

            for(int j = 1; j <= N; j ++) vertex[j] = new ArrayList<>();
            for(int j = 0; j < M; j ++){
                st = new StringTokenizer(br.readLine(), " ");
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                vertex[start].add(new int[] { end, cost });
                vertex[end].add(new int[] { start, cost });
            }

            for(int j = 0; j < W; j ++){
                st = new StringTokenizer(br.readLine(), " ");
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                vertex[start].add(new int[] { end, - cost });
            }

            boolean isCycle = false;
            for(int k = 1; k <= N; k ++){
                if(bellmanFord(k)){
                    isCycle = true;
                    sb.append("YES\n");
                    break;
                }
            }

            if(!isCycle){
                sb.append("NO\n");
            }
        }
        System.out.println(sb.toString());
    }

    public static boolean bellmanFord(int start){
        Arrays.fill(dis, 987654321);
        dis[start] = 0;
        boolean isUpdate = false;
        for(int i = 1; i < N; i ++){
            isUpdate = false;
            for(int j = 1; j <= N; j ++){
                for(int[] cur : vertex[j]){
                    int next = cur[0];
                    int cost = cur[1];
                    if(dis[j] != 987654321 && dis[next] > dis[j] + cost){
                        dis[next] = dis[j] + cost;
                        isUpdate = true;
                    }
                }
            }

            if(!isUpdate){
                break;
            }
        }

        if(isUpdate){
            for(int i = 1; i <= N; i ++){
                for(int[] cur : vertex[i]){
                    int next = cur[0];
                    int cost = cur[1];
                    if(dis[i] != 987654321 && dis[next] > dis[i] + cost)
                    return true;
                }
            }
        }

        return false;
    }
}