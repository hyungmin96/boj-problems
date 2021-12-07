import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> arrList = new ArrayList<>();
        while(true){
            String str = br.readLine();
            if(str.equals("*")){
                break;
            }else{
                arrList.add(str);
            }
        }
        solution(arrList);
    }

    public static void solution(ArrayList<String> arrList){

        for(String item : arrList){
            if(item.length() == 1){
                System.out.println(item + " is surprising.");
            }else{
                boolean isSurprising = true;
                for(int i = 0; i < item.length() - 2; i ++){
                    HashSet<String> set = new HashSet<>();
                    for(int j = 0; j < item.length() - 1 - i; j ++){
                        StringBuilder sb = new StringBuilder();
                        sb.append(item.charAt(j) + "" + item.charAt(j + i + 1));
                        if(!set.contains(sb.toString())){
                            set.add(sb.toString());
                        }else{
                            isSurprising = false;
                            System.out.println(item + " is NOT surprising.");
                            break;
                        }
                    }
                    if(!isSurprising) break;
                }
                if(isSurprising) System.out.println(item + " is surprising.");
            }
        }
    }
}
