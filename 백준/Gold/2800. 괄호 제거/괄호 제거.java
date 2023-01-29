import java.io.*;
import java.util.*;

public class Main {

    static HashSet<String> set = new HashSet<>();
    static boolean[] check;
    static String str = "";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();

        ArrayList<int[]> pos = new ArrayList<>();
        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < str.length(); i ++){
            if(str.charAt(i) == '(') st.push(i);
            else if(str.charAt(i) == ')'){
                pos.add(new int[] { st.pop(), i });
            }
        }

        for(int i = 1; i <= pos.size(); i ++){
            dfs(i, 0, 0, str.toCharArray(), pos);
        }

        ArrayList<String> order = new ArrayList<>();
        for(String s : set){
            order.add(s);
        }

        StringBuilder sb = new StringBuilder();
        Collections.sort(order, new Comparator<>(){
            @Override
            public int compare(String s1, String s2){ return s1.compareTo(s2); }
        });

        for(String s : order)
            sb.append(s + "\n");

        System.out.println(sb.toString());
    }

    public static void dfs(int n, int depth, int cnt, char[] ch, ArrayList<int[]> pos){
        if(depth == n){
            StringBuilder sb = new StringBuilder();
            for(char c : ch){
                if(c != '\0')
                    sb.append(c);
            }
            if(!str.equals(sb.toString()))
                set.add(sb.toString());
            return;
        }

        int s = pos.get(depth)[0];
        int e = pos.get(depth)[1];

        char t1 = ch[s];
        char t2 = ch[e];
        ch[s] = '\0';
        ch[e] = '\0';
        dfs(n, depth + 1, cnt + 1, ch, pos);
        ch[s] = t1;
        ch[e] = t2;
        dfs(n, depth + 1, cnt, ch, pos);
    }
}