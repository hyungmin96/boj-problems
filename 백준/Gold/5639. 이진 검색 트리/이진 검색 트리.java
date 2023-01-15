import java.io.*;
import java.util.*;

public class Main {

    public static class Node{
        int val;
        Node left = null, right = null;
        public Node(int val) { this.val = val; }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = null;
        Node root = new Node(Integer.parseInt(br.readLine()));
        while((str = br.readLine()) != null && !str.equals("")){
            int num = Integer.parseInt(str);
            create(root, num);
        }

        dfs(root);
    }

    public static void dfs(Node root){
        if(root.left != null) dfs(root.left);
        if(root.right != null) dfs(root.right);
        System.out.println(root.val);
    }

    public static void create(Node parent, int val){
        if(parent.val > val){
            if(parent.left == null)
                parent.left = new Node(val);
            else create(parent.left, val);
        }else{
            if(parent.right == null)
                parent.right = new Node(val);
            else create(parent.right, val);
        }
    }
}