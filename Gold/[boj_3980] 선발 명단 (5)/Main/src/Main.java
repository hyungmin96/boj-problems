import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
public class Main {

    private static int N;
    private static ArrayList<ArrayList<int[]>> arrList = new ArrayList<>();
    private static StringBuilder sb = new StringBuilder();
    private static int answer = 0;
    private static boolean[] visited = new boolean[11];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i ++){
            answer = 0;
            visited = new boolean[11];
            arrList.clear();
            for(int l = 0; l < 11; l ++){
                arrList.add(new ArrayList<>());
            }
            for(int j = 0; j < 11; j ++){
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int idx = 0;
                while(st.hasMoreTokens()){
                    int in = Integer.parseInt(st.nextToken());
                    if(in > 0)
                        arrList.get(idx).add(new int[] { j, in });
                    idx ++;
                }
            }
            solution(0, 0);
            sb.append(answer).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void solution(int depth, int stat){
        if(depth == arrList.size()){
            answer = (answer > stat) ? answer : stat;
            return;
        }

        int temp = stat;
        for(int i = 0; i < arrList.get(depth).size(); i ++){
            if(!visited[arrList.get(depth).get(i)[0]]){
                stat += arrList.get(depth).get(i)[1];
                visited[arrList.get(depth).get(i)[0]] = true;
                solution(depth + 1, stat);
                visited[arrList.get(depth).get(i)[0]] = false;
                stat = temp;
            }
        }
    }
}
