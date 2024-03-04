import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int N, T, min = 0, max = 0;
    int[][] arr;

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        arr = new int[N][2];

        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine(), " ");

            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            min += l;
            max += r;
            arr[i] = new int[] { l, r };
        }
        solve();
    }

    public void solve() {
        int l = 1, r = 1000000000;
        if(min > T || max < T){
            System.out.println(-1);
            return;
        }

        int answer = 987654321;
        while(l <= r){
            int mid = (r + l) / 2;
            if(check(mid)){
                r = mid - 1;
                answer = Math.min(answer, mid);
            }else{
                l = mid + 1;
            }
        }

        System.out.println(answer);
    }

    public boolean check(int mid){
        // 남은 술통의 양
        int rest = T - min;
        for(int i = 0; i < N; i ++){
            if(arr[i][0] > mid){
                // 아무리 적게 먹어도 S보다 많이 마셔야하는 인원이 있는 경우
                return false;
            }
            // 최대주량과 S 중 적은 값
            int p_max = Math.min(arr[i][1], mid) - arr[i][0];
            rest -= p_max;
        }
        return rest <= 0;
    }
}


