import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");
        int r = Integer.parseInt(stz.nextToken());
        int c = Integer.parseInt(stz.nextToken());

        String[][] board = new String[r][c];
        for (int i = 0; i < r; i++) {
            board[i] = br.readLine().split("");
        }

        solution(r, c, board);
    }

    public static void solution(int row, int col, String[][] board) {

        ArrayList<String> arr = new ArrayList<>();

        for (int i = 0; i < row; i++) {
            String r = "";
            for (int j = 0; j < col; j++) {
                if (!board[i][j].equals("#"))
                    r += board[i][j];
                else {
                    if (r.length() >= 2)
                        arr.add(r);
                    r = "";
                }
            }
            if (r.length() >= 2)
                arr.add(r);
        }

        for (int i = 0; i < col; i++) {
            String r = "";
            for (int j = 0; j < row; j++) {
                if (!board[j][i].equals("#"))
                    r += board[j][i];
                    else {
                        if (r.length() >= 2)
                            arr.add(r);
                        r = "";
                    }
            }
            if (r.length() >= 2)
                arr.add(r);
        }

        Collections.sort(arr);
        System.out.println(arr.get(0));
    }
}
