import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
public class Main {

    public static class Seat implements Comparable<Seat>{
        int id, r, c, empty, close;
        int[] favorite;
        public Seat(int id, int r, int c, int empty, int close, int[] favorite){
            this.id = id;
            this.r = r;
            this.c = c;
            this.empty = empty;
            this.close = close;
            this.favorite = favorite;
        }

        @Override
        public int compareTo(Seat o){
            if(this.close != o.close) return o.close - this.close;
            else if(this.empty != o.empty) return o.empty - this.empty;
            else if(this.r != o.r) return this.r - o.r;
            else return this.c - o.c;
        }
    }

    private static int N;
    private static Seat[] student;
    private static int[][] board;
    private static int[] dx = new int[] {-1, 0, 1, 0 };
    private static int[] dy = new int[] { 0, 1, 0,-1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        student = new Seat[(N * N) + 1];
        board = new int[N][N];
        ArrayList<Integer> orders = new ArrayList<>();
        for(int i = 0; i < N * N; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            orders.add(id);
            int[] t = new int[4];
            for(int j = 0; j < 4; j ++){
                t[j] = Integer.parseInt(st.nextToken());
            }
            student[id] = new Seat(id, -1, -1, 0, 0, t);
        }
        System.out.println(solution(orders));
    }

    public static int solution(ArrayList<Integer> orders){
        for(int i = 0; i < orders.size(); i ++){
            Seat pos = findSpot(student[orders.get(i)]);
            student[orders.get(i)].r = pos.r;
            student[orders.get(i)].c = pos.c;
            board[pos.r][pos.c] = student[orders.get(i)].id;
        }
        return getSatisfaction();
    }

    public static Seat findSpot(Seat target){
        ArrayList<Seat> tempArr = new ArrayList<>();
        for(int r = 0; r < N; r ++){
            for(int c = 0; c < N; c ++){
                if(board[r][c] == 0){
                    Seat temp = getSpaceCount(r, c, target);
                    tempArr.add(new Seat(target.id, r, c, temp.empty, temp.close, target.favorite));
                }
            }
        }
        Collections.sort(tempArr);
        return tempArr.get(0);
    }

    public static Seat getSpaceCount(int r, int c, Seat target){
        int emptyCount = 0;
        int closeCount = 0;
        for(int i = 0; i < dx.length; i ++){
            int nr = r + dx[i];
            int nc = c + dy[i];
            if(nr < 0 || nc < 0 || nr >= N || nc >= N){
                continue;
            }else if(board[nr][nc] > 0){
                for(int j = 0; j < target.favorite.length; j ++){
                    if(target.favorite[j] == board[nr][nc]){
                        closeCount ++;
                    }
                }
            }else{
                emptyCount ++;
            }
        }
        return new Seat(target.id, r, c, emptyCount, closeCount, target.favorite);
    }

   public static int getSatisfaction(){
        int ret = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j ++){
                int cnt = 0;
                int id = board[i][j];
                if(id != 0){
                    int[] arr = student[id].favorite;
                    for(int k = 0; k < arr.length; k ++){
                        for(int l = 0; l < dx.length; l ++){
                            int nr = i + dx[l];
                            int nc = j + dy[l];
                            if(nr < 0 || nc < 0 || nr >= N || nc >= N){
                                continue;
                            }
                            if(board[nr][nc] == arr[k]){
                                cnt ++;
                            }
                        }
                    }
                    if(cnt == 1) ret += 1;
                    else if(cnt == 2) ret += 10;
                    else if(cnt == 3) ret += 100;
                    else if(cnt == 4) ret += 1000;
                }
            }
        }
        return ret;
    }
}

