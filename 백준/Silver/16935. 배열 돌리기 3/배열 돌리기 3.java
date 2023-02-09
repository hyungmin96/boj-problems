import java.io.*;
import java.util.*;

public class Main {

    static int n, m, r;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        arr = new int[n][m];

        for(int i = 0; i < n; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < m; j ++){
                int num = Integer.parseInt(st.nextToken());
                arr[i][j] = num;
            }
        }

        int[] cmd = new int[r];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < r; i ++){
            cmd[i] = Integer.parseInt(st.nextToken());
        }

        solution(cmd);
    }

    public static void solution(int[] cmd){
        for(int i : cmd){
            switch(i){
                case 1:
                    upDownFlip();
                    break;
                case 2:
                    leftRightFlip();
                    break;
                case 3:
                    rightSpin();
                    break;
                case 4:
                    leftSpin();
                    break;
                case 5:
                    rightMove();
                    break;
                case 6:
                    leftMove();
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int r = 0; r < n; r ++){
            for(int c = 0; c < m; c ++){
                sb.append(arr[r][c] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void leftMove(){
        int w = m / 2;
        int h = n / 2;
        int[][] dirs = {
            { 0, w }, { h, 0 }, { -h, 0 }, { 0, -w }
        };

        int idx = 0;
        int[][] temp = new int[n][m];
        for(int i = 0; i < n; i += h){
            for(int j = 0; j < m; j += w){
                for(int k = 0; k < h; k ++){
                    for(int l = 0; l < w; l ++){
                        temp[i + k][j + l] = arr[i + k + dirs[idx][0]][j + l + dirs[idx][1]];
                    }
                }
                idx ++;
            }
        }
        arr = temp;
    }

    public static void rightMove(){
        int w = m / 2;
        int h = n / 2;
        int[][] dirs = {
            { h, 0 }, { 0, -w }, { 0, w }, { -h, 0 }
        };

        int idx = 0;
        int[][] temp = new int[n][m];
        for(int i = 0; i < n; i += h){
            for(int j = 0; j < m; j += w){
                for(int k = 0; k < h; k ++){
                    for(int l = 0; l < w; l ++){
                        temp[i + k][j + l] = arr[i + k + dirs[idx][0]][j + l + dirs[idx][1]];
                    }
                }
                idx ++;
            }
        }
        arr = temp;
    }

    public static void leftSpin(){
        int t = n;
        n = m;
        m = t;

        int[][] temp = new int[n][m];
        for(int i = 0; i < n; i ++){
            for(int j = 0; j < m; j ++){
                temp[i][j] = arr[j][n - i - 1];
            }
        }

        arr = temp;
    }

    public static void rightSpin(){
        int t = n;
        n = m;
        m = t;

        int[][] temp = new int[n][m];
        for(int i = 0; i < n; i ++){
            for(int j = 0; j < m; j ++){
                temp[i][j] = arr[m - 1 - j][i];
            }
        }

        arr = temp;
    }

    public static void upDownFlip(){
        for(int i = 0; i < n / 2; i ++){
            for(int j = 0; j < m; j ++){
                int temp = arr[n - 1 - i][j];
                arr[n - 1 - i][j] = arr[i][j];
                arr[i][j] = temp;
            }
        }
    }

    public static void leftRightFlip(){
        for(int j = 0; j < m / 2; j ++){
            for(int i = 0; i < n; i ++){
                int temp = arr[i][j];
                arr[i][j] = arr[i][m - 1 - j];
                arr[i][m - 1 - j] = temp;
            }
        }
    }
}