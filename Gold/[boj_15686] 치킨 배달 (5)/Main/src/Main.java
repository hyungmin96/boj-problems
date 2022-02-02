import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {

    private static int answer = Integer.MAX_VALUE;
    private static int N, M;
    private static int[][] board;
    private static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        visited = new boolean[N][N];

        ArrayList<int[]> chicken_pos = new ArrayList<>();
        ArrayList<int[]> house_pos = new ArrayList<>();

        for(int i = 0; i < N; i ++){
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(item -> Integer.parseInt(item)).toArray();
            board[i] = arr;
            for(int j = 0; j < arr.length; j ++){
                if(arr[j] == 2){
                    chicken_pos.add(new int[] { i, j });
                }else if(arr[j] == 1){
                    house_pos.add(new int[] { i, j });
                }
            }
        }
        int[][] chicken_part_arr = new int[M][2];
        solution(0, 0, M, chicken_pos, chicken_part_arr, house_pos);
        System.out.println(answer);
    }

    public static void solution(int level, int index, int depth, ArrayList<int[]> chicken_pos, int[][] chicken_part_arr, ArrayList<int[]> house_pos){
        if(level == depth){
            int cnt = getDistance(chicken_part_arr, house_pos);
            answer = (answer > cnt) ? cnt : answer;
            return;
        }

        for(int i = index; i < chicken_pos.size(); i ++){
            if(!visited[chicken_pos.get(i)[0]][chicken_pos.get(i)[1]]){
                visited[chicken_pos.get(i)[0]][chicken_pos.get(i)[1]] = true;
                chicken_part_arr[level][0] = chicken_pos.get(i)[0];
                chicken_part_arr[level][1] = chicken_pos.get(i)[1];
                solution(level + 1, i, depth, chicken_pos, chicken_part_arr, house_pos);
                visited[chicken_pos.get(i)[0]][chicken_pos.get(i)[1]] = false;
            }
        }
    }

    public static int getDistance(int[][] chicken_part_arr, ArrayList<int[]> house_pos){
        int result = 0;
        
        for(int[] house : house_pos){
            int temp = Integer.MAX_VALUE;
            for (int i = 0; i < M; i++) {
                temp = Math.min(temp, Math.abs(chicken_part_arr[i][0] - house[0]) + Math.abs(chicken_part_arr[i][1] - house[1]));
            }
            result += temp;
        }

        return result;
    }
}
