import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static class Move{
        int d, s;
        public Move(int d, int s){
            this.d = d;
            this.s = s;
        }
    }

    private static int N, M;
    private static int[][] board;
    private static boolean[][] location;
    private static Move[] moves;
    private static int[] dx = new int[] { 0,-1,-1,-1, 0, 1, 1, 1 };
    private static int[] dy = new int[] {-1,-1, 0, 1, 1, 1, 0,-1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        location = new boolean[N][N];
        moves = new Move[M];

        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int index = 0;
            while(st.hasMoreTokens()){
                board[i][index++] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < M; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            moves[i] = new Move(d, s);
        }
        location[N-1][0] = true;
        location[N-1][1] = true;
        location[N-2][0] = true;
        location[N-2][1] = true;
        System.out.println(solution());
    }

    public static int solution(){
        for(int i = 0; i < moves.length; i ++){
            moveClouds(moves[i].d, moves[i].s);
            dropRain();
            duplicateWater();
            createNewClouds();
        }
        return getWater();
    }

    public static void moveClouds(int d, int s){
        boolean[][] visited = new boolean[N][N];
        for(int r = 0; r < N; r ++){
            for(int c = 0; c < N; c ++){
                if(location[r][c]){
                    int tr = r;
                    int tc = c;
                    location[r][c] = false;
                    for(int i = 0; i < s; i ++){
                        tr += dx[d];
                        tc += dy[d];
                        if(tr >= N) tr = 0;
                        if(tr < 0) tr = N-1;
                        if(tc >= N) tc = 0;
                        if(tc < 0) tc = N-1;
                    }
                    visited[tr][tc] = true;
                }
            }
        }
        for(int r = 0; r < N; r ++){
            for(int c = 0; c < N; c ++){
                location[r][c] = visited[r][c];
            }
        }
    }

    public static void dropRain(){
        for(int r = 0; r < N; r ++){
            for(int c = 0; c < N; c ++){
                if(location[r][c]){
                    board[r][c] ++;
                }
            }
        }
    }

    public static void duplicateWater(){
        for(int r = 0; r < N; r ++){
            for(int c = 0; c < N; c ++){
                if(location[r][c]){
                    int cnt = 0;
                    for(int i = 1; i < dx.length; i +=2){
                        int nr = r + dx[i];
                        int nc = c + dy[i];
                        if(nr >= 0 && nc >= 0 && nr < N && nc < N && board[nr][nc] > 0){
                            cnt ++;
                        }
                    }
                    board[r][c] += cnt;
                }
            }
        }
    }

    public static void createNewClouds(){
        for(int r = 0; r < N; r ++){
            for(int c = 0; c < N; c ++){
                if(!location[r][c] && board[r][c] >= 2){
                    board[r][c] -= 2;
                    location[r][c] = true;
                }else if(location[r][c]) location[r][c] = false;
            }
        }
    }

    public static int getWater(){
        int ret = 0;
        for(int r = 0; r < N; r ++){
            for(int c = 0; c < N; c ++){
                if(board[r][c] > 0){
                    ret += board[r][c];
                }
            }
        }
        return ret;
    }
}
