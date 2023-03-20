import java.io.*;
import java.util.*;

public class Main {

    static int[] p;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long answer = 0;

        p = new int[n + 1];
        Arrays.fill(p, -1);
        for(int i = 1; i <= n; i ++) p[i] = i;
        for(int i = 0; i < m; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());

            if(!union(to, from)) answer ++;
        }
        
        HashSet<Integer> hs = new HashSet<>();
        for(int i = 1; i <= n; i ++){
            hs.add(find(i));
        }

        answer += hs.size() - 1;
        System.out.println(answer);
    }

    public static boolean union(int a, int b){
        a = find(a);
        b = find(b);

        if(a == b) return false;
        if(a > b)
            p[a] = b;
        else
            p[b] = a;
        return true;
    }

    public static int find(int a){
        if(a == p[a]) return a;
        return p[a] = find(p[a]);
    }
}