import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int N;

    public class Pair{
        int idx;
        String str;
        public Pair(int idx, String str){
            this.idx = idx;
            this.str = str;
        }
    }

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        Pair[] arr = new Pair[N];
        String[] order = new String[N];
        for(int i = 0; i < N; i ++){
            String str = br.readLine();
            arr[i] = new Pair(i, str);
            order[i] = str;
        }
        
        Arrays.sort(arr, new Comparator<>(){
             @Override
            public int compare(Pair p1, Pair p2){
                if(p1.str.compareTo(p2.str) == 0)
                    return p1.idx - p2.idx;

                return p1.str.compareTo(p2.str);
            }
        });

        String prefix = "";
        int prefix_idx = 987654321;
        for(int i = 0; i < N - 1; i ++){
            Pair cur = arr[i];
            Pair next = arr[i + 1];
            int idx = (cur.idx < next.idx) ? cur.idx : next.idx;

            String cur_prefix = count(cur.str, next.str);
            if(cur_prefix.length() > prefix.length()){
                prefix_idx = idx;
                prefix = cur_prefix;
            }else if(cur_prefix.length() == prefix.length()){
                if(prefix_idx > idx){
                    prefix_idx = idx;
                    prefix = cur_prefix;
                }
            }
        }

        int cnt = 0;
        HashSet<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for(String o : order){
            if(cnt == 2) break;
            if(o.length() < prefix.length() || set.contains(o)) continue;
            if(o.substring(0, prefix.length()).equals(prefix)){
                set.add(o);
                sb.append(o + "\n");
                cnt ++;
            }
        }

        System.out.println(sb.toString());
    }

    public String count(String str1, String str2){ 
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < Math.min(str1.length(), str2.length()); i ++){
            if(str1.charAt(i) != str2.charAt(i)){
                return sb.toString();
            }
            sb.append(str1.charAt(i));
        }
        return sb.toString();
    }
}

