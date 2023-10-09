import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException{
        Solution sol = new Solution();
        sol.solution();
    }
}
    
class Solution{

    int[] uf;

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = 0;
        while(true){
            tc ++;
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int nodes = Integer.parseInt(st.nextToken());
            int edge = Integer.parseInt(st.nextToken());

            if(nodes == 0 && edge == 0) break;

            uf = new int[nodes + 1];
            for(int i = 1; i <= nodes; i ++) uf[i] = i;
            for(int i = 0; i < edge; i ++){
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                union(a, b);
            }

            HashSet<Integer> set = new HashSet<>();
            for(int i = 1; i <= nodes; i ++){
                int parent = find(uf[i]);
                if(parent > 0){
                    set.add(parent);
                }
            }

            int cnt = set.size();
            if(cnt == 0){
                sb.append(String.format("Case %d: No trees.", tc));
            }else if(cnt == 1){
                sb.append(String.format("Case %d: There is one tree.", tc));
            }else{
                sb.append(String.format("Case %d: A forest of %d trees.", tc, cnt));
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public void union(int a, int b){
        a = find(a);
        b = find(b);

        if(b < a){
            int tmp = a;
            a = b;
            b = tmp;
        }

        if(a == b){
            uf[a] = 0;
        }else{
            uf[b] = a;
        }
    }

    public int find(int a){
        if(a == uf[a]) return a;
        return find(uf[a]);
    }
}