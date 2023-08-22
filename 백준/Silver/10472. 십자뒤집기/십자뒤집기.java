import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    public class Pair{
        int cnt;
        String str;
        public Pair(int cnt, String str){
            this.cnt = cnt;
            this.str = str;
        }
    }
    int P;
    HashSet<String> v = new HashSet<>();
    int[][] dirs = { { 0, 0 }, { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, - 1}};

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        P = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < P; i ++){
            v = new HashSet<>();
            StringBuilder sb2 = new StringBuilder();
            for(int j = 0; j < 3; j ++)
                sb2.append(br.readLine());

            sb.append(bfs(sb2.toString()) + "\n");
        }
        System.out.println(sb.toString());
    }

    public int bfs(String str){
       Queue<Pair> q = new LinkedList<>();
       q.offer(new Pair(0, str));
       v.add(str);

       while (!q.isEmpty()) {
           Pair p = q.poll();
           if(p.str.equals(".........")){
                return p.cnt;
           }

           for(int i = 0; i < 9; i ++){
               int r = i / 3;
               int c = i % 3;
               char[] tmp = p.str.toCharArray();
               for (int d = 0; d < 5; d ++) {
                   int nr = r + dirs[d][0];
                   int nc = c + dirs[d][1];
                   if (nr < 0 || nc < 0 || nr >= 3 || nc >= 3) continue;
                   tmp[nr * 3 + nc] = tmp[nr * 3 + nc] == '.' ? '*' : '.';
                }
                if(v.contains(convert(tmp))) continue;
                v.add(convert(tmp));
                q.offer(new Pair(p.cnt + 1, convert(tmp)));
           }
       }
       return 987654321;
    }

    public String convert(char[] tmp){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < tmp.length; i ++)
            sb.append(tmp[i]);
        return sb.toString();
    }
}