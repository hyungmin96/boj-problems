import java.io.*;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> items = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < n; i ++){
            int k = Integer.parseInt(br.readLine());
            items.add(k);
        }

        for(String item : solution(n, items)){
            for(int i = 0; i < item.toCharArray().length; i ++){
                if(item.charAt(i) == '1'){
                    System.out.print(i + " ");
                }
            }
            System.out.println();
        }
    }

    public static ArrayList<String> solution(int n, ArrayList<Integer> items){
        ArrayList<String> answer = new ArrayList<>();
        
        for(int i = 0; i < items.size(); i ++){
            StringBuilder sb = new StringBuilder();
            int value = items.get(i);
            while(value != 0){
                if(value % 2 != 0){
                    sb.append("1");
                }else{
                    sb.append("0");
                }
                value /= 2;
            }
            answer.add(sb.toString());
        }

        return answer;
    }
}
