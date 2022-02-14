import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static int N;
    private static int[][] board;
    private static boolean[][] visited;
    private static int curr_eat_number = 0;
    private static int[] dx = new int[] { -1, 0, 1, 0 };
    private static int[] dy = new int[] { 0, 1, 0, -1 };

    public static class Shark implements Comparable<Shark>{
        
        int row; 
        int col; 
        int size;
        int dis;
        public Shark(int row, int col, int size, int dis){
            this.row = row;
            this.col = col;
            this.size = size;
            this.dis = dis;
        }

        @Override
        public int compareTo(Shark o){
            if(this.dis != o.dis) return this.dis - o.dis;
            if(this.row != o.row) return this.row - o.row;
            else return this.col - o.col;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        visited = new boolean[N][N];
        Shark shark = new Shark(0, 0, 2, 0);

        for (int i = 0; i < N; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(item -> Integer.parseInt(item)).toArray();
            for (int j = 0; j < N; j++) {
                board[i][j] = arr[j];
                if (arr[j] == 9) {
                    shark.row = i;
                    shark.col = j;
                }
            }
        }
        
        while(true){
            visited = new boolean[N][N];
            shark = solution(shark);
            if(shark.row == -1 && shark.col == -1) break;
        }
        System.out.println(shark.dis);
    }

    public static Shark solution(Shark shark){
        Queue<Shark> que = new LinkedList<>();
        ArrayList<Shark> eatList = new ArrayList<>();
        boolean flag = false;
        que.offer(new Shark(shark.row, shark.col, shark.size, shark.dis));
        visited[shark.row][shark.col] = true;

        while(!que.isEmpty()){
            Shark curr = que.poll();
            for(int i = 0; i < dx.length; i ++){
                int next_row = curr.row + dx[i];
                int next_col = curr.col + dy[i];
                if(next_row >= 0 && next_col >= 0 && next_row < N && next_col < N && board[next_row][next_col] <= curr.size && !visited[next_row][next_col]){
                    que.offer(new Shark(next_row, next_col, curr.size, curr.dis + 1));
                    visited[next_row][next_col] = true;
                    if(curr.size > board[next_row][next_col] && board[next_row][next_col] != 0){
                        flag = true;
                        eatList.add(new Shark(next_row, next_col, curr.size, curr.dis + 1));
                    }
                }
            }
        }

        if(eatList.size() > 0){
            board[shark.row][shark.col] = 0;
            shark = eat(eatList);
            que.clear();
        }   

        if(!flag)
            return new Shark(-1, -1, 0, shark.dis);
        else
            return shark;
    }

    public static Shark eat(ArrayList<Shark> eatList){
        Collections.sort(eatList);
        int row = eatList.get(0).row;
        int col = eatList.get(0).col;
        int size = eatList.get(0).size;
        int dis = eatList.get(0).dis;

        board[row][col] = 9;
        curr_eat_number ++;
        if(curr_eat_number == size){
            curr_eat_number = 0;
            size ++;
        }
        return new Shark(row, col, size, dis);
    }

}
