import java.util.*;
import java.io.*;

public class Main {

    public static class Node{
        int cur;
        Node left, right;
        public Node(int cur) { this.cur = cur; }
    }

    static int maxLevel = -1;
    static int N, cnt = 0;
    static Node[] bt;
    static int[] check, leftLayer, rightLayer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        bt = new Node[N + 1];
        check = new int[N + 1];
        for(int i = 0; i < N; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int cur = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            if(bt[cur] == null) bt[cur] = new Node(cur);
            if(left != -1) {
                check[left] = cur;
                bt[cur].left = new Node(left);
            }
            if(right != -1) {
                check[right] = cur;
                bt[cur].right = new Node(right);
            }
        }

        int idx = -1;
        for(int i = 1; i <= N; i ++){
            if(check[i] == 0){
                idx = i;
                break;
            }
        }

        int maxLayer = findDepth(cnt, idx) + 1;
        check = new int[N + 1];
        leftLayer = new int[maxLayer];
        rightLayer = new int[maxLayer];

        Arrays.fill(leftLayer, 987654321);
        dfs(0, idx);

        int answer = 0;
        int level = maxLayer + 1;
        for(int i = 0; i < maxLayer; i ++){
            if(answer <= (rightLayer[i] - leftLayer[i]) + 1){
                if(answer < (rightLayer[i] - leftLayer[i]) + 1){
                    answer = (rightLayer[i] - leftLayer[i]) + 1;
                    level = i + 1;
                }else if(answer == (rightLayer[i] - leftLayer[i]) + 1){
                    level = Math.min((i + 1), level);
                }
            }
        }

        System.out.println(level + " " + answer);
    }

    public static int findDepth(int depth, int idx){
        if(bt[idx].left != null){
            findDepth(depth + 1, bt[idx].left.cur);
        }

        if(bt[idx].right != null){
            findDepth(depth + 1, bt[idx].right.cur);
        }

        return maxLevel = Math.max(maxLevel, depth);
    }

    public static void dfs(int depth, int idx){
        if(bt[idx].left != null){
            dfs(depth + 1, bt[idx].left.cur);
        }

        cnt ++;
        leftLayer[depth] = Math.min(leftLayer[depth], cnt);
        rightLayer[depth] = Math.max(rightLayer[depth], cnt);
        if(bt[idx].right != null){
            dfs(depth + 1, bt[idx].right.cur);
        }
    }
}