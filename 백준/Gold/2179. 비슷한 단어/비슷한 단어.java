import java.io.*;
import java.util.*;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] arr = new String[N];
        for(int i = 0; i < N; i ++) arr[i] = br.readLine();

        int max = 0;
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j ++){
                if(arr[i].equals(arr[j])) continue;
                int temp = 0, min = 0;
                min = (arr[i].length() > arr[j].length()) ? arr[j].length() : arr[i].length();
                for(int k = 0; k < min; k ++){
                    if(arr[i].charAt(k) != arr[j].charAt(k)) break;
                    else temp ++;
                }
                if(max <= temp){
                    if(max < temp) list.clear();
                    list.add(arr[i]);
                    list.add(arr[j]);
                    max = temp;
                }
            }
        }

        System.out.println(list.get(0));
        System.out.println(list.get(1));
    }
}
