import java.util.*;
import java.io.*;

class Main {

    static int N1, M1, N2, M2;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N1 = Integer.parseInt(st.nextToken());
        M1 = Integer.parseInt(st.nextToken());

        int[][] map1 = new int[N1][M1];
        for(int i = 0; i < N1; i ++){
            String str = br.readLine();
            for(int j = 0; j < M1; j ++){
                char c = str.charAt(j);
                map1[i][j] = Integer.parseInt(c + "");
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        N2 = Integer.parseInt(st.nextToken());
        M2 = Integer.parseInt(st.nextToken());

        int[][] map2 = new int[N2][M2];
        for(int i = 0; i < N2; i ++){
            String str = br.readLine();
            for(int j = 0; j < M2; j ++){
                char c = str.charAt(j);
                map2[i][j] = Integer.parseInt(c + "");
            }
        }

        int size = Math.max(N2, M2);
        int[][] board = new int[N1 + (size * 2)][M1 + (size * 2)];
        for(int i = 0; i < N1; i ++){
            for(int j = 0; j < M1; j ++){
                board[i + size][j + size] = map1[i][j];
            }
        }
        
        int answer = Integer.MAX_VALUE;
        for (int r = 0; r <= board.length - size; r ++) {
            for (int c = 0; c <= board[r].length - size; c ++) {
                for (int d = 0; d < 4; d ++) {
                    map2 = rotate(map2);
                    if(check(r, c, board, map2)) {
                        int min_y = Math.min(r, size);
                        int max_y = Math.max(r + map2.length - 1, (size + N1 - 1));
                        int min_x = Math.min(c, size);
                        int max_x = Math.max(c + map2[0].length - 1, (size + M1 - 1));
                
                        int area = ((max_y - min_y) + 1) * ((max_x - min_x) + 1);
                        answer = Math.min(answer, area);
                    }
                }
            }
        }
        System.out.println(answer);
    }

    public static int[][] rotate(int[][] arr){
        int width = arr.length;
        int height = arr[0].length;
        int[][] temp = new int[height][width];

        for(int r = 0; r < height; r ++){
            for(int c = 0; c < width; c ++){
                temp[r][c] = arr[c][height - r - 1];
            }
        }
        return temp;
    }

    public static boolean check(int r, int c, int[][] temp, int[][] map2){
        for(int i = 0; i < map2.length; i ++){
            for(int j = 0; j < map2[0].length; j ++){
                if(map2[i][j] == 1 && temp[i + r][c + j] == 1) return false;
            }
        }
        return true;
    }
}