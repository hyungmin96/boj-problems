import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
public class Main {

    private static ArrayList<Integer> arrList = new ArrayList<>();
    private static ArrayList<Integer> values = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] board = new int[n][n];
        boolean[] visited = new boolean[n];
        
        for(int i = 0; i < n; i ++)
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(item -> Integer.parseInt(item)).toArray();

        solution(0, n, 0, board, visited);
        Collections.sort(values);
        System.out.println(values.get(0));
    }

    public static void solution(int depth, int n, int index, int[][] board, boolean[] visited){
        if(n / 2 == depth){
            getDiff(n, board);
            return;
        }else{
            for(int i = index; i < n; i ++){
                if(!visited[i]){
                    visited[i] = true;
                    arrList.add(i);
                    solution(depth + 1, n, i, board, visited);
                    arrList.remove(arrList.size() - 1);
                    visited[i] = false;
                }
            }
        }
    }

    public static void getDiff(int n, int[][] board){

        int t1 = 0;
        int t2 = 0;
        for(int i = 0; i < n; i ++){
            if(!arrList.contains(i)){
                arrList.add(i);
            }
        }

        for(int i = 0; i < n / 2; i ++){
            for(int j = i + 1; j < n / 2; j ++){
                t1 += board[arrList.get(i)][arrList.get(j)];
                t1 += board[arrList.get(j)][arrList.get(i)];
            }
        }

        for(int i = n / 2; i < n; i ++){
            for(int j = i + 1; j < n; j ++){
                t2 += board[arrList.get(i)][arrList.get(j)];
                t2 += board[arrList.get(j)][arrList.get(i)];
            }
        }
        values.add(Math.abs(t1 - t2));
        for(int i = arrList.size() - 1; i >= n / 2; i --){
            arrList.remove(i);
        }
    }
}
