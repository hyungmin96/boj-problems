import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int N, K;

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); // 멀티탭의 수
        K = Integer.parseInt(st.nextToken()); // 전자기기의 수

        if(N >= K){
            System.out.println(0);
            return;
        }

        int[] arr = new int[K];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < K; i ++){    
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 초기 멀티탭에 전자기기 꽂기
        int idx = 0;
        HashMap<Integer, Integer> tab = new HashMap<>();
        // 이미 꽂혀있는 전자기기가 아닐경우 새로 꼽기
        while(idx < K && tab.size() < N){
            int num = arr[idx];
            tab.put(num, idx);
            idx ++;
        }

        int answer = 0;
        while(idx < K){
            int last_idx = -1, key = 0;
            // 이미 꽂혀있는 전자기기는 건너뛰기
            if(!tab.containsKey(arr[idx])){
                if(tab.size() < N) continue;
                // 멀티탭에 없는 전자기기를 새로 끼워야할 경우.

                // 1. 다시 사용하지 않는 전자기기일 경우 뽑기
                // 2. 이미 꽂혀있는 전자기기 중 거리가 제일 먼 제품을 뽑는다.
                for(int k : tab.keySet()){
                    int tmp_idx = -1;
                    for(int j = idx + 1; j < K; j ++){
                        if(arr[j] == k){
                            tmp_idx = j;
                            break;
                        }
                    }
                    // k 전자기기를 이후에 사용하지 않는 경우 뽑기
                    if(tmp_idx == -1){
                        key = k;
                        break;
                    }

                    if(last_idx < tmp_idx){
                        last_idx = tmp_idx;
                        key = k;
                    }
                }   
                answer ++;
                tab.remove(key);
            }
            tab.put(arr[idx], idx);
            idx ++;
        }

        System.out.println(answer);
    }
}

