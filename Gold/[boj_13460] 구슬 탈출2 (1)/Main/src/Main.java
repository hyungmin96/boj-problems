import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {

    public static class Marble{
        int rr, rc, br, bc, time;
        public Marble(int rr, int rc, int br, int bc, int time){
            this.rr = rr;
            this.rc = rc;
            this.br = br;
            this.bc = bc;
            this.time = time;
        }
    }

    private static int N, M;
    private static int[][] board;
    private static int[][] marble_pos = new int[2][2];
    private static int[] dx = new int[] {-1, 0, 1, 0 };
    private static int[] dy = new int[] { 0, 1, 0,-1 };
    private static int answer = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for(int i = 0; i < N; i ++){
            String str = br.readLine();
            for(int j = 0; j < str.length(); j ++){
                if(str.charAt(j) == 'R') {
                    marble_pos[0] = new int[] { i, j };
                    board[i][j] = 1;
                }
                else if(str.charAt(j) == 'B'){
                    marble_pos[1] = new int[] { i, j };
                    board[i][j] = 0;
                }
                else if(str.charAt(j) == '.')
                    board[i][j] = 0;
                else if(str.charAt(j) == 'O'){
                    board[i][j] = 3;
                }else
                    board[i][j] = -1;
            }
        }

        boolean[][][][] visited = new boolean[N][M][N][M];
        solution(0, marble_pos[0][0], marble_pos[0][1], marble_pos[1][0], marble_pos[1][1], true, true, visited);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    public static void solution(int depth, int rr, int rc, int br, int bc, boolean isRedAlive, boolean isBlueAlive, boolean[][][][] visited){
        if(depth > 10) return;

        if(!isRedAlive && isBlueAlive){
            answer = (answer > depth) ? depth : answer;
            return;
        }

        visited[rr][rc][br][bc] = true;
        for(int d = 0; d < dx.length; d ++){
            int crr = rr;
            int crc = rc;
            int brr = br;
            int brc = bc;

            while(board[crr + dx[d]][crc + dy[d]] != -1){
                crr += dx[d];
                crc += dy[d];
                if(board[crr][crc] == 3){
                    isRedAlive = false;
                    break;
                } 
            }

            while(board[brr + dx[d]][brc + dy[d]] != -1){
                brr += dx[d];
                brc += dy[d];
                if(board[brr][brc] == 3){
                    isBlueAlive = false;
                    break;
                } 
            }

            if(!isBlueAlive) {
                isRedAlive = true;
                isBlueAlive = true;
                continue;
            }
            
            if(crr == brr && crc == brc){
                if(d == 0){
                    if(rr > br){
                        crr += 1;
                    }else{
                        brr += 1;
                    }
                }else if(d == 1){
                    if(rc > bc){
                        brc -= 1;
                    }else{
                        crc -= 1;
                    }
                }else if(d == 2){
                    if(rr > br){
                        brr -= 1;
                    }else{
                        crr -= 1;
                    }
                }else if(d == 3){
                    if(rc > bc){
                        crc += 1;
                    }else{
                        brc += 1;
                    }
                }
            }

            if(!visited[crr][crc][brr][brc]){
                solution(depth + 1, crr, crc, brr, brc, isRedAlive, isBlueAlive, visited);
                visited[crr][crc][brr][brc] = false;
            }
        }
    }
}
