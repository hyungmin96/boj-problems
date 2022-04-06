import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {

    public static class Info{
        int durability;
        boolean state;
        public Info(int durability, boolean state){
            this.durability = durability;
            this.state = state;
        }
    }

    private static int N, K;
    private static Info[][] containers;
    private static int[] dx = new int[] {-1, 0, 1, 0 };
    private static int[] dy = new int[] { 0, 1, 0,-1 };
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        containers = new Info[2][N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < 2; i ++){
            for(int j = 0; j < N; j ++){
                if(i == 0){
                    containers[i][j] = new Info(Integer.parseInt(st.nextToken()), false);
                }else{
                    containers[i][N - 1- j] = new Info(Integer.parseInt(st.nextToken()), false);
                }
            }
        }
        System.out.println(solution());
    }

    public static int solution(){
        int answer = 1;
        while(true){
            spin();
            moveRobots();
            putRobots();
            if(check()) return answer; else answer ++;
        }
    }

    public static void putRobots(){
        if(!containers[0][0].state && containers[0][0].durability > 0){
            containers[0][0].state = true;
            containers[0][0].durability -= 1;
        }
    }

    public static void moveRobots(){
        for(int i = N - 1; i >= 1; i --){
            if(containers[0][i].state && !containers[0][i + 1].state && containers[0][i + 1].durability > 0){
                containers[0][i].state = false;
                containers[0][i + 1].state = true;
                containers[0][i + 1].durability -= 1;
            }
        }
        containers[0][N - 1].state = false;
    }

    public static void spin(){
        int cr = 0;
        int cc = 0;
        int index = 1;
        Queue<Info> que = new LinkedList<>();
        que.offer(containers[cr][cc]);
        
        while(true){
            int nr = cr + dx[index % dx.length];
            int nc = cc + dy[index % dx.length];
            que.offer(containers[nr][nc]);
            containers[nr][nc] = que.poll();
            if((nr == 0 && nc == N - 1) || (nr == 1 && nc == N - 1) || (nr == 1 && nc == 0)){
                index ++;
            }
            if(nr == 0 && nc == 0){
                containers[0][N - 1].state = false;
                break;
            }
            cr = nr;
            cc = nc;
        }
    }

    public static boolean check(){
        int result = 0;
        for(int i = 0; i < 2; i ++)
            for(int j = 0; j < N; j ++){
                if(containers[i][j].durability == 0)
                    result ++;
            }
        
        return result >= K;
    }
}

