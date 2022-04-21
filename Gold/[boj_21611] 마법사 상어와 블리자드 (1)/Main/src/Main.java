import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
public class Main {

    private static class Direction{
        int d, s;
        public Direction(int d, int s){
            this.d = d;
            this.s = s;
        }
    }

    private static class Marble{
        int one, two, three;
        public Marble(int one, int two, int three){
            this.one = one;
            this.two = two;
            this.three = three;
        }
    }

    private static int N;
    private static int[][] board;
    private static int[] dx = new int[] {-1, 1, 0, 0 };
    private static int[] dy = new int[] { 0, 0,-1, 1 };
    private static Marble marble = new Marble(0, 0, 0);

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        int M = Integer.parseInt(st.nextToken());
        Direction[] directions = new Direction[M];

        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int idx = 0;
            while(st.hasMoreTokens()){
                board[i][idx++] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < M; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            directions[i] = new Direction(d, s);
        }
        board[N / 2][N / 2] = -1;
        System.out.println(solution(directions));
    }

    public static int solution(Direction[] directions){
        for(int i = 0; i < directions.length; i ++){
            throwIceParts(directions[i].d, directions[i].s);
            int[] temp = getMarble();
            moveMarble(temp);
            while(explodeMarble()){
                temp = getMarble();
                moveMarble(temp);
            }
            ArrayList<int[]> new_groups = createNewGroup();
            putNewGroups(new_groups);
        }
        return marble.one + (2 * marble.two) + (3 * marble.three);
    }

    public static void putNewGroups(ArrayList<int[]> new_groups){
        int sr = N / 2; // 4
        int sc = N / 2; // 4
        int cnt = 0;
        int d = 0;
        int idx = 0;
        int groups = 0;
        int[] dirs = new int[] { 2, 1, 3, 0 };

        for(int i = 1; i <= N; i ++){
            for(int j = 0; j < 2; j ++){
                for(int k = 0; k < i; k ++){
                    cnt ++;
                    if(cnt == N * N || groups >= new_groups.size()) return;
                    sr += dx[dirs[d % dx.length]];
                    sc += dy[dirs[d % dx.length]];
                    board[sr][sc] = new_groups.get(groups)[idx];
                    if(idx == 1){
                        idx = 0;
                        groups ++;
                    }else{
                        idx ++;
                    }
                }
                d ++;
            }
        }
    }

    public static ArrayList<int[]> createNewGroup(){
        int sr = N / 2; // 4
        int sc = N / 2; // 4
        int cnt = 0;
        int d = 0;
        int[] dirs = new int[] { 2, 1, 3, 0 };
        ArrayList<int[]> tempArr = new ArrayList<>();
        int[] current_temp_arr = new int[] { -2, -2 }; // 개수, 번호

        for(int i = 1; i <= N; i ++){
            for(int j = 0; j < 2; j ++){
                for(int k = 0; k < i; k ++){
                    sr += dx[dirs[d % dx.length]];
                    sc += dy[dirs[d % dx.length]];
                    cnt ++;
                    if(cnt == N * N) return tempArr;
                    if(current_temp_arr[1] != board[sr][sc]){
                        if(!(current_temp_arr[1] == 0 || current_temp_arr[1] == -2)){
                            tempArr.add(new int[] { current_temp_arr[0], current_temp_arr[1] });
                        }
                        current_temp_arr[0] = 1;
                        current_temp_arr[1] = board[sr][sc];
                    }else{
                        current_temp_arr[0] ++;
                    }
                }
                d ++;
            }
        }
        return tempArr;
    }

    public static boolean explodeMarble(){
        int sr = N / 2;
        int sc = N / 2;
        int d = 0;
        int cnt = 0;
        int current_temp = -1;
        int[] dirs = new int[] { 2, 1, 3, 0 };
        ArrayList<int[]> tempArr = new ArrayList<>();
        boolean flag = false;

        for(int i = 1; i <= N; i ++){
            for(int j = 0; j < 2; j ++){
                for(int k = 0; k < i; k ++){
                    sr += dx[dirs[d % dx.length]];
                    sc += dy[dirs[d % dx.length]];
                    cnt ++;
                    if(cnt == N * N)  return flag;
                    if(board[sr][sc] != current_temp){
                        current_temp = board[sr][sc];
                        if(tempArr.size() < 4){
                            tempArr.clear();
                            tempArr.add(new int[] { sr, sc });
                        }else{
                            flag = true;
                            for(int l = tempArr.size() -1; l >= 0; l --){
                                int[] del = tempArr.get(l);
                                if(board[del[0]][del[1]] == 1) marble.one ++;
                                else if(board[del[0]][del[1]] == 2) marble.two ++;
                                else if(board[del[0]][del[1]] == 3) marble.three ++;
                                board[del[0]][del[1]] = 0;
                                tempArr.remove(l);
                            }
                        }
                    }else{
                        tempArr.add(new int[] { sr, sc });
                    }
                }
                d ++;
            }
        }
        return flag;
    }

    public static int[] getMarble(){
        int sr = N / 2; // 4
        int sc = N / 2; // 4
        int cnt = 0;
        int c = 0;
        int d = 0;
        int[] dirs = new int[] { 2, 1, 3, 0 };
        int[] temp = new int[N * N];
        
        for(int i = 1; i <= N; i ++){
            for(int j = 0; j < 2; j ++){
                for(int k = 0; k < i; k ++){
                    sr += dx[dirs[d % dx.length]];
                    sc += dy[dirs[d % dx.length]];
                    cnt ++;
                    if(cnt == N * N) return temp;
                    if(board[sr][sc] != 0){
                        temp[c++] = board[sr][sc];
                    }
                }
                d ++;
            }
        }
        return temp;
    }

    public static void moveMarble(int[] temp){
        int sr = N / 2; // 4
        int sc = N / 2; // 4
        int cnt = 0;
        int d = 0;
        int[] dirs = new int[] { 2, 1, 3, 0 };
        for(int i = 1; i <= N; i ++){
            for(int j = 0; j < 2; j ++){
                for(int k = 0; k < i; k ++){
                    sr += dx[dirs[d % dx.length]];
                    sc += dy[dirs[d % dx.length]];
                    board[sr][sc] = temp[cnt++];
                    if(cnt == (N * N) - 1) return;
                }
                d ++;
            }
        }
    }

    public static void throwIceParts(int d, int s){
        int nr = N / 2;
        int nc = N / 2;
        for(int i = 0; i < s; i ++){
            nr += dx[d];
            nc += dy[d];
            board[nr][nc] = 0;
        }
    }
}
