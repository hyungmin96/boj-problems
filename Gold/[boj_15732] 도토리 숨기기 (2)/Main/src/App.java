import java.io.*;
import java.util.StringTokenizer;
public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int[][] roles = new int[k][k];
        for(int i = 0; i < k; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int boxes = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int unit = Integer.parseInt(st.nextToken());
            roles[i] = new int[] { boxes, start, unit };
        }

    }
}
