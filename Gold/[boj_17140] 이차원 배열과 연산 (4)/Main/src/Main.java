import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
public class Main {

    public static class Info{
        int length; int[] arr;
        public Info(int length, int[] arr){
            this.length = length;
            this.arr = arr;
        }
    }

    public static class Pair implements Comparable<Pair>{
        int number, count;
        public Pair(int number, int count){
            this.number = number;
            this.count = count;
        }

        public int compareTo(Pair o){
            if(this.count != o.count) return this.count - o.count;
            return this.number - o.number;
        }
    }

    private static int r, c, k;
    private static int[][] board;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[101][101];
        for(int i = 1; i <= 3; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 1; j <= 3; j ++){
                board[i][j] = Integer.parseInt(st.nextToken()); 
            }
        }
        System.out.println(solution());
    }

    public static int solution(){
        int curr_row = 3;
        int curr_col = 3;
        for(int i = 0; i <= 100; i ++){
            if(board[r][c] == k) {
                return i;
            }
            else{
                if(curr_row >= curr_col)
                    curr_col = rOperation(curr_row);
                else 
                    curr_row = cOperation(curr_col);
            }
        }
        return -1;
    }

    public static int rOperation(int curr_row){
        int result = 0;
        for(int i = 1; i <= curr_row; i ++){
            Info info = generate(board[i]);
            board[i] = info.arr;
            result = result > info.length ? result : info.length;
        }

        return result;
    }

    public static int cOperation(int curr_col){
        int result = 0;
        int[] arr = new int[101];

        for(int i = 1; i <= curr_col; i ++){
            for(int j = 1; j <= 100; j ++)
                arr[j] = board[j][i];

            Info info = generate(arr);
            arr = info.arr;
            result = result > info.length ? result : info.length;
            for(int k = 1; k < arr.length; k ++){
                board[k][i] = arr[k];
            }
        }

        return result;
    }

    public static Info generate(int[] arr){
        Arrays.sort(arr);
        int index = 0;
        int curr_number = arr[0];
        int cnt = 0;
        ArrayList<Pair> arrList = new ArrayList<>();
        while(index != 101){
            int next_number = index >= 100 ? curr_number : arr[index + 1];
            if(index + 1 == 101 || curr_number != next_number){
                if(curr_number != 0)
                    arrList.add(new Pair(curr_number, cnt));
                curr_number = next_number;
                cnt = 0;
            }
            cnt ++;
            index ++;
        }

        Collections.sort(arrList);
        int change = 0;
        for(int i = 0; i < arrList.size(); i ++){
            Pair curr = arrList.get(i);
            arr[(i * 2) + 1] = curr.number;
            arr[(i * 2) + 2] = curr.count;
            change = (i * 2) + 3;
        }

        for(int i = change; i < 101; i ++){
            arr[i] = 0;
        }

        return new Info(change - 1, arr);
    }
    
}
