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

        int[][] arr = new int[N][2];
        for(int i = 0; i < N; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int pos = Integer.parseInt(st.nextToken());
            int population = Integer.parseInt(st.nextToken());

            arr[i] = new int[] { pos, population };
        }

        Arrays.sort(arr, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[0] - o2[0];
            }
        });

        long tmp = 0;
        long[] sum = new long[N];
        for(int i = 0; i < arr.length; i ++){
            tmp += arr[i][1];
            sum[i] = tmp;
        }

        System.out.println(binarySearch(sum, arr));
    }

    public long binarySearch(long[] sum, int[][] arr){
        long ret = 987654321;
        int l = 0, r = arr.length - 1;
        while(l <= r){
            int mid = (r + l) / 2;
            long p_l = sum[mid];
            long p_r = sum[arr.length - 1] - sum[mid];

            if(p_l >= p_r){
                ret = Math.min(ret, arr[mid][0]);
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }

        return ret;
    }
}