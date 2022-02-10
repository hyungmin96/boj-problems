import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static StringBuilder sb = new StringBuilder();

    private static final int U = 0;
    private static final int D = 1;
    private static final int L = 2;
    private static final int R = 3;
    private static final int F = 4;
    private static final int B = 5;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int m = Integer.parseInt(br.readLine());
            char[][][] cube = new char[6][3][3];

            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    cube[U][x][y] = 'w';
                    cube[D][x][y] = 'y';
                    cube[R][x][y] = 'b';
                    cube[L][x][y] = 'g';
                    cube[F][x][y] = 'r';
                    cube[B][x][y] = 'o';
                }
            }

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                solution(st.nextToken(), cube);
                printResult(cube);
                System.out.println(sb.toString());
                sb.setLength(0);
            }
        }
    }

    public static void solution(String command, char[][][] cube) {
      
        char facet = command.charAt(0);
        char dir = command.charAt(1);
        char[] temp = new char[3];

        switch (facet) {
            case 'U':
                break;
            case 'D':
                break;
            case 'F':
                break;
            case 'B':
                break;
            case 'L':
                break;
            case 'R':
                break;
        }
        spinFacet(cube, facet, dir);
    }

    public static void spinFacet(char[][][] cube, int facet, char dir){
        facet = (facet == 'U') ? 0 : (facet == 'D') ? 1 : (facet == 'L') ? 2 : (facet == 'R') ? 3 : (facet == 'F') ? 4 : 5;
        char[][] temp = new char[3][3];
        if(dir == '-'){
            for(int i = 0; i < 3; i ++){
                for(int j = 0; j < 3; j ++){
                    temp[i][j] = cube[facet][j][2 - i];
                }
            }
        }else{
            for(int i = 0; i < 3; i ++){
                for(int j = 0; j < 3; j ++){
                    temp[i][j] = cube[facet][2- j][i];
                }
            }
        }
        cube[facet] = temp;
    }

    public static void printResult(char[][][] cube) {
        for (int i = 0; i < 3; i ++) {
            sb.append("" + cube[U][i][0] + cube[U][i][1] + cube[U][i][2] + "\n");
        }
    }
}
