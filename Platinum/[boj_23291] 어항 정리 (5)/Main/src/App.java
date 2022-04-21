import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
public class App {

    private static int[][] board;
    private static int N, K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int[] fishbowl = new int[N];
        
        board = new int[N][N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i ++){
            fishbowl[i] = Integer.parseInt(st.nextToken());
        }
        solution();
    }

    public static void solution(){
        N = 8;
        int[][] b = new int[N][N];
        b[0] = new int[] { 5, 3, 3, 14, 9, 3, 11, 8 };
        int p = 0, w = 1, h = 1;

        while(p + w + h <= N){
            for(int r = 0; r < h; r ++){
                for(int c = 0; c < w; c ++){
                    int nr = w - c;
                    int nc = p + w + r;
                    b[nr][nc] = b[r][c + p];
                    b[r][c + p] = 0;
                    System.out.println();
                }
            }
            p += w;
            if(w == h) h ++; else w ++;
        }

        for(int r = 0; r < N; r ++){
            for(int c = 0; c < N; c ++){
                System.out.print(b[r][c]);
            }
            System.out.println();
        }
    }

    public static void putFishbowl(){
        int p = 1, w = 1, h = 1;
        int index = 0;
        while((p - 1) + w + h <= N){
            index ++;
            for(int i = p; i < p + w; i ++){
                for(int j = N; j > N - h; j --){
                    int nr = (N - w) + (i - p);
                    int nc = (p + w) + (N - j);
                }
            }
            if(index % 2 != 0) h ++; else w ++;
        }
    }

    public static void putFish(){
        ArrayList<int[]> tempList = new ArrayList<>();
        int temp = Integer.MAX_VALUE;
        for(int i = 0; i < board[N-1].length; i ++){
            if(board[N-1][i] < temp){
                if(board[N-1][i] != temp)
                    tempList.clear();
                temp = board[N-1][i];
                tempList.add(new int[] { N - 1, i });
            }
        }
        for(int i = 0; i < tempList.size(); i ++){
            int[] curr = tempList.get(i);
            board[curr[0]][curr[1]] ++;
        }
    }
}
