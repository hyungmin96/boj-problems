import java.io.*;
import java.util.Arrays;

public class Main {

    private static String str1 = "";
    private static String str2 = "";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str1 = br.readLine();
        str2 = br.readLine();

        System.out.println(solution());
    }

    public static int solution(){
        int answer = 0;
        int len = str1.length() - 1;
        if(str1.length() != str2.length()) return -1;

        int[] arr1 = new int[str1.length()];
        int[] arr2 = new int[str2.length()];

        for(int i = 0; i < arr1.length; i ++){
            arr1[i] = str1.charAt(i) - 'A';
            arr2[i] = str2.charAt(i) - 'A';
        }
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        for(int i = 0; i < arr1.length; i ++)
            if(arr1[i] != arr2[i]) 
                return -1;

        for(int i = str1.length() - 1; i >= 0; i --){
            if(str1.charAt(i) == str2.charAt(len - answer)){
                answer ++;
            }
        }

        return (len + 1) - answer;
    }
}
