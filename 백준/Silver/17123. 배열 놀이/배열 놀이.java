import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        for(int i = 0; i < tc; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[][] arr = new int[n][n];
            int[] row = new int[n];
            int[] col = new int[n];
            for(int j = 0; j < n; j ++){
                st = new StringTokenizer(br.readLine());
                for(int k = 0; k < n; k ++){
                    int num = Integer.parseInt(st.nextToken());
                    arr[j][k] = num;
                    col[k] += num;
                    row[j] += num;
                }
            }

            for(int j = 0; j < m; j ++){
                st = new StringTokenizer(br.readLine());
                int r1 = Integer.parseInt(st.nextToken()) - 1;
                int c1 = Integer.parseInt(st.nextToken()) - 1;
                int r2 = Integer.parseInt(st.nextToken()) - 1;
                int c2 = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken());

                for(int r = r1; r <= r2; r ++){
                    row[r] += ((c2 - c1) + 1) * v;
                }

                for(int c = c1; c <= c2; c ++){
                    col[c] += ((r2 - r1) + 1) * v;
                }
            }
            
            for(int j = 0; j < n; j ++){
                sb.append(row[j] + " ");
            }
            sb.append("\n");

            for(int j = 0; j < n; j ++){
                sb.append(col[j] + " ");
            }
            sb.append("\n");

        }

        System.out.println(sb.toString());
    }
}