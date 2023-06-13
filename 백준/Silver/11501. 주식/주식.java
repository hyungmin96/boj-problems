import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for(int i = 0; i < tc; i ++){
            int n = Integer.parseInt(br.readLine());

            int[][] arr = new int[n][2];
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; j ++){
                arr[j] = new int[] { Integer.parseInt(st.nextToken()), j };
            }

            long answer = Math.max(solution(arr), 0);
            sb.append(answer + "\n");
        }

        System.out.println(sb.toString());
    }

    public static long solution(int[][] arr){
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<>(){
            @Override
            public int compare(int[] o1, int[] o2){
                if(o2[0] != o1[0])
                    return o2[0] - o1[0];

                return o1[1] - o2[1];
            }
        });

        for(int i = 0; i < arr.length; i ++){
            pq.offer(new int[] { arr[i][0], arr[i][1] });
        }

        int idx = 0;
        long answer = 0;
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            if(cur[1] < idx) continue;
            for(int i = idx; i < cur[1]; i ++){
                answer += cur[0] - arr[i][0];
            }
            idx = cur[1] + 1;
        }

        return answer;
    }
}