import java.io.*;
import java.util.*;

public class Main {

    static int G, P;
    static int[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());
        check = new int[G + 1];

        int answer = 0;
        int[] arr = new int[P + 1];
        boolean[] visited = new boolean[G + 1];
        for(int i = 0; i < P; i ++) arr[i] = Integer.parseInt(br.readLine()); 
        for(int i = 0; i <= G; i ++) check[i] = i;

        for(int i = 0; i < P; i ++){
            int r = find(arr[i]);
            if(r == 0) break;
            if(!visited[r]){
                visited[r] = true;
                union(r - 1, r);
                answer ++;
            }else break;
        }
        System.out.println(answer);
    }

    public static void union(int a, int b){
        a = find(a);
        b = find(b);
        check[b] = a;
    }

    public static int find(int a){
        if(check[a] == a) return a;
        return check[a] = find(check[a]);
    }
}