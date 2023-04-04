import java.io.*;
import java.util.*;

public class Main {

    static int cnt = 0;
    static int N, R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        dfs(R, C, N);
        System.out.println(cnt);
    }

    public static void dfs(int r, int c, int k){
        if(k == 0){
            return;
        }

        int size = (int)Math.pow(2, k - 1);
        if(r < size && c < size){
            dfs(r, c, k - 1);
        }else if(r < size && c < size * 2){
            cnt += size * size;
            dfs(r, c - size, k - 1);
        }else if(r < size * 2 && c < size){
            cnt += (size * size) * 2;
            dfs(r - size, c, k - 1);
        }else if(r < size * 2 && c < size * 2){
            cnt += (size * size) * 3;
            dfs(r - size, c - size, k - 1);
        }
    }
}