import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException{
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    public class Shark{
        int r, c, s, d, z;
        public Shark(int r, int c, int s, int d, int z){
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }    
    
    int N, M, K, answer = 0;
    int[][] map, dirs = {{-1, 0},{1,0},{0,1},{0,-1}};
    HashMap<Integer, Shark> hm = new HashMap<>();
    
    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        
        for(int i = 0; i < K; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());
            
            map[r][c] = z;
            hm.put(z, new Shark(r, c, s, d, z));
        }
        
        for(int i = 0; i < M; i ++){
            fishing(i);
            move();
            //System.out.println("=================");
        }

        System.out.println(answer);
    }
    
    public void move(){
        int[][] tmp = new int[N][M + 1];
        ArrayList<Integer> remove_list = new ArrayList<>();
        for(int key : hm.keySet()){
            Shark shk = hm.get(key);
            map[shk.r][shk.c] = 0;
            cal(shk);

            if(tmp[shk.r][shk.c] == 0){
                tmp[shk.r][shk.c] = shk.z;
            }else{
                int remove_size = shk.z;
                if(tmp[shk.r][shk.c] < shk.z){
                    //System.out.println(tmp[shk.r][shk.c] + " shark is eaten by " + shk.z);
                    remove_size = tmp[shk.r][shk.c];
                    tmp[shk.r][shk.c] = shk.z;
                }
                remove_list.add(remove_size);
            }
        }

        for(int z : remove_list){
            hm.remove(z);
        }

        for(int key : hm.keySet()){
            Shark shk = hm.get(key);
            map[shk.r][shk.c] = shk.z;
            //System.out.println(key + "'shark': " + (shk.r + 1) + ", " + (shk.c + 1) + ", " + shk.d);
        }
    }

    public void fishing(int c){
        for(int i = 0; i < N; i ++){
            if(map[i][c] > 0){
                //System.out.println("catch " + map[i][c]);
                answer += hm.get(map[i][c]).z;
                hm.remove(map[i][c]);
                break;
            }
        }
    }

    public int[] cal(Shark s){
        if(s.d == 0 || s.d == 1){
            s.s %= 2 * (N - 1);
            int des = (s.r + (s.s * dirs[s.d][0])) % (2 * (N - 1));
            if(des < 0){
                if(Math.abs(des) < N){
                    s.d = (s.d + 1) % 2;
                    s.r = Math.abs(des);
                }else{
                    s.r = (N - 1) - (Math.abs(des) % (N - 1));
                    //System.out.println();
                }
            }else{
                if(des < N){
                    s.r = des;
                }else{
                    s.d = (s.d + 1) % 2;
                    s.r = (N - 1) - (Math.abs(des) % (N - 1));
                }
            }
        }else{
            s.s %= 2 * (M - 1);
            int des = (s.c + (s.s * dirs[s.d][1])) % (2 * (M - 1));
            if(des < 0){
                if(Math.abs(des) < M){
                    s.d = ((s.d + 1) % 2) + 2;
                    s.c = Math.abs(des);
                }else{
                    s.c = (M - 1) - (Math.abs(des) % (M - 1));
                }
            }else{
                if(des < M){
                    s.c = des;
                }else{
                    s.d = ((s.d + 1) % 2) + 2;
                    s.c = (M - 1) - (Math.abs(des) % (M - 1));
                }
            }
        }

        return new int[] { s.r, s.c };
    }
}

