import java.io.*;
import java.util.StringTokenizer;
public class Main {

    private static int N, M;
    private static int[][] category;
    private static int[][] favorite;
    private static int[][] bridge;
    private static boolean[] frog;
    private static boolean[] use;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        category = new int[N][4];
        favorite = new int[N][2];
        frog = new boolean[N];
        bridge = new int[M][3];
        use = new boolean[M];

        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int index = 0;
            while(st.hasMoreTokens()){
                category[i][index ++] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int index = 0;
            while(st.hasMoreTokens()){
                favorite[i][index ++] = Integer.parseInt(st.nextToken());
            }
            if(index != 2) favorite[i][1] = favorite[i][0];
        }

        for(int i = 0; i < M; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int index = 0;
            while(st.hasMoreTokens()){
                bridge[i][index ++] = Integer.parseInt(st.nextToken());
            }
        }
        solution(0, 0, new int[N]);
    }

    public static void solution(int depth, int index, int[] temp){
        if(depth == N){
            return;
        }

        for(int i = 0; i < N; i ++){
            for(int j = 0; j < favorite[i].length; j ++){
                if(frog[i]) continue;
                if(favorite[i][j] - 1 != depth) continue;
                frog[i] = true;
                temp[depth] = i;
                solution(depth + 1, i, temp);
                frog[i] = false;
            }
        }
    }
}
