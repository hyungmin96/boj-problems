import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int[] dx = new int[] {-1,-1, 0, 1, 1, 1, 0,-1 };
    private static int[] dy = new int[] { 0, 1, 1, 1, 0,-1,-1,-1 };

    public static class Tree implements Comparable<Tree>{
        int x; int y; int age;
        public Tree(int x, int y, int age){
            this.x = x;
            this.y = y;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o){
            return this.age - o.age;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] board = new int[N][N];
        int[][] addition = new int[N][N];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                board[i][j] = 5;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                addition[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Tree> trees = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            Tree tree = new Tree(row, col, age);
            trees.add(tree);
        }

        System.out.println(solution(N, M, K, trees, board, addition));
    }

    public static int solution(int N, int M, int K, Queue<Tree> trees, int[][] board, int[][] addition) {
        
        Queue<Tree> dead_trees = new LinkedList<>();

        for(int i = 0; i < K; i ++){
            int size = trees.size();
            for(int k = 0; k < size; k ++){
                Tree tree = trees.poll();
                if(board[tree.x][tree.y] >= tree.age){
                    board[tree.x][tree.y] -= tree.age;
                    trees.offer(new Tree(tree.x, tree.y, tree.age + 1));
                }else{
                    dead_trees.offer(new Tree(tree.x, tree.y, tree.age / 2));
                }
            }

            while(!dead_trees.isEmpty()){
                Tree tree = dead_trees.poll();
                board[tree.x][tree.y] += tree.age;
            }

            ArrayList<Tree> parents = new ArrayList<>();
            size = trees.size();
            for(int x = 0; x < size; x ++){
                Tree tree = trees.poll();
                parents.add(tree);
                if(tree.age % 5 == 0){
                    for(int j = 0; j < dx.length; j ++){
                        int next_row = tree.x + dx[j];
                        int next_col = tree.y + dy[j];
                        if(next_row < 0 || next_col < 0 || next_row >= N || next_col >= N) continue;
                        trees.offer(new Tree(next_row, next_col, 1));
                    }
                }
            }

            for(Tree item : parents)
                trees.offer(item);

            for(int x = 0; x < N; x ++){
                for(int y = 0; y < N; y ++){
                    board[x][y] += addition[x][y];
                }
            }

        }

        return trees.size();
    }

}
