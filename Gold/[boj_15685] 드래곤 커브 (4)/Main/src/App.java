import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class App {

    public static int[][] board;
    private static int[] dx = { 0,-1, 0, 1 };
    private static int[] dy = { 1, 0,-1, 0 };

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] curves = new int[N][4];
        board = new int[100][100];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            curves[i] = new int[] {
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
            };
        }

        solution(curves);
    }

    public static void solution(int[][] curves) {

        // X축, Y축, 이동방향, 세대 수
        for (int[] curve : curves) {
            ArrayList<Integer> dir = new ArrayList<>();
            board[curve[1]][curve[0]] = 1;
            board[curve[1] + dx[curve[2]]][curve[0] + dy[curve[2]]] = 1;
            int[] curr = new int[] { curve[1] + dx[curve[2]], curve[0]  + dy[curve[2]] };
            dir.add(curve[2]);
            for(int i = 0; i < curve[4]; i ++){
                for(int j = 0; j < (int)Math.pow(2, curve[4] - 1); j ++){

                }
            }
        }
    }
}
