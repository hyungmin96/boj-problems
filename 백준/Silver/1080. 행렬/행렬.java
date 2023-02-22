import java.io.*;
import java.util.*;

public class Main {

    static char[][] arr1;
    static char[][] arr2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr1 = new char[n][m];
        arr2 = new char[n][m];    

        for(int i = 0; i < n; i ++){
            String str = br.readLine();
            for(int j = 0; j < m; j ++){
                arr1[i][j] = str.charAt(j);
            }
        }

        for(int i = 0; i < n; i ++){
            String str = br.readLine();
            for(int j = 0; j < m; j ++){
                arr2[i][j] = str.charAt(j);
            }
        }

        System.out.println(solution(n, m));
    }

    public static int solution(int n, int m){
        int answer = 0;
        if(n < 3 || m < 3) {
            return isEquals(n, m) ? 0 : -1;
        }

        for(int r = 0; r < n - 2; r ++){
            for(int c = 0; c < m - 2; c ++){
                if(arr1[r][c] != arr2[r][c]){
                    change(r, c);
                    answer ++;
                }
            }
        }
        return !isEquals(n, m) ? -1 : answer;
    }

    public static void change(int i, int j){
        for(int r = 0; r < 3; r ++){
            for(int c = 0; c < 3; c ++){
                if(arr1[r + i][c + j] == '1'){
                    arr1[r + i][c + j] = '0';
                }else{
                    arr1[r + i][c + j] = '1';
                }
            }
        }
    }

    public static boolean isEquals(int n, int m){
        for(int r = 0; r < n; r ++){
            for(int c = 0; c < m; c ++){
                if(arr1[r][c] != arr2[r][c]) return false;
            }
        }
        return true;
    }
}