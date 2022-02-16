import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int R, C, M;
    private static Shark[][] board;
    private static int[] dx = new int[] { -1, 1, 0, 0 };
    private static int[] dy = new int[] { 0, 0, 1, -1 };

    public static class Shark implements Comparable<Shark> {
        int r, c, s, d, z;

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }

        public void setR(int r){
            this.r = r;
        }

        public void setC(int c){
            this.c = c;
        }

        public void setD(int d){
            this.d = d;
        }

        @Override
        public int compareTo(Shark o) {
            if (this.r != o.r)
                return this.r - o.r;
            return this.c - o.c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken()) + 1;
        M = Integer.parseInt(st.nextToken());

        ArrayList<Shark> sharks = new ArrayList<>();
        board = new Shark[R][C];

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken());
            Shark shark = new Shark(row, col,
                                    Integer.parseInt(st.nextToken()),
                                    Integer.parseInt(st.nextToken()) - 1,
                                    Integer.parseInt(st.nextToken()));
            sharks.add(shark);
            board[row][col] = shark;
        }
        
        System.out.println(solution(sharks));
    }

    public static int solution(ArrayList<Shark> sharks) {
        int answer = 0;
        for(int i = 1; i < C; i ++){
            for(int j = 0; j < R; j ++){
                if(board[j][i] != null){
                    answer += board[j][i].z;
                    sharks.remove(board[j][i]);
                    board[j][i] = null;
                    break;
                }
            }
            moveShark(sharks);
        }
        return answer;
    }

    public static void moveShark(ArrayList<Shark> sharks) {
        board = new Shark[R][C];
        for(int i = 0; i < sharks.size(); i ++){
            int row = sharks.get(i).r;
            int col = sharks.get(i).c;
            int speed = sharks.get(i).s;
            int dir = sharks.get(i).d;

            while(speed > 0){
                row += dx[dir];
                col += dy[dir];
                if(row < 0 || col < 1 || row >= R || col >= C){
                    if(dir == 0) dir = 1;
                    else if(dir == 1) dir = 0;
                    else if(dir == 2) dir = 3;
                    else dir = 2;
                    row += dx[dir];
                    col += dy[dir];
                    continue;
                }
                speed--;
            }

            sharks.get(i).setR(row);
            sharks.get(i).setC(col);
            sharks.get(i).setD(dir);

            if(board[row][col] == null) board[row][col] = sharks.get(i);
            else if(board[row][col].z < sharks.get(i).z) board[row][col] = sharks.get(i);
        }

        sharks.clear();
        for(int i = 0; i < R; i ++){
            for(int j = 0; j < C; j ++){
                if(board[i][j] != null){
                    sharks.add(board[i][j]);
                }
            }
        }
    }
}
