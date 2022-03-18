import java.io.*;
import java.util.StringTokenizer;
public class Main {

    private static int answer = Integer.MIN_VALUE;
    private static int[] nums = new int[10];
    private static int[] dice = new int[10];
    private static int[][] board = new int[4][23];

    public static class Piece{
        int id, index, location, pre_location, pre_index;
        boolean state, course;
        public Piece(int id, int index, int location, int pre_index, int pre_location, boolean state, boolean course){
            this.id = id;
            this.index = index;
            this.location = location;
            this.pre_index = pre_index;
            this.pre_location = pre_location;
            this.state = state;
            this.course = course;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        board[0] = new int[] { 0, 2, 4, 6, 8, 10, 13, 16, 19, 25, 30, 35, 40, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        board[1] = new int[] { 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 25, 30, 35, 40, -1, -1, -1, -1, -1, -1, -1 };
        board[2] = new int[] { 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 28, 27, 26, 25, 30, 35, 40, -1 };
        board[3] = new int[] { 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, -1, -1, -1 };
        for(int i = 0; i < 10; i ++){
            dice[i] = Integer.parseInt(st.nextToken());
        }
        solution(10, 0, dice);
        System.out.println(answer);
    }

    public static void solution(int n, int depth, int[] dice){
        if(n == depth){
            int temp = getResult();
            answer = (temp > answer) ? temp : answer;
            return;
        }
        for(int i = 0; i < 4; i ++){
            nums[depth] = i;
            solution(n, depth + 1, dice);
        }
    }

    public static int getResult(){
        int result = 0;
        Piece[] piece = new Piece[4];
        int[] score = new int[4];
        boolean[][] visited = new boolean[4][42];
        boolean[][] ex_visited = new boolean[4][42];

        for(int i = 0; i < 4; i ++)
            piece[i] = new Piece(i, 3, 0, 3, 0, true, false);

        for(int i = 0; i < dice.length; i ++){
            Piece cp = piece[nums[i]];
            int loc = cp.location + dice[i];
            if (cp.state) {
                cp.pre_index = cp.index;
                cp.pre_location = cp.location;
                if(loc >= board[cp.index].length || board[cp.index][loc] == -1){
                    cp.state = false;
                    reMark(cp.pre_index, cp.pre_location, visited, ex_visited);
                }else{
                    setCourse(cp, cp.course, cp.index, loc);
                    if (check(cp.index, loc, visited, ex_visited)) {
                        mark(cp.index, loc, visited, ex_visited);
                        reMark(cp.pre_index, cp.pre_location, visited, ex_visited);
                        score[nums[i]] += board[cp.index][loc];
                        cp.location = loc;
                    } else {
                        return -1;
                    }
                }
            }
        }

        for(int s : score)
            result += s;

        return result;
    }

    public static void mark(int idx, int loc, boolean[][] visited, boolean[][] ex_visited){
        int n = board[idx][loc];
        if((n == 16 || n == 22 || n == 24 || n == 30 || n == 26 || n == 28) && !ex_visited[idx][loc]){
            ex_visited[idx][loc] = true;
        }else{
            visited[0][n] = true;
        }
    }

    public static boolean check(int idx, int loc, boolean[][] visited, boolean[][] ex_visited){
        int n = board[idx][loc];
        if((n == 16 || n == 22 || n == 24 || n == 30 || n == 26 || n == 28) && ex_visited[idx][loc])
            return false;
        else if(visited[0][n]){
            return false;
        }
        return true;
    }

    public static void reMark(int p_idx, int p_loc, boolean[][] visited, boolean[][] ex_visited){
        int n = board[p_idx][p_loc];
        if ((n == 16 || n == 22 || n == 24 || n == 30 || n == 26 || n == 28) && ex_visited[p_idx][p_loc])
            ex_visited[p_idx][p_loc] = false;
        else
            visited[0][n] = false;
    }

    public static void setCourse(Piece cp, boolean isSet, int idx, int loc){
        int n = board[idx][loc];
        if(!isSet && (n == 10 || n == 20 || (idx == 3 && n == 30))){
            cp.course = true;
            if(n == 10){
                cp.index = 0;
            }else if(n == 20){
                cp.index = 1;
            }else if(n == 30){
                cp.index = 2;
            }
        }
    }
}
