import java.io.*;
import java.util.*;

public class Main {

    public static class Pair{
        String s;
        int c;
        public Pair(String s, int c){
            this.s = s;
            this.c = c;
        }
    }

    static int[][] dirs = {{ -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }};
    static HashMap<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String board = "";
        for(int r = 0; r < 3; r ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int c = 0; c < 3; c ++){
                board += st.nextToken();
            }
        }
        System.out.println(bfs(board));
    }

    public static int bfs(String cur){
        Queue<Pair> que = new LinkedList<>();
        que.offer(new Pair(cur, 0));
        map.put(cur, 0);

        while(!que.isEmpty()){
            Pair p = que.poll();
            int pos = p.s.indexOf("0");
            if(p.s.equals("123456780")){
                return p.c;
            }

            for(int d = 0; d < 4; d ++){
                int nr = (pos / 3) + dirs[d][0];
                int nc = (pos % 3) + dirs[d][1];

                if(nr < 0 || nc < 0 || nr >= 3 || nc >= 3) continue;
                int next_pos = (nr * 3) + nc;
                
                char[] ch = p.s.toCharArray();
                char temp = p.s.charAt(next_pos);
                ch[next_pos] = '0';
                ch[pos] = temp;
                StringBuilder sb = new StringBuilder();
                for(int k = 0; k < p.s.length(); k ++){
                    sb.append(ch[k]);
                }

                String next = sb.toString();
                if(!map.containsKey(next)){
                    map.put(next, p.c + 1);
                    que.offer(new Pair(next, p.c + 1));
                }
            }
        }
        return -1;
    }
}