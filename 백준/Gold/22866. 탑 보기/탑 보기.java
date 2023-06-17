import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] arr;
    static int[][] b, idx; // building

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        b = new int[2][N]; // 0 : left, 1 : right
        idx = new int[2][N];
        arr = new int[N];
        for(int i = 0; i < N; i ++){
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
        }
        
        leftSight();
        rightSight();

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i ++){
            int t = b[0][i] + b[1][i];
            int tIdx = 999999;

            if(idx[0][i] == 999999){
                tIdx = idx[1][i];
            }else if(idx[1][i] == 999999){
                tIdx = idx[0][i];
            }else if(i - idx[0][i] <= idx[1][i] - i){
                tIdx = idx[0][i];
            }else{
                tIdx = idx[1][i];
            }

            if(t == 0)
                sb.append(t + " " + "\n");
            else
                sb.append(t + " " + (tIdx + 1) + "\n");
        }

        System.out.println(sb.toString());
    }

    public static void leftSight(){
        Stack<int[]> stk = new Stack<>(); // idx, height
        for(int i = 0; i < N; i ++){
            if(stk.isEmpty()){
                b[0][i] = 0;
                idx[0][i] = 999999;
            }else{
                int h = arr[i];
                while(!stk.isEmpty() && stk.peek()[1] <= h){
                    stk.pop();
                }

                b[0][i] = stk.size();
                if(!stk.isEmpty()){
                    idx[0][i] = stk.peek()[0];
                }else{
                    idx[0][i] = 999999;
                }
            }

            stk.push(new int[] { i, arr[i] });
        }
    }

    public static void rightSight(){
        Stack<int[]> stk = new Stack<>(); // idx, height
        for(int i = N - 1; i >= 0; i --){
            if(stk.isEmpty()){
                b[1][i] = 0;
                idx[1][i] = 999999;
            }else{
                int h = arr[i];
                while(!stk.isEmpty() && stk.peek()[1] <= h){
                    stk.pop();
                }

                b[1][i] = stk.size();
                if(!stk.isEmpty()){
                    idx[1][i] = stk.peek()[0];
                }else{
                    idx[1][i] = 999999;
                }
            }

            stk.push(new int[] { i, arr[i] });
        }
    }
}