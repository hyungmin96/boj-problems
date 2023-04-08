import java.io.*;
import java.util.*;

public class Main {

    static boolean[] check;
    static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        check = new boolean[str.length()];

        char[] arr = new char[str.length()];
        dfs(0, str.length(), str, arr);
        System.out.println(sb.toString());
    }

    public static void dfs(int p_pos, int cur_pos, String str, char[] arr){
        int idx = -1;
        char temp = 'Z' + 2;
        for(int i = p_pos; i < cur_pos; i ++){
            if(!check[i] && temp > str.charAt(i)){
                temp = str.charAt(i);
                idx = i;
            }
        }
        if(idx != -1){
            check[idx] = true;
            arr[idx] = str.charAt(idx);
            for(char c : arr){
                if(c != '\0'){
                    sb.append(c);
                }
            }
            sb.append("\n");

            dfs(idx, cur_pos, str, arr);
            dfs(p_pos, idx, str, arr);
        }
    }
}