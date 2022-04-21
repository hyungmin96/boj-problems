import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {

    private static int M, S;
    private static int[] dx = new int[] { 0,-1, 0, 1, 0 };
    private static int[] dy = new int[] { 0, 0,-1, 0, 1 };

    private static int[] shark_pos = new int[2];
    private static ArrayList<Fish>[][] fish_pos = new ArrayList[4][4];
    private static int[][] smell_pos = new int[4][4];
    private static int number = 0;
    
    public static class Fish{
        int r, c, dir;
        boolean isMoved;
        public Fish(int r, int c, int dir, boolean isMoved){
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.isMoved = isMoved;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        for(int r = 0; r < 4; r ++){
            for(int c = 0; c < 4; c ++){
                fish_pos[r][c] = new ArrayList<>();
            }
        }

        for(int i = 0; i < M; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());
            fish_pos[r][c].add(new Fish(r, c, dir, false));
        }
        st = new StringTokenizer(br.readLine(), " ");
        shark_pos[0] = Integer.parseInt(st.nextToken()) - 1;
        shark_pos[1] = Integer.parseInt(st.nextToken()) - 1;
        System.out.println(solution());
    }

    public static int solution(){
        for(int i = 0; i < S; i ++){
            number = 0;
            ArrayList<Fish> temp = duplicateFishs();
            moveFishs();
            moveShark();
            removeFishSmell();
            implementDuplicatedFishs(temp);
        }
        return getFishNumbers();
    }

    public static void moveShark(){
        int[] dirs = new int[3];
        dfs(0, new int[] { 0, 0, 0 }, dirs);
        int nr = shark_pos[0];
        int nc = shark_pos[1];
        for(int i = 0; i < dirs.length; i ++){
            nr += dx[dirs[i]];
            nc += dy[dirs[i]];
            if(fish_pos[nr][nc].size() > 0)
                smell_pos[nr][nc] = 3;
            fish_pos[nr][nc].clear();
            shark_pos[0] = nr;
            shark_pos[1] = nc;
        }
    }

    public static void dfs(int depth, int[] dir, int[] dirs){
        if(depth == 3){
            int temp = 0;
            boolean change = false;
            boolean[][] visited = new boolean[4][4];
            int nr = shark_pos[0];
            int nc = shark_pos[1]; 
            for(int i = 0; i < dir.length; i ++){
                nr += dx[dir[i]];
                nc += dy[dir[i]];
                if(nr < 0 || nc < 0 || nr >= 4 || nc >= 4){
                    temp = 0;
                    return;
                }
                if(!visited[nr][nc]){
                    visited[nr][nc] = true;
                    temp += fish_pos[nr][nc].size();
                }
            }
            if(temp >= number){
                if(temp == number){
                    if(dirs[0] == 0 && dirs[1] == 0 && dirs[2] == 0)
                        change = true;
                    else if((dir[0] * 100) + (dir[1] * 10) + dir[2] < (dirs[0] * 100) + (dirs[1] * 10) + dirs[2]){
                        change = true;
                    }
                }else if(temp > number){
                    change = true;
                }
            }

            if(change){
                number = temp;
                for(int k = 0; k < dir.length; k ++){
                    dirs[k] = dir[k];
                }
            }
            return;
        }

        for(int d = 1; d < dx.length; d ++){
            dir[depth] = d;
            dfs(depth + 1, dir, dirs);
        }
    }

    public static void moveFishs() {

        int[] mr = new int[] { 0, 0, -1,-1,-1, 0, 1, 1, 1 };
        int[] mc = new int[] { 0,-1, -1, 0, 1, 1, 1, 0,-1 };

        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                if (fish_pos[r][c].size() > 0) {
                    for (int i = fish_pos[r][c].size() - 1; i >= 0; i--) {
                        if (!fish_pos[r][c].get(i).isMoved) {
                            Fish curr = fish_pos[r][c].get(i);
                            Queue<Fish> que = new LinkedList<>();
                            que.offer(curr);

                            while (!que.isEmpty()) {
                                Fish t = que.poll();
                                int dir = t.dir;
                                for (int d = 1; d < mr.length; d++) {
                                    int nr = r + mr[dir];
                                    int nc = c + mc[dir];
                                    if (nr < 0 || nc < 0 || nr >= 4 || nc >= 4 || smell_pos[nr][nc] > 0 || (nr == shark_pos[0] && nc == shark_pos[1])) {
                                        dir --;
                                        if(dir == 0) dir = 8;
                                        continue;
                                    }
                                    t.dir = dir;
                                    fish_pos[nr][nc].add(new Fish(nr, nc, t.dir, true));
                                    fish_pos[r][c].remove(i);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static ArrayList<Fish> duplicateFishs(){
        ArrayList<Fish> temp = new ArrayList<>();
        for(int r = 0; r < 4; r ++){
            for(int c = 0; c < 4; c ++){
                if(fish_pos[r][c].size() > 0){
                    for(int i = 0; i < fish_pos[r][c].size(); i ++){
                        fish_pos[r][c].get(i).isMoved = false;
                        temp.add(new Fish(r, c, fish_pos[r][c].get(i).dir, false));
                    }
                }
            }
        }
        return temp;
    }

    public static void implementDuplicatedFishs(ArrayList<Fish> temp){
        for(int i = 0; i < temp.size(); i ++){
            int r = temp.get(i).r;
            int c = temp.get(i).c;
            fish_pos[r][c].add(temp.get(i));
        }
    }
    
    public static void removeFishSmell(){
        for(int r = 0; r < 4; r ++){
            for(int c = 0; c < 4; c ++){
                if(smell_pos[r][c] > 0){
                    smell_pos[r][c] --;
                }
            }
        }
    }

    public static int getFishNumbers(){
        int ret = 0;
        for(int r = 0; r < 4; r ++){
            for(int c = 0; c < 4; c ++){
                if(fish_pos[r][c].size() > 0){
                    ret += fish_pos[r][c].size();
                }
            }
        }
        return ret;
    }

}
