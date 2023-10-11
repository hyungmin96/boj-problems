import java.util.*;
import java.io.*;

class Main {

    static int N;
    static int[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        boolean[] check = new boolean[N + 1];

        for(int i = 1; i <= N; i ++) arr[i] = Integer.parseInt(br.readLine());
        for(int i = 1; i <= N; i ++){
            visited = new boolean[N + 1];
            if(find(i, arr[i], check, visited)){
                check[i] = true;
            }
        }
        
        int answer = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i ++){
            if(check[i]) {
                sb.append(i + "\n");
                answer ++;
            }
        }
        System.out.println(answer);
        System.out.println(sb.toString());
    }

    public static boolean find(int s, int a, boolean[] check, boolean[] visited){
        if(visited[a]) return false;
        visited[a] = true;
        if(arr[a] == s) return true;
        return find(s, arr[a], check, visited);
    }
}