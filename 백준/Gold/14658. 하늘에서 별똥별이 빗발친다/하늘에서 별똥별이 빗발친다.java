import java.io.*;
import java.util.*;
public class Main {

    static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    static int N, M, L, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Point[] stars = new Point[K];
        for(int i = 0; i < K; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            stars[i] = new Point(r, c);
        }

        int answer = 0;
        for(int i = 0; i < K; i ++){
            for(int j = 0; j < K; j ++){
                answer = Math.max(answer, check(stars[i].r, stars[j].c, stars));
            }
        }
        System.out.println(K - answer);
    }

    public static int check(int r, int c, Point[] stars){
        int ret = 0;
        for(int i = 0; i < stars.length; i ++){
            if(r <= stars[i].r && r + L >= stars[i].r && c <= stars[i].c && c + L >= stars[i].c)
                ret ++;
        }
        return ret;
    }
}