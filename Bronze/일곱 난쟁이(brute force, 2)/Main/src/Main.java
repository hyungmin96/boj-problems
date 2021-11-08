import java.io.*;
import java.util.Arrays;
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] members = new int[9];
        for(int i = 0; i < 9; i ++){
            members[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(members);
        for(int item : solution(members)){
            if(item != 0){
                System.out.println(item);
            }
        };
    }

    public static int[] solution(int[] members){

        int totals = Arrays.stream(members).sum();
        boolean isFind = false;
        
        for(int i = 0; i < 9; i ++){
            if(isFind) break;
            for(int j = 0; j < 9; j ++){
                if(i != j){
                    if(totals - members[i] - members[j] == 100){
                        members[i] = 0;
                        members[j] = 0;
                        isFind = true;
                        break;
                    }
                }
            }
        }

        return members;
    }
}
