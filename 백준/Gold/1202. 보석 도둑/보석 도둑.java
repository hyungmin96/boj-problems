import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int N, K;
    int[] bags;
    int[][] jewelry;
    long answer = 0;
    
    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bags = new int[K];
        jewelry = new int[N][2];
        
        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine(), " ");

            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            jewelry[i] = new int[] { w, v };
        }

        for(int i = 0; i < K; i ++){
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(jewelry, new Comparator<>(){
            @Override
            public int compare(int[] o1, int[] o2){
                if(o1[0] == o2[0]) return o2[1] - o1[1];
                return o1[0] - o2[0];
            }
        });
        Arrays.sort(bags);

        int j_idx = 0;
        Long answer = 0L;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
        for(int i = 0; i < K; i ++){
            while(j_idx < N && bags[i] >= jewelry[j_idx][0]){
                pq.offer(jewelry[j_idx][1]);
                j_idx ++;
            }
            if(!pq.isEmpty()){
                answer += pq.poll();
            }
        }

        System.out.println(answer);
    }
}

// 보석 N개 <= 300,000
// 가방 K개 <= 300,000

// 보석의 가치와 무게 <= 1,000,000
// 가방의 최대 수용 무게 <= 1억

// 가방에는 최대 1개의 보석만 담을수있다.

// 이분탐색 + 우선순위 큐
// 가방을 오름차순하여 이분탐색을 통해 해당 가방에 담을 수 있는 가장 큰 가치의 보석 담기
// - 가방의 수용가능한 무게보다 훨씬 작지만 큰 가치의 보석일 경우 무게를 기준으로 탐색하는게 무의미함
// 
