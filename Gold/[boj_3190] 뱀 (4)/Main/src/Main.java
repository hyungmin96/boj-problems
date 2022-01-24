import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
public class Main {

    public static class Command{
        int time;
        char c;
        public Command(int time, char c){
            this.time = time;
            this.c = c;
        }
    }

    private static int[] dx = new int[] {-1, 0, 1, 0 };
    private static int[] dy = new int[] { 0, 1, 0,-1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int A = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];
        
        for(int i = 0; i < A; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            board[row][col] = 2; // 사과는 2
        }

        int C = Integer.parseInt(br.readLine());
        Command[] commands = new Command[C];
        for(int i = 0; i < C; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int time = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            commands[i] = new Command(time, c);
        }
        // { 위치 x, y, length, direction }
        int[] snake = new int[] { 0, 0, 1, 1 };
        System.out.println(solution(N, board, commands, snake));
    }

    public static int solution(int N, int[][] board, Command[] commands, int[] snake){
        
        int answer = 0;
        int command_index = 0;

        ArrayList<int[]> pos = new ArrayList<>();
        board[0][0] = -1;
        pos.add(new int[] { 0, 0 });

        while(true){
            answer ++;

            int len = snake[2];
            int dir = snake[3];
            int next_row = snake[0] + dx[dir];
            int next_col = snake[1] + dy[dir];

            if(answer - 1 != 0 && answer % commands[command_index].time == 0){
                if(commands[command_index].c == 'D') dir = (dir + 1) % 4;
                if(commands[command_index].c == 'L') dir = (dir + 3) % 4;                    
                if(commands.length - 1 > command_index) command_index ++;
            }

            if(next_row >= 0 && next_col >= 0 && next_row < N && next_col < N && board[next_row][next_col] != -1){
                if(board[next_row][next_col] == 2){
                    len ++;
                }else{
                    board[pos.get(0)[0]][pos.get(0)[1]] = 0;
                    pos.remove(0);
                }
                pos.add(new int[] { next_row, next_col });
                board[next_row][next_col] = -1;
            }else{
                break;
            }
            
            snake = new int[] { next_row, next_col, len, dir };
        }

        return answer;
    }

}
