import java.io.*;
import java.util.*;

public class Main {

    public static class Pair{
        int time, end;
        public Pair(int time, int end) { this.time = time; this.end = end; }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        Pair[] arr = new Pair[n];
        for(int i = 0; i < n; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int time = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            arr[i] = new Pair(time, end);
        }

        Arrays.sort(arr, new Comparator<Pair>(){
            @Override
            public int compare(Pair p1, Pair p2){
                return p1.end - p2.end;
            }
        });

        System.out.println(binarySearch(arr));
    }

    public static int binarySearch(Pair[] arr){
        int answer = -1;
        int left = 1, right = 1000001;
        while(left <= right){
            int mid = (right + left) / 2;
            if(isAvailable(mid, arr)){
                answer = Math.max(mid, answer);
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return answer;
    }

    public static boolean isAvailable(int mid, Pair[] arr){
        for(Pair p : arr){
            mid += p.time;
            if(mid > p.end) return false;
        }
        return true;
    }
}