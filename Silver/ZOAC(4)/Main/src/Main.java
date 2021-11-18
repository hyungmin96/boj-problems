import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static class Position {
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] key1 = new char[] { 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p' };
        char[] key2 = new char[] { 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l' };
        char[] key3 = new char[] { 'z', 'x', 'c', 'v', 'b', 'n', 'm' };

        char[][] keyboard = new char[3][10];

        keyboard[0] = key1;
        keyboard[1] = key2;
        keyboard[2] = key3;

        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");
        
        String c1 = stz.nextToken();
        String c2 = stz.nextToken();
        
        String str = br.readLine();

        Position[] fingers = new Position[2];

        for (int i = 0; i < keyboard.length; i++) {
            for (int j = 0; j < keyboard[i].length; j++) {
                if (keyboard[i][j] == c1.charAt(0) || keyboard[i][j] == c2.charAt(0)) {
                    if (i < 2 && j > 4)
                        fingers[0] = new Position(i, j);
                    else
                        fingers[1] = new Position(i, j);
                }
            }
        }

        System.out.println(solution(str, fingers, keyboard));
    }

    public static int solution(String str, Position[] fingers, char[][] keyboard) {
        int answer = 0;

        for (char c : str.toCharArray()) {
            for (int i = 0; i < keyboard.length; i++) {
                for (int j = 0; j < keyboard[i].length; j++) {
                    if (keyboard[i][j] == c) {
                        if (i < 2 && j < 5 || i == 2 && j < 4) {
                            answer += Math.abs(fingers[0].x - i) + Math.abs(fingers[0].y - j) + 1; 
                            fingers[0] = new Position(i, j);
                        } else{
                            answer += Math.abs(fingers[1].x - i) + Math.abs(fingers[1].y - j) + 1; 
                            fingers[1] = new Position(i, j);
                        }
                    }
                }
            }
        }

        return answer;
    }
}
