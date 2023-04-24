import java.io.*;
import java.util.*;

public class Main {

    static int N;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] arr = new int[N], org = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i ++){
            org[i] = i + 1;
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= 9; i ++){
            Stack<Integer> stack = new Stack<>();
            dfs(i, 0, new Stack<>(), new Stack<>(), stack, org);
            int idx = 0;
            int[] temp = new int[N];
            while(!stack.isEmpty()){
                temp[idx ++] = stack.pop();
            }
            for(int j = 1; j <= 9; j ++){
                Stack<Integer> stack2 = new Stack<>();
                dfs(j, 0, new Stack<>(), new Stack<>(), stack2, temp);
                if(check(arr, stack2)){
                    System.out.println(i + " " + j);
                    return;
                }
            }
        }
    }

    public static void dfs(int k, int depth, Stack<Integer> st1, Stack<Integer> st2, Stack<Integer> st3, int[] arr){
        if(depth == k + 1){
            while(!st2.empty()) st3.push(st2.pop());
            return;
        }

        st1.clear();
        if(st2.isEmpty()){
            for(int i = 0; i < arr.length; i ++){
                st1.push(arr[i]);
            }
        }else{
            while(!st2.isEmpty()){
                st1.push(st2.pop());
            }
        }

        int cur = (int)Math.pow(2, k - depth);
        for(int i = 0; i < cur; i ++){
            if(!st1.isEmpty())
                st2.push(st1.pop());
        }

        while(!st1.empty()) st3.push(st1.pop());

        dfs(k, depth + 1, st1, st2, st3, arr);
    }

    public static boolean check(int[] arr, Stack<Integer> st){
        for(int i = 0; i < arr.length; i ++){
            if(st.isEmpty() || arr[i] != st.pop()) return false;
        }
        return true;
    }
}