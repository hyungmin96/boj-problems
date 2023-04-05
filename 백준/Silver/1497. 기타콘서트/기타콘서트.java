import java.io.*;
import java.util.*;

public class Main {

    public static class Pair{
        String guitar, available;
        public Pair(String guitar, String available){ this.guitar = guitar; this.available = available; }
    }

    static int N, M, answer = 0, play = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Pair[] p = new Pair[N];

        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            p[i] = new Pair(st.nextToken(), st.nextToken());
        }

        char[] temp = new char[M];
        for(int i = 0; i < M; i ++) temp[i] = 'N';
        dfs(0, 0, 0, temp, p);
        System.out.println(play == 0 ? - 1 : play);
    }

    public static void dfs(int depth, int idx, int cnt, char[] arr, Pair[] p){
        if(cnt >= answer){
            if(cnt > answer){
                play = depth;
            }else if(cnt == answer){
                play = Math.min(depth, play);
            }
            answer = cnt;
        }

        for(int i = idx; i < N; i ++){
            int temp = 0;
            char[] org = new char[M];
            org = arr.clone();
            for(int j = 0; j < M; j ++){
                if(arr[j] == 'N' && p[i].available.charAt(j) == 'Y'){
                    temp ++;
                    arr[j] = 'Y';
                }
            }

            dfs(depth + 1, i + 1, cnt + temp, arr, p);
            arr = new char[M];
            arr = org.clone();
        }
    }
}