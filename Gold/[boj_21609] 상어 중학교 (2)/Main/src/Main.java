import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static class Group{
        int id, r, c, rainbow, size;
        public Group(int id, int r, int c, int rainbow, int size){
            this.id = id;
            this.r = r;
            this.c = c;
            this.rainbow = rainbow;
            this.size = size;
        }
    }

    private static int N, M;
    private static int[][] board;
    private static int[] dx = new int[] {-1, 0, 1, 0 };
    private static int[] dy = new int[] { 0, 1, 0,-1 };
    private static ArrayList<Group> curList = new ArrayList<>();
    private static ArrayList<int[]> cordsArrayList = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine());
            int index = 0;
            while(st.hasMoreTokens()){
                board[i][index++] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution());
    }

    public static void findBiggestGroup(){
        boolean[][] visited = new boolean[N][N];
        for(int r = 0; r < N; r ++){
            for(int c = 0; c < N; c ++){
                if(board[r][c] > 0 && !visited[r][c]){
                    bfs(new Group(board[r][c], r, c, 0, 1), visited);
                }
            }
        }
    }

    public static void bfs(Group g, boolean[][] visited){
        Queue<Group> que = new LinkedList<>();
        ArrayList<int[]> cords = new ArrayList<>();
        int rainbow = 0;
        que.offer(g);
        cords.add(new int[] { g.r, g.c });
        visited[g.r][g.c] = true;

        while(!que.isEmpty()){
            Group cur = que.poll();
            for(int d = 0; d < dx.length; d ++){
                int nr = cur.r + dx[d];
                int nc = cur.c + dy[d];
                if(nr < 0 || nc < 0 || nr >= N || nc >= N || visited[nr][nc])
                    continue;
                if(board[nr][nc] == 0 || board[nr][nc] == g.id){
                    visited[nr][nc] = true;
                    if(board[nr][nc] == 0) rainbow++;
                    cords.add(new int[] { nr, nc });
                    que.offer(new Group(g.id, nr, nc, rainbow, g.size + 1));
                }
            }
        }

        for(int i = 0; i < cords.size(); i ++){
            if(board[cords.get(i)[0]][cords.get(i)[1]] == 0){
                visited[cords.get(i)[0]][cords.get(i)[1]] = false;
            }
        }

        boolean flag = false;
        if (curList.size() != 0) {
            if (curList.get(0).size < cords.size()) {
                flag = true;
            } else if (curList.get(0).size == cords.size()) {
                if (curList.get(0).rainbow < rainbow) {
                    flag = true;
                } else if (curList.get(0).rainbow == rainbow) {
                    if (curList.get(0).r < g.r) {
                        flag = true;
                    } else if (curList.get(0).r == g.r) {
                        if (curList.get(0).c < g.c) {
                            flag = true;
                        }
                    }
                }
            }
        }else{
            flag = true;
        }

        if(flag){
            curList.clear();
            cordsArrayList.clear();
            curList.add(new Group(g.id, g.r, g.c, rainbow, cords.size()));
            for(int i = 0; i < cords.size(); i ++){
                cordsArrayList.add(cords.get(i));
            }
        }
    }

    public static int solution(){
        int answer = 0;
        while(true){
            findBiggestGroup();
            if(curList.size() == 0 || curList.get(0).size < 2){
                return answer;
            }else{
                answer += deleteGroup();
                setGravity();
                antiRotate();
                setGravity();
            }
        }
    }

    public static void antiRotate(){
        int[][] temp = new int[N][N];
        for(int r = 0; r < N; r ++){
            for(int c = 0; c < N; c ++){
                temp[r][c] = board[c][N-r-1];
            }
        }
        for(int r = 0; r < N; r ++){
            for(int c = 0; c < N; c ++){
                board[r][c] = temp[r][c];
            }
        }
    }

    public static void setGravity(){
        for(int c = 0; c < N; c ++){
            for(int r = N - 2; r >= 0; r --){
                int loc = 0;
                if(board[r][c] != -1){
                    for(int l = r + 1; l < N; l ++){
                        if(board[l][c] != -2) break;
                        loc ++;
                    }
                    if(loc > 0){
                        board[r+loc][c] = board[r][c];
                        board[r][c] = -2;
                    }
                }
            }
        }
    }

    public static int deleteGroup(){
        int score = cordsArrayList.size() * cordsArrayList.size();
        for(int i = 0; i < cordsArrayList.size(); i ++){
            board[cordsArrayList.get(i)[0]][cordsArrayList.get(i)[1]] = -2;
        }
        curList.clear();
        cordsArrayList.clear();
        return score;
    }
}
