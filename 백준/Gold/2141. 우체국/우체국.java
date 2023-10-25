import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException{
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {
    
    int N;
    int[][] house;

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        house = new int[N][2];

        long tmp = 0;
        for(int i = 0; i < N; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int pos = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            house[i][0] = pos;
            house[i][1] = p;
            tmp += p;
        }

        Arrays.sort(house, new Comparator<>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[0] - o2[0];
            }
        });
        System.out.println(solve(tmp));
    }

    public int solve(long tmp){
        int pos = Integer.MAX_VALUE;
        long cnt = 0;
        for(int i = 0; i < N; i ++){
            cnt += house[i][1];
            if((tmp + 1) / 2 <= cnt){
                return house[i][0];
            }
        }

        return pos;
    }
}

