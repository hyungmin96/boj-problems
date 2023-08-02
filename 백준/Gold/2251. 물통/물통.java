import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    public int A, B, C;
    public boolean[][] v;
    public HashSet<Integer> set = new HashSet<>();

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        v = new boolean[201][201];
        dfs(0, 0, C);

        int idx = 0;
        int[] arr = new int[set.size()];
        for(int i : set){
            arr[idx ++] = i;
        }
        Arrays.sort(arr);
        for(int i : arr){
            System.out.print(i + " ");
        }
    }

    public void dfs(int a, int b, int c){
        if(v[a][b]) return;
        v[a][b] = true;

        if(a == 0)
            set.add(c);
        // A가 B에게 물을 줄 때
        if(a + b > B) dfs(a + b - B, B, c);
        else dfs(0, a + b, c);

        // A가 C에게 물을 줄 때
        if(a + c > C) dfs(a + c - C, b, C);
        else dfs(0, b, a + c);

        // B가 A에게 물을 줄 때
        if(a + b > A) dfs(A, a + b - A, c);
        else dfs(a + b, 0, c);

        // B가 C에게 물을 줄 때
        if(b + c > C) dfs(a, b + c - C, C);
        else dfs(a, 0, b + c);

        // C가 A에게 물을 줄 때
        if(a + c > A) dfs(A, b, a + c - A);
        else dfs(a + c, b, 0);

        // C가 B에게 물을 줄 때
        if(b + c > B) dfs(a, B, b + c - B);
        else dfs(a, b + c, 0);
    }
}