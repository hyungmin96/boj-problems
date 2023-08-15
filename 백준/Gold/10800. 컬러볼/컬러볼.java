import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int N;

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        ArrayList<int[]> list = new ArrayList<>();
        for(int i = 0; i < N; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int idx = i;
            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());

            list.add(new int[] { idx, color, size });
        }

        Collections.sort(list, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[2] - o2[2];
            }
        });

        int total_sum = 0, pre_idx = 0;
        int[] color_map = new int[N + 1], answer = new int[N + 1];
        for(int i = 0; i < N; i ++){
            int[] cur = list.get(i);
            int[] pre = list.get(pre_idx);

            while(pre[2] < cur[2]){
                total_sum += pre[2];
                color_map[pre[1]] += pre[2];
                pre = list.get(++pre_idx);
            }

            answer[cur[0]] = total_sum - color_map[cur[1]];
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i ++){
            sb.append(answer[i] + "\n");
        }

        System.out.println(sb.toString());
    }
}

