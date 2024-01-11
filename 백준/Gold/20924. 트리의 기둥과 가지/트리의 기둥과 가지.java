import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int N, R;
    boolean[] v;
    ArrayList<int[]>[] vertex;

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        v = new boolean[N + 1];
        vertex = new ArrayList[N + 1];
        for(int i = 1; i <= N; i ++){
            vertex[i] = new ArrayList<>();
        }

        for(int i = 0; i < N - 1; i ++){
            st = new StringTokenizer(br.readLine(), " ");

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            vertex[from].add(new int[] { to, weight });
            vertex[to].add(new int[] { from, weight });
        }

        v[R] = true;
        int[] giga = findGigaNode(R, 0);
        int len = findMaxLen(giga[0], 0);

        System.out.println(giga[1] + " " + len);
    }

    public int findMaxLen(int giga, int weight){
        int len = weight;
        for(int[] next : vertex[giga]){
            if(v[next[0]]) continue;
            v[next[0]] = true;
            len = Math.max(len, findMaxLen(next[0], weight + next[1]));
        }
        return len;
    }

    public int[] findGigaNode(int root, int len){
        int size = vertex[root].size();
        if((root == R && size > 1) || (root != R && size > 2)){
            return new int[] { root, len };
        }
        for(int[] next : vertex[root]){
            if(v[next[0]]) continue;
            v[next[0]] = true;
            return findGigaNode(next[0], len + next[1]);
        }
        return new int[] { root, len };
    }
}

