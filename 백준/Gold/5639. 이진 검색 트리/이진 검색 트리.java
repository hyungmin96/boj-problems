import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        // sol.solution();
        sol.solution();
    }
}

class Solution {

    int N;
    int depth = 1;
    public void solution() throws IOException {
        String s = "";
        ArrayList<Integer> nodes = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while((s = br.readLine()) != null && !s.isEmpty()){
            int key = Integer.parseInt(s);
            nodes.add(key);
        }

        dfs(Integer.MAX_VALUE, nodes.get(0), nodes);
    }

    public void dfs(int p, int start, ArrayList<Integer> nodes){
        if(depth < nodes.size()){
            // 왼쪽 서브트리 구성
            int next = nodes.get(depth);
            if(start > next){
                depth ++;
                dfs(start, next, nodes);
            }
        }

        if(depth < nodes.size()){
            // 오른쪽 서브트리 구성
            int next = nodes.get(depth);
            if(p > next && start < next){
                depth ++;
                dfs(p, next, nodes);
            }
        }

        System.out.println(start);
    }
}
