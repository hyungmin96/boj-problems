import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> arr = new ArrayList<>();
        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < n; i ++){
            arr.add(Integer.parseInt(stz.nextToken()));
        }

        int k = Integer.parseInt(br.readLine());
        ArrayList<Integer> arr2 = new ArrayList<>();
        stz = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < k; i ++){
            arr2.add(Integer.parseInt(stz.nextToken()));
        }

        Collections.sort(arr, Collections.reverseOrder());
        Collections.sort(arr2, Collections.reverseOrder());

        System.out.println(solution(arr, arr2));
        
    }

    public static int solution(ArrayList<Integer> arr, ArrayList<Integer> arr2){

        int answer = 0;

        if(arr.get(0) < arr2.get(0)) return - 1;
        while(arr2.size() != 0){
            int index = 0;
            for(int i = 0; i < arr.size();){
                if(index == arr2.size()) break;
                if(arr.get(i) >= arr2.get(index)){
                    arr2.remove(index);
                    i ++;
                }else{
                    index ++;
                }
            }
            answer ++;
        }

        return answer;
    }
}
