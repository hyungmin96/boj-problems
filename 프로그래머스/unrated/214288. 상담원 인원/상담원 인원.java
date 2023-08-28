import java.util.*;
class Solution {
    
    HashMap<Integer, ArrayList<int[]>> map = new HashMap<>();
    public int solution(int k, int n, int[][] reqs) {
        int answer = 0;
        for(int i = 0; i < reqs.length; i ++){
            int s = reqs[i][0];
            int e = reqs[i][1];
            int t = reqs[i][2];
            
            if(!map.containsKey(t))
                map.put(t, new ArrayList<>());
        
            map.get(t).add(new int[] { s, e });
        }
        
        int[] arr = new int[k];
        Arrays.fill(arr, 1);
        n -= k;
        
        answer = dfs(n, 0, 0, arr);
        return answer;
    }
    
    public int getWaitTime(int[] arr){
        int total_wait_time = 0;
        for(int i = 0; i < arr.length; i ++){
            // 작업이 먼저 종료되는 상담부터 꺼내기
            PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
                @Override
                public int compare(int[] o1, int[] o2){
                    if(o1[1] == o2[1]) return o1[0] - o2[0];
                    return o1[1] - o2[1];
                }
            });
            
            if(!map.containsKey(i + 1)) continue;
            for(int[] item : map.get(i + 1)){
                int wait = 0;
                int start = item[0];
                int end = start + item[1];
                if(!pq.isEmpty()){
                    if(start < pq.peek()[1] && pq.size() + 1 <= arr[i]){
                        // 다른 상담사가 있고
                        // 다음 상담시작시간이 되어도 상담이 종료되지 않았을 경우
                    }else if(start < pq.peek()[1] && pq.size() == arr[i]){
                        // 대기자 발생
                        wait = (pq.peek()[1] - start);
                        total_wait_time += wait;
                        pq.poll();
                    }else if(start >= pq.peek()[1]){
                        pq.poll();
                    }
                }
                pq.offer(new int[] { wait + start, wait + end });
            }
        }
        return total_wait_time;
    }
    
    public int dfs(int n, int depth, int idx, int[] arr){
        if(depth == n){
            return getWaitTime(arr);
        }
        int ret = 987654321;
        for(int i = idx; i < arr.length; i ++){
            arr[i] ++;
            ret = Math.min(ret, dfs(n, depth + 1, i, arr));
            arr[i] --;
        }

        return ret;
    }
}