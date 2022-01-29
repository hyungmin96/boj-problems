import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
public class Main {

    private static int N, M, H;
    private static int[][] ladders;
    private static int[] destination;
    private static int answer = 0;
    private static boolean flag = false;
    private static int[] dy = {-1, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ladders = new int[H][N];
        destination = new int[N];
        
        for(int i = 0; i < N; i ++){
            destination[i] = i + 1;
        }

        for(int i = 0; i < M; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            ladders[row][col] = 1;
        }

        if(checkLadder(ladders)) {
            System.out.println(0);
        }else{
            for(int i = 1; i <= 3; i ++){
                answer = i;
                int[][] new_ladder = copyLadder();
                solution(0, 0, i, new_ladder);
                if(flag) break;
            }
            System.out.println((flag) ? answer : - 1);
        }
    }

    public static void solution(int index, int row, int depth, int[][] new_ladder){
        if(flag) return;
        if(index == depth){
            if(checkLadder(new_ladder)) flag = true;
            return;
        }

        for (int i = row; i < H; i++) {
            for (int j = 0; j < N - 1; j++) {
                if (new_ladder[i][j] == 0 && new_ladder[i][j + dy[1]] == 0) {
                    if((j == 0 || new_ladder[i][j + dy[0]] == 0) && new_ladder[i][j + dy[1]] == 0){
                        new_ladder[i][j] = 1;
                        solution(index + 1, i, depth, new_ladder);
                        new_ladder[i][j] = 0;
                    }
                }
            }
        }
    }

    public static boolean checkLadder(int[][] new_ladder){
        for(int i = 0; i < N; i ++){
            int curr_index = i;
            for(int j = 0; j < H; j ++){
                if(new_ladder[j][curr_index] == 1) curr_index ++;
                else if(curr_index + dy[0] >= 0 && new_ladder[j][curr_index + dy[0]] == 1) curr_index --;
            }
            if(destination[curr_index] != i + 1) return false;
        }
        return true;
    }

    public static int[][] copyLadder(){
        int[][] new_ladder = new int[H][N];
        for(int i = 0; i < H; i ++){
            for(int j = 0; j < N; j ++){
                new_ladder[i][j] = ladders[i][j];
            }
        }
        return new_ladder;
    }
}
