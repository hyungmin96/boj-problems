import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
public class Main {

    public static class Pos{
        int t, x, y;
        int size_x, size_y;
        public Pos(int t, int x, int y){
            this.t = t;
            this.x = x;
            this.y = y;
            if(t == 2) size_y ++;
            else if(t == 3) size_x ++;
        }
    }

    private static int[][] blue = new int[4][6];
    private static int[][] green = new int[6][4];
    private static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Pos[] pos = new Pos[N];
        for(int i = 0; i < N; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pos[i] = new Pos(t, x, y);
        }

        for(Pos p : pos){
            stackToBoard(p);
            ArrayList<Integer> delete_row = removeGreenRow();
            ArrayList<Integer> delete_col = removeBlueCol();
            changeGreenBoard(delete_row);
            changeBlueBoard(delete_col);
            checkGreenBoardZone();
            checkBlueBoardZone();
            System.out.println();
        }

        System.out.println(answer);
        System.out.println(getTileNum());
    }

    public static void stackToBoard(Pos pos){
        for(int i = 5; i >= 0; i --){
            boolean flag = true;
            if(i + pos.size_x < 6 && pos.y + pos.size_y < 4 && green[i][pos.y] == 0 && green[i + pos.size_x][pos.y + pos.size_y] == 0){
                for(int j = i; j >= 0; j --){
                    if(green[j][pos.y] != 0 || green[j + pos.size_x][pos.y + pos.size_y] != 0){
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    green[i + pos.size_x][pos.y] = 1;
                    green[i][pos.y + pos.size_y] = 1;
                    break;
                }
            }
        }
        for(int i = 5; i >= 0; i --){
            boolean flag = true;
            if(pos.x + pos.size_x < 4 && i + pos.size_y < 6 && blue[pos.x][i] == 0 && blue[pos.x + pos.size_x][i + pos.size_y] == 0){
                for(int j = i; j >= 0; j --){
                    if(blue[pos.x][j] != 0 || blue[pos.x + pos.size_x][j + pos.size_y] != 0){
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    blue[pos.x][i + pos.size_y] = 1;
                    blue[pos.x + pos.size_x][i] = 1;
                    break;
                }
            }
        }
    }

    public static ArrayList<Integer> removeGreenRow(){
        ArrayList<Integer> delete_row = new ArrayList<>();
        for(int i = 2; i < 6; i ++){
            boolean flag = true;
            for(int j = 0; j < 4; j ++){
                if(green[i][j] == 0){
                    flag = false;
                    break;
                }
            }
            if(flag){
                answer ++;
                delete_row.add(i);
                for(int k = 0; k < 4; k ++){
                    green[i][k] = 0;
                }
            }
        }
        return delete_row;
    }

    public static ArrayList<Integer> removeBlueCol(){
        ArrayList<Integer> delete_col = new ArrayList<>();
        for(int i = 2; i < 6; i ++){
            boolean flag = true;
            for(int j = 0; j < 4; j ++){
                if(blue[j][i] == 0){
                    flag = false;
                    break;
                }
            }
            if(flag){
                answer ++;
                delete_col.add(i);
                for(int k = 0; k < 4; k ++){
                    blue[k][i] = 0;
                }
            }
        }
        return delete_col;
    }

    public static void changeGreenBoard(ArrayList<Integer> delete_row){
        for(int i = delete_row.size() - 1; i >= 0; i --){
            int row = delete_row.get(i);
            for(int j = row; j >= 1; j --){
                for(int k = 0; k < 4; k ++){
                    green[j][k] = green[j - 1][k];
                }
            }
        }
    }

    public static void changeBlueBoard(ArrayList<Integer> delete_col){
        for(int i = delete_col.size() - 1; i >= 0; i --){
            int col = delete_col.get(i);
            for(int j = col; j >= 1; j --){
                for(int k = 0; k < 4; k ++){
                    blue[k][j] = blue[k][j - 1];
                }
            }
        }
    }

    public static void checkGreenBoardZone(){
        int cnt = 0;
        for(int i = 0; i < 2; i ++){
            for(int j = 0; j < 4; j ++){
                if(green[i][j] == 1){
                    cnt ++;
                    break;
                }
            }
        }

        for(int k = 0; k < cnt; k ++){
            for(int i = 5; i >= 1; i --){
                green[i] = green[i - 1];
            }
            green[0] = new int[] { 0, 0, 0, 0 };
        }
    }

    public static void checkBlueBoardZone(){
        int cnt = 0;
        for(int i = 0; i < 2; i ++){
            for(int j = 0; j < 4; j ++){
                if(blue[j][i] == 1){
                    cnt ++;
                    break;
                }
            }
        }

        for(int k = 0; k < cnt; k ++){
            for(int i = 5; i >= 1; i --){
                for(int j = 0; j < 4; j ++){
                    blue[j][i] = blue[j][i - 1];
                }
            }
            for(int j = 0; j < 4; j ++)
                blue[j][0] = 0;
        }
    }

    public static int getTileNum(){
        int result = 0;
        for(int i = 0; i < 6; i ++){
            for(int j = 0; j < 4; j ++){
                if(green[i][j] == 1){
                    result ++;
                }
            }
        }
        for(int i = 0; i < 4; i ++){
            for(int j = 0; j < 6; j ++){
                if(blue[i][j] == 1){
                    result ++;
                }
            }
        }
        return result;
    }
}
