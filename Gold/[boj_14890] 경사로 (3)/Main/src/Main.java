import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {
    
    private static int N;
    private static int L;
    private static int[][] board;
    private static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        board = new int[N][N];

        for(int i = 0; i < N; i ++){
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(item -> Integer.parseInt(item)).toArray();
        }

        for(int i = 0; i < N; i ++){
            if(solution(board[i])) answer ++;
        }
        for(int i = 0; i < N; i ++){
            int[] arr = new int[N];
            for(int j = 0; j < N; j ++) arr[j] = board[j][i];
            if(solution(arr)) answer ++;
        }

        System.out.println(answer);
    }

    public static boolean solution(int[] arr){
        boolean[] visited = new boolean[N];
        for(int i = 0; i < N - 1; i ++){
            if(Math.abs(arr[i + 1] - arr[i]) == 0) continue;
            if(Math.abs(arr[i + 1] - arr[i]) > 1) return false;

            boolean direction = (arr[i + 1] > arr[i]) ? true : false;
            int installedIndex = 0;
            if(direction) installedIndex = i;
            else installedIndex = i + 1; 

            if(!isAvailableHeight(arr, visited, direction, installedIndex)){
                return false;
            }
        }
        return true;
    }

    public static boolean isAvailableHeight(int[] arr, boolean[] visited, boolean direction, int index){
        if(direction){
            if((index + 1) - L < 0) return false;
            for(int i = index; i >= (index + 1) - L; i --){
                if(visited[i] || arr[index] != arr[i]) return false;
            }
            for(int i = index; i >= (index + 1) - L; i --){
                visited[i] = true;
            }
            return true;
        }else{
            if(index + L > arr.length) return false;
            for(int i = index; i < index + L; i ++){
                if(visited[i] || arr[index] != arr[i]) return false;
            }
            for(int i = index; i < index + L; i ++){
                visited[i] = true;
            }
            return true;
        }
    }
}
