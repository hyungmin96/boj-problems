import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int m = Integer.parseInt(br.readLine());
            char[][][][][] cube = new char[6][3][3][3][4];
            
            for (int x = 0; x < 6; x++) {
                for (int y = 0; y < 3; y++) {
                    for (int z = 0; z < 3; z++) {
                        cube[x][y][z] = new char[][] {
                            { '.', 'o' },
                            { 'g', 'y', 'b', 'w' },
                            { '.', 'r' }
                        };
                    }
                }
            }

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                solution(st.nextToken(), cube);
            }
            printResult(cube);
        }
        System.out.println(sb.toString());
    }

    public static void solution(String command, char[][][][][] cube) {
        char facet = command.charAt(0);
        char dir = command.charAt(1);

        char temp_char;
        char[][] temp_cube;
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
    }

    public static void printResult(char[][][][][] cube) {
        for (int i = 0; i < 3; i++) {
            sb.append("" + cube[i][0][0][1][3] + cube[i][0][1][1][3] + cube[i][0][2][1][3] + "\n");
        }
    }
}
