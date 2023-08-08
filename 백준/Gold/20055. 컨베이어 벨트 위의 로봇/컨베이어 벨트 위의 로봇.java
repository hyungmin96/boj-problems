import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    public int N, K, total;
    public Box[] arr;

    public class Box{
        int durability;
        boolean empty;
        public Box(int durability, boolean empty){
            this.durability = durability;
            this.empty = empty;
        }
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        arr = new Box[2 * N];
        for(int i = 0; i < 2 * N; i ++){
            int durability = Integer.parseInt(st.nextToken());
            arr[i] = new Box(durability, true);
        }

        int time = 0;
        while(total < K){
            time ++;
            rotate();
            removeBox();

            move();
            removeBox();

            putRobot();
        }

        System.out.println(time);
    }

    public void removeBox(){
        arr[N - 1].empty = true;
    }

    public void putRobot(){
        if(arr[0].empty && arr[0].durability > 0){
            arr[0].durability --;
            arr[0].empty = false;
            if(arr[0].durability == 0){
                total ++;
            }
        }
    }

    public void move(){
        for(int i = N - 1; i > 0; i --){
            if(arr[i].empty && arr[i].durability > 0){
                if(!arr[i - 1].empty){
                    arr[i].durability --;
                    arr[i].empty = false;

                    arr[i - 1].empty = true;
                    if(arr[i].durability == 0){
                        total ++;
                    }
                }
            }
        }
        arr[0].empty = true;
    }

    public void rotate(){
        Box tmp = arr[2 * N - 1];
        for(int i = 2 * N - 1; i > 0; i --){
            arr[i] = arr[i - 1];
        }
        arr[0] = tmp;
    }
}