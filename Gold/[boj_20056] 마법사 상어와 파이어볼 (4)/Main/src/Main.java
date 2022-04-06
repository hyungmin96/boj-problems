import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    private static int N, M, K;
    private static ArrayList<Fireball>[][] board;
    private static int[] dx = new int[] {-1,-1, 0, 1, 1, 1, 0,-1 };
    private static int[] dy = new int[] { 0, 1, 1, 1, 0,-1,-1,-1 };

    public static class Fireball{
        int r, c, m, s, d;
        boolean isMoved;
        public Fireball(int r, int c, int m, int s, int d, boolean isMoved){
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
            this.isMoved = isMoved;
        }
    }

    public static class Create{
        int nm, ns, n;
        boolean isChange;
        public Create(int nm, int ns, int n, boolean isChange){
            this.nm = nm; 
            this.ns = ns;
            this.n = n;
            this.isChange = isChange;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new ArrayList[N][N];

        for(int i = 0; i < M; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            board[r][c] = new ArrayList<>();
            board[r][c].add(new Fireball(r, c, m, s, d, false));
        }
        System.out.println(solution());
    }

    public static int solution(){
        for(int i = 0; i < K; i ++){
            move();
            shareBalls();
            clear();
        }
        return getMass();
    }

    public static void move(){
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j ++){
                if(board[i][j] == null) board[i][j] = new ArrayList<>();
                for(int l = board[i][j].size() - 1; l >= 0; l --){
                    if(board[i][j].get(l).isMoved) continue;
                    Fireball curr = board[i][j].get(l);
                    board[i][j].remove(l);
                    int cr = curr.r;
                    int cc = curr.c;
                    for(int k = 0; k < curr.s; k ++){
                        cr += dx[curr.d];
                        cc += dy[curr.d];
                        if(cr < 0) cr = N - 1;
                        if(cr >= N) cr = 0;
                        if(cc < 0) cc = N - 1;
                        if(cc >= N) cc = 0;
                    }
                    if(board[cr][cc] == null) board[cr][cc] = new ArrayList<>();
                    board[cr][cc].add(new Fireball(cr, cc, curr.m, curr.s, curr.d, true));
                }
            }
        }
    }

    public static void shareBalls(){
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j ++){
                if(board[i][j].size() > 1){
                    int nm = 0;
                    int ns = 0;
                    int n = 0;
                    int odd_cnt = 0;
                    int even_cnt = 0;
                    for(int k = 0; k < board[i][j].size(); k ++){
                        Fireball ball = board[i][j].get(k);
                        nm += ball.m;
                        ns += ball.s;
                        n ++;
                        if(ball.d % 2 == 0) even_cnt ++;
                        else odd_cnt ++;
                    }
                    board[i][j].clear();
                    int[] t = new int[4];
                    if(even_cnt == 0 || odd_cnt == 0){
                        t = new int[] { 0, 2, 4, 6 };
                    }else{
                        t = new int[] { 1, 3, 5, 7 };
                    }
                    for(int l = 0; l < 4; l ++){
                        board[i][j].add(new Fireball(i, j, nm / 5, ns / n, t[l], false));
                    }
                }
            }
        }
    }

    public static void clear(){
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j ++){
                for(int k = board[i][j].size() - 1; k >= 0; k --){
                    if(board[i][j].get(k).m < 1){
                        board[i][j].remove(k);
                    }else{
                        board[i][j].get(k).isMoved = false;
                    }
                }
            }
        }
    }

    public static int getMass(){
        int result = 0;
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j ++){
                for(int k = 0; k < board[i][j].size(); k ++){
                    result += board[i][j].get(k).m;
                }
            }
        }
        return result;
    }
}
