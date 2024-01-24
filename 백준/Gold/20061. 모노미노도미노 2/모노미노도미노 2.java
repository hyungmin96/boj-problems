import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int N, answer = 0;
    int[][] blue = new int[6][4];
    int[][] green = new int[4][6];

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            
            int t = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            down(t, c);
            rowCheck();
            deleteRow();

            right(t, r);
            colCheck();
            deleteCol();
        }
        System.out.println(answer);
        System.out.println(count());
    }

    public int count(){
        int cnt = 0;
        for(int i = 0; i < blue.length; i ++){
            for(int j = 0; j < blue[i].length; j ++){
                if(blue[i][j] == 1){
                    cnt ++;
                }
                if(green[j][i] == 1){
                    cnt ++;
                }
            }
        }

        return cnt;
    }

    public void deleteRow(){
        int cnt = 0;
        for(int i = 0; i < 2; i ++){
            for(int j = 0; j < 4; j ++){
                if(blue[i][j] == 1){
                    cnt ++;
                    break;
                }
            }
        }

        for(int i = 5; i > 1; i --){
            for(int j = 0; j < 4; j ++){
                blue[i][j] = blue[i - cnt][j];
            }
        }

        for(int i = 0; i < 2; i ++){
            for(int j = 0; j < 4; j ++){
                blue[i][j] = 0;
            }
        }

    }

    public void rowCheck(){
        int r = 5;
        while(r > 1){
            boolean flag = true;
            for(int i = 0; i < 4; i ++){
                if(blue[r][i] == 0){
                    r --;
                    flag = false;
                    break;
                }
            }

            if(flag){
                answer ++;
                for(int i = r; i > 0; i --){
                    for(int j = 0; j < 4; j ++){
                        blue[i][j] = blue[i - 1][j];
                    }
                }
            }
        }
    }

    public void down(int type, int c){
        int r;
        switch(type){
            case 1:
                r = 0;
                while(r + 1 < 6 && blue[r + 1][c] == 0){
                    r ++;
                }
                blue[r][c] = 1;
                break;
            case 2: // 1 * 2
                r = 0;
                while(r + 1 < 6 && blue[r + 1][c] == 0 && blue[r + 1][c + 1] == 0){
                    r ++;
                }
                blue[r][c] = 1;
                blue[r][c + 1] = 1;
                break;
            case 3: // 1 * 2
                r = 0;
                while(r + 2 < 6 && blue[r + 2][c] == 0 && blue[r + 1][c] == 0){
                    r ++;
                }
                blue[r][c] = 1;
                blue[r + 1][c] = 1;
                break;
        }
    }

    public void deleteCol(){
        int cnt = 0;
        for(int i = 0; i < 2; i ++){
            for(int j = 0; j < 4; j ++){
                if(green[j][i] == 1){
                    cnt ++;
                    break;
                }
            }
        }

        for(int i = 5; i > 1; i --){
            for(int j = 0; j < 4; j ++){
                green[j][i] = green[j][i - cnt];
            }
        }

        for(int i = 0; i < 2; i ++){
            for(int j = 0; j < 4; j ++){
                green[j][i] = 0;
            }
        }

    }

    public void colCheck(){
        int c = 5;
        while(c > 1){
            boolean flag = true;
            for(int i = 0; i < 4; i ++){
                if(green[i][c] != 1){
                    c --;
                    flag = false;
                    break;
                }
            }

            if(flag){
                answer ++;
                for(int i = c; i > 0; i --){
                    for(int j = 0; j < 4; j ++){
                        green[j][i] = green[j][i - 1];
                    }
                }
            }
        }
    }

    public void right(int type, int r){
        int c;
        switch(type){
            case 1:
                c = 0;
                while(c + 1 < 6 && green[r][c + 1] == 0){
                    c ++;
                }
                green[r][c] = 1;
                break;
            case 2: // 1 * 2
                c = 0;
                while (c + 2 < 6 && green[r][c + 2] == 0 && green[r][c + 1] == 0) {
                    c++;
                }
                green[r][c] = 1;
                green[r][c + 1] = 1;
                break;
            case 3: // 2 * 1
                c = 0;
                while (c + 1 < 6 && green[r][c + 1] == 0 && green[r + 1][c + 1] == 0) {
                    c++;
                }
                green[r][c] = 1;
                green[r + 1][c] = 1;
                break;
        }
    }
}
