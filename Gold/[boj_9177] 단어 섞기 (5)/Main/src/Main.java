import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
public class Main {

    private static StringBuilder sb = new StringBuilder("");
    private static boolean find;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Set<Character> set = new HashSet<>();

        for(int i = 0; i < n; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String str1 = st.nextToken();
            String str2 = st.nextToken();
            String str3 = st.nextToken();
            
            for(int j = 0; j < Math.max(str1.length(), str2.length()); j ++){
                if(j < str1.length()) set.add(str1.charAt(j));
                if(j < str2.length()) set.add(str2.charAt(j));
            }

            boolean flag = true;
            for(char c : str3.toCharArray()){
                if(!set.contains(c)){
                    flag = false;
                    break;
                }
            }

            find = false;
            if(flag){
                solution(str1, str2, str3, i + 1, 0, 0 , "");
                String str = (find) ? "yes" : "no";
                sb.append("Data set " + (i + 1) + ": " + str + "\n");
            }
            else
                sb.append("Data set " + (i + 1) + ": no\n");
        }
        System.out.println(sb.toString());
    }

    public static void solution(String str1, String str2, String str3, int index, int idx1, int idx2, String total){
        if(find) return;
        if(idx1 + idx2 == str3.length()){
            if(total.equals(str3)){
                find = true;
            }
            return;
        }else{
            if(idx1 < str1.length() && str1.charAt(idx1) == str3.charAt(idx1 + idx2))
                solution(str1, str2, str3, index, idx1 + 1, idx2, total + str1.charAt(idx1));
            if(idx2 < str2.length() && str2.charAt(idx2) == str3.charAt(idx1 + idx2))
                solution(str1, str2, str3, index, idx1, idx2 + 1, total + str2.charAt(idx2));
        }
    }
}
