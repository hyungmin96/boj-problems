import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int[] len = new int[m + 1];
        for(int i = 0; i < m; i ++){
            len[i] = Integer.parseInt(br.readLine());
        }
        len[m] = l;

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i ++){
            int answer = 0; 
            int cut = Integer.parseInt(br.readLine());
            int left = 0;
            int right = l;
            while(left <= right){
                int mid = (right + left) / 2;
                if(check(mid, cut, len)){
                    answer = Math.max(answer, mid);
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
            sb.append(answer + "\n");
        }

        System.out.println(sb.toString());
    }

    public static boolean check(int mid, int part, int[] len){
        int cnt = 0;
        int prev = 0;
        for(int i = 0; i < len.length; i ++){
            if(len[i] - prev >= mid) {
                cnt ++;
                prev = len[i];
            }
        }
        return cnt > part ? true : false;
    }
}