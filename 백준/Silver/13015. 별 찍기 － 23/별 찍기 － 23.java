import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Solution sol = new Solution();
        sol.solution(n);
    }
}

class Solution {
   
    public void solution(int n){
        int width = 2 * n + (2 * (n - 2) + 1);
        int height = 2 * n - 1;
        char[][] map = new char[height][width + 1];
        
        for(int i = 0; i < n; i ++){
            int space_width = 2 * (n - i - 2) + 1;
            if(i == 0){
                for(int j = 0; j < n; j ++){
                    map[i][j] = '*';
                }
                for(int j = n; j < 2 * n; j ++){
                    map[i][j + space_width] = '*';
                }
            }else{
                map[i][i] = '*';
                map[i][i + (n - 1)] = '*';

                map[i][i + (n - 1) + space_width + 1] = '*';
                map[i][i + (2 * (n - 1)) + space_width + 1] = '*';

                map[i][i + (2 * (n - 1)) + space_width + 3] = 'e';
            }
        }

        for(int i = n; i < height; i ++){
            int empty_width = n - (i % n) - 2;
            int center_space_width = 2 * (i - n) + 1;
            if(i == height - 1){
                for(int j = 0; j < n; j ++){
                    map[i][j] = '*';
                }
                for(int j = n; j < 2 * n; j ++){
                    map[i][j + center_space_width] = '*';
                }
            }else{
                map[i][empty_width] = '*';
                map[i][empty_width + (n - 1)] = '*';

                map[i][empty_width + (n - 1) + center_space_width + 1] = '*';
                map[i][empty_width + (2 * (n - 1)) + center_space_width + 1] = '*';

                map[i][empty_width + (2 * (n - 1)) + center_space_width + 3] = 'e';
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < map.length; i ++){
            for(int j = 0; j < map[i].length; j ++){
                if(map[i][j] == 'e') break;
                if(map[i][j] == '*')
                    sb.append(map[i][j]);
                else
                    sb.append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }
}