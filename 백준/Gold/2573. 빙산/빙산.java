import java.io.*;
import java.util.*;

public class Main {

    static int[][] dirs = {
        { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        ArrayList<int[]> list = new ArrayList<>();

        for(int i = 0; i < n; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < m; j ++){
                int num = Integer.parseInt(st.nextToken());
                if(num > 0) list.add(new int[] { i, j });
                map[i][j] = num;
            }
        }

        System.out.println(solution(n, m, list, map));
    }

    public static int solution(int n, int m, ArrayList<int[]> list, int[][] map){
        int answer = 0;
        boolean flag = false;
        while(list.size() > 0){
            flag = check(n, m, list, map);
            if(flag) break;
            answer ++;
            int[] minus = new int[list.size()];
            for (int idx1 = list.size() - 1; idx1 >= 0; idx1 --){
                int cnt = 0;
                for(int[] d : dirs){
                    int nr = list.get(idx1)[0] + d[0];
                    int nc = list.get(idx1)[1] + d[1];
                    if(nr < 0 || nc < 0 || nr >= n || nc >= m) continue;
                    if(map[nr][nc] > 0) continue;
                    cnt ++;
                }
                minus[idx1] = cnt;
            }

            for(int idx2 = list.size() - 1; idx2 >= 0; idx2 --){
                if(map[list.get(idx2)[0]][list.get(idx2)[1]] - minus[idx2] <= 0){
                    map[list.get(idx2)[0]][list.get(idx2)[1]] = 0;
                    list.remove(idx2);
                }else{
                    map[list.get(idx2)[0]][list.get(idx2)[1]] -= minus[idx2];
                }
            }
        }
        return (flag) ? answer : 0;
    }

    public static boolean check(int n, int m, ArrayList<int[]> list, int[][] map){
        int cnt = 0;
        boolean[][] check = new boolean[n][m];
        Queue<int[]> que = new LinkedList<>();
        for(int[] l : list){

            if(!check[l[0]][l[1]]) cnt ++;
            que.offer(new int[] { l[0], l[1] });
            check[l[0]][l[1]] = true;

            while(!que.isEmpty()){
                int[] cur = que.poll();
                for(int[] d : dirs){
                    int nr = cur[0] + d[0];
                    int nc = cur[1] + d[1];
                    if(nr < 0 || nc < 0 || nr >= n || nc >= m || check[nr][nc]) continue;
                    if(map[nr][nc] <= 0) continue;

                    check[nr][nc] = true;
                    que.offer(new int[] { nr, nc });
                }
            }
        }

        return cnt >= 2;
    }
}