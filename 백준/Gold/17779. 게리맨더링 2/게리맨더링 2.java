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
    int[][] map;

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        int total = 0;
        for(int i = 1; i <= N; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 1; j <= N; j ++){
                map[i][j] = Integer.parseInt(st.nextToken());
                total += map[i][j];
            }
        }

        int answer = 987654321;
        for(int x = 1; x <= N; x ++){
            for(int y = 1; y <= N; y ++){
                for(int d1 = 1; d1 <= N; d1 ++){
                    for(int d2 = 1; d2 <= N; d2 ++){
                        if(y - d1 <= 0) continue;
                        if(y + d2 > N) continue;
                        if(x + d1 + d2 > N) continue;

                        int s1 = getSection1(x, y, d1, d2);
                        int s2 = getSection2(x, y, d1, d2);
                        int s3 = getSection3(x, y, d1, d2);
                        int s4 = getSection4(x, y, d1, d2);
                        int s5 = total - s1 - s2 - s3 - s4;

                        int max = Math.max(s1, Math.max(s2, Math.max(s3, Math.max(s4, s5))));
                        int min = Math.min(s1, Math.min(s2, Math.min(s3, Math.min(s4, s5))));

                        answer = Math.min(answer, max - min);
                    }
                }
            }
        }
        System.out.println(answer);
    }

    public int getSection1(int x, int y, int d1, int d2){
        int cnt = 0;
        for(int r = x + d1 - 1, c = y - d1; r > 0; r --, c ++){
            if(c >= y) c = y;
            for(int i = 1; i <= c; i ++){
                cnt += map[r][i];
            }
        }
        return cnt;
    }

    public int getSection2(int x, int y, int d1, int d2){
        int cnt = 0;
        for(int r = x + d2, c = y + d2 + 1; r > 0; r --, c --){
            if(c <= y + 1) c = y + 1;
            for(int i = c; i <= N; i ++){
                cnt += map[r][i];
            }
        }
        return cnt;
    }

    public int getSection3(int x, int y, int d1, int d2){
        int cnt = 0;
        for(int r = x + d1, c = y - d1 - 1; r <= N; r ++, c ++){
            if(c == y - d1 + d2) c = y - d1 + d2 - 1;
            for(int i = 1; i <= c; i ++){
                cnt += map[r][i];
            }
        }
        return cnt;
    }

    public int getSection4(int x, int y, int d1, int d2){
        int cnt = 0;
        for(int r = x + d2 + 1, c = y + d2; r <= N; r ++, c --){
            if(c <= y - d1 + d2) c = y - d1 + d2;
            for(int i = c; i <= N; i ++){
                cnt += map[r][i];
            }
        }
        return cnt;
    }
}
