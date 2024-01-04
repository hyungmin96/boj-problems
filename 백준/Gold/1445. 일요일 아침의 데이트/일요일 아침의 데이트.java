import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {
    
    int N, M;
    char[][] map;
    int[] s;
    int[][] v1, v2, dirs = {{-1,0},{0,1},{1,0},{0,-1}};
    final int INF = 987654321;

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        v1 = new int[N][M];
        v2 = new int[N][M];
        map = new char[N][M];

        for(int i = 0; i < N; i ++){
            Arrays.fill(v1[i], INF);
            Arrays.fill(v2[i], INF);
        }

        for(int i = 0; i < N; i ++){
            String str = br.readLine();
            for(int j = 0; j < M; j ++){
                map[i][j] = str.charAt(j);
                if(str.charAt(j) == 'S'){
                    s = new int[] { i, j };
                }
            }
        }

        int[] answer = bfs();
        System.out.println(answer[0] + " " + answer[1]);
    }

    public int[] bfs(){
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<>(){
            @Override
            public int compare(int[] o1, int[] o2){
                if(o1[2] == o2[2]) return o1[3] - o2[3];
                return o1[2] - o2[2];
            }
        });

        pq.offer(new int[] { s[0], s[1], 0, 0 });
        v1[s[0]][s[1]] = 0;
        v2[s[0]][s[1]] = 0;

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            for(int d = 0; d < 4; d ++){

                int passGarbage = cur[2];
                int closeGarbage = cur[3];

                int nr = cur[0] + dirs[d][0];
                int nc = cur[1] + dirs[d][1];
                if(isOutRange(nr, nc)) continue;
                
                if(map[nr][nc] == 'F'){
                    return new int[] { cur[2], cur[3] };
                }

                if(map[nr][nc] == 'g'){
                    // 다음 방문 격자에 쓰레기가 놓여있는 경우
                    passGarbage ++;
                }else if(map[nr][nc] == '.'){
                    // 다음 방문 칸이 깨끗한 칸일 경우
                    // 인접한 칸에 쓰레기가 있는 지 탐색
                    if(checkCloseGarbage(nr, nc)){
                        // 쓰레기가 있는 경우
                        closeGarbage ++;
                    }
                }

                if(passGarbage > v1[nr][nc]) continue;
                if(passGarbage < v1[nr][nc]){
                    // 이전 탐색에서 쓰레기를 통고한 횟수보다 적은 탐색분기가 해당 격자를 방문한 경우
                    v1[nr][nc] = passGarbage;
                    v2[nr][nc] = closeGarbage;
                }else if(passGarbage == v1[nr][nc]){
                    // 쓰레기를 통과한 횟수가 같은 경우 지나는 횟수를 비교
                    if(closeGarbage >= v2[nr][nc]) continue;
                    v2[nr][nc] = closeGarbage;
                }

                pq.offer(new int[] { nr, nc, passGarbage, closeGarbage });
            }
        }
        return new int[] { -1, -1 };
    }

    public boolean checkCloseGarbage(int r, int c){
        for(int d = 0; d < 4; d ++){
            int nr = r + dirs[d][0];
            int nc = c + dirs[d][1];
            if(isOutRange(nr, nc)) continue;
            if(map[nr][nc] == 'g') return true;
        }
        return false;
    }

    public boolean isOutRange(int nr, int nc){
        return (nr < 0 || nc < 0 || nr >= N || nc >= M);
    }
}
