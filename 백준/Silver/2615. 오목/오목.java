import java.io.*;
import java.util.*;

public class Main {

    static int[][] map = new int[21][21];
    static int[][] dir = {
            { -1, 0 },
            { -1, 1 },
            { 0, 1 },
            { 1, 1 },
            { 1, 0 },
            { 1, -1 },
            { 0, -1 },
            { -1, -1 }
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 1; i < 20; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j < 20; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] answer = solution();
        if (answer[0] == -1) {
            System.out.println(0);
        } else {
            System.out.println(answer[0]);
            System.out.println(answer[1] + " " + answer[2]);
        }
    }

    public static int[] solution() {
        for (int i = 1; i < 20; i++) {
            for (int j = 1; j < 20; j++) {
                if (map[i][j] > 0) {
                    int[] temp = check(i, j, map[i][j]);
                    if (temp[0] != -1) {
                        return temp;
                    }
                }
            }
        }
        return new int[] { -1 };
    }

    public static int[] check(int r, int c, int cur) {
        for (int i = 0; i < 8; i ++) {
            int tr = r, tc = c;
            int row = r, col = c, m = 1;
            while(true){
                if(m > 5) break;
                int nr = tr + dir[i][0];
                int nc = tc + dir[i][1];
                if (nr > 19 || nc > 19 || nr < 1 || nc < 1 || cur != map[nr][nc]) {
                    if (m == 5){
                        nr = r + dir[(i + 4) % 8][0];
                        nc = c + dir[(i + 4) % 8][1];
                        if (nr > 19 || nc > 19 || nr < 1 || nc < 1 || cur != map[nr][nc]){
                            return new int[] { cur, row, col };
                        }
                    }

                    break;
                }

                m ++;
                tr = nr;
                tc = nc;
                
                if (col >= nc) {
                    if (col == nc) {
                        row = Math.min(row, nr);
                    } else {
                        row = nr;
                    }
                    col = nc;
                }
            }
        }

        return new int[] { -1 };
    }
}