import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int N, K;
    int[][] map;

    public void solution() throws IOException{
        // StringBuilder t = new StringBuilder();
        // for(int i = 0; i < 88; i ++){
        //     t.append(Math.abs(1) + " ");
        // }
        // System.out.println(t.toString());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[101][101];

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i ++)
            arr[i] = Integer.parseInt(st.nextToken());

        if(cal(arr)){
            System.out.println(0);
            return;
        }

        int cnt = 0;
        while(true){
            putFish(arr);
            map = new int[101][101];
            int[] area = stackFish(arr);
            spread(area);
            arr = horizontalArr();
            stackArr(arr);
            spread(new int[] { 4, arr.length / 4 });
            arr = horizontalArr();
            cnt ++;
            if(cal(arr)) break;
        }
        System.out.println(cnt);
    }

    public boolean cal(int[] arr){
        int max = -1, min = 987654321;
        for(int i = 0; i < arr.length; i ++){
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }

        return max - min <= K;
    }
    
    public void stackArr(int[] arr){
        int[][] tmp = new int[2][arr.length / 2];
        for(int i = 0; i < arr.length / 2; i ++){
            tmp[0][i] = arr[arr.length / 2 - i - 1];
        }
        for(int i = 0; i < arr.length / 2; i ++){
            tmp[1][i] = arr[arr.length / 2 + i];
        }

        int[][] tmp2 = new int[4][arr.length / 4];
        for(int j = 0; j < 2; j ++){
            for(int i = 0; i < arr.length / 4; i ++){
                tmp2[j][i] = tmp[2 - j - 1][arr.length / 4 - i - 1];                
            }
        }
        
        for(int j = 0; j < 2; j ++){
            for(int i = 0; i < arr.length / 4; i ++){
                tmp2[j + 2][i] = tmp[j][arr.length / 4 + i];                
            }
        }
        
        map = new int[101][101];

        for(int i = 97; i < 101; i ++){
            for(int j = 0; j < arr.length / 4; j ++){
                map[i][j] = tmp2[i - 97][j];
            }
        }
    }

    public int[] horizontalArr(){
        ArrayList<Integer> list = new ArrayList<>();
        for(int j = 0; j < 101; j ++){
            for(int i = 100; i >= 0; i --){
                if(map[i][j] > 0){
                    list.add(map[i][j]);
                }else{
                    break;
                }
            }
        }

        int[] arr = new int[list.size()];
        for(int i = 0; i < arr.length; i ++){
            arr[i] = list.get(i);
        }

        return arr;
    }

    public void spread(int[] area){
        int[][] dirs = {{ -1, 0 }, { 0, 1 }, { 1, 0}, { 0, -1 }};
        int[][] tmp = new int[101][101];
        
        for(int i = 100 - area[0] - 1; i < 101; i ++){
            for(int j = 0; j < area[1]; j ++){
                if(map[i][j] > 0){
                    for(int d = 0; d < 4; d ++){
                        int nr = i + dirs[d][0];
                        int nc = j + dirs[d][1];
                        if(nr < 0 || nc < 0 || nr >= 101 || nc >= 101 || map[nr][nc] == 0) continue;
                        if(map[i][j] > map[nr][nc] && (map[i][j] - map[nr][nc]) / 5 > 0){
                            tmp[i][j] -= (map[i][j] - map[nr][nc]) / 5;
                            tmp[nr][nc] += (map[i][j] - map[nr][nc]) / 5;
                        }
                    }
                }
            }
        }

        for(int i = 0; i < 101; i ++){
            for(int j = 0; j < 101; j ++){
                map[i][j] += tmp[i][j];
            }
        }
    }

    public int[] stackFish(int[] arr){
        // 물고기 쌓아올리기
        int width = 1, height = 1;
        int width_size = 0, height_size = 1;
        Queue<Integer> q = new LinkedList<>();
        
        for(int a : arr) q.offer(a);
        while(width <= q.size()){
            //회전 
            int[][] tmp = new int[height][width];
            for(int i = 100 - height_size + 1; i < 101; i ++){
                for(int j = 0; j < width_size; j ++){
                    tmp[j][100 - i] = map[i][j];
                }
            }

            for(int i = 0; i < height; i ++){
                for(int j = 0; j < width; j ++){
                    if(height == width){
                        map[100 - (height - 1) + i][j] = tmp[i][j];
                    }else{
                        map[100 - (height - 1) + i][j] = tmp[i][j];
                    }
                }
            }

            // 가장 밑 변에 어항 내려두기
            for(int j = 0; j < width && !q.isEmpty(); j ++){
                map[100][j] = q.poll();
            }            

            // 현재 크기 늘리기
            if(height_size == width_size){
                height_size ++;
            }else{
                width_size ++;
            }

            // 크기 늘리기
            if(height == width){
                height ++;
            }else{
                width ++;
            }
        }
        
        for(int j = 0; j < 100; j ++){
            if(map[100][j] > 0)
                width = j + 1;
        }

        while(!q.isEmpty()){
            map[100][width ++] = q.poll();
        }

        height = 0;
        width = 0;
        for(int j = 0; j < 101; j ++){
            if(map[100][j] > 0){
                width = j;
            }
        }
        for(int j = 0; j < width; j ++){
            for(int i = 100; i >= 0; i --){
                if(map[i][j] > 0)
                    height = Math.max(height, 100 - i);
            }
        }
        return new int[] { height + 1, width + 1 };
    }

    public void putFish(int[] arr){
        // 제일 적은 물고기 어항에 물고기 + 1
        int min = 987654321;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < N; i ++){
            if(min >= arr[i]){
                if(min > arr[i]){
                    min = arr[i];
                    list.clear();
                }
                list.add(i);
            }
        }

        for(int idx : list)
            arr[idx] ++;
    }
}

