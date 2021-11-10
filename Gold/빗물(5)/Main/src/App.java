import java.io.*;
import java.util.StringTokenizer;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");
        
        int height = Integer.parseInt(stz.nextToken());
        int width = Integer.parseInt(stz.nextToken());
        
        int[][] board = new int[height][width];
        int[] block = new int[width];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < width; i ++){
            block[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < block.length; i ++){
            for(int j = height - block[i]; j < height; j ++){
                board[j][i] = 1;
            }
        }

        System.out.println(solution(height, board, block));
    }

    public static int solution(int height, int[][] board, int[] block){
        
        int answer = 0;

        for(int i = 0; i < board.length; i ++){ // 높이
            for(int j = 1; j < board[i].length - 1; j ++){ // 넓이(양 끝은 확인할 필요 x)
                if(board[i][j] == 0){ // 블록이 아닌 공간
                    
                }
            }
        }

        return answer;
    }
}
