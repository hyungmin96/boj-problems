import java.io.*;
import java.util.*;

public class Main {

    public static class Pair{
        int num, repeat;
        public Pair(int num, int repeat) { this.num = num; this.repeat = repeat; }
    }
    static int rowSize = 3, colSize = 3;
    static int[][] arr = new int[100][100];

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for(int i = 0; i < 3; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < 3; j ++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution(r - 1, c - 1, k));
    }

    public static int solution(int r, int c, int k){
        int answer = 0;
        for(; answer <= 100; answer ++){
            if(arr[r][c] == k) return answer;
            if(rowSize >= colSize){
                rowSort();
            }else{
                colSort();
            }
        }        
        return -1;
    }

    public static void rowSort(){
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>(new Comparator<>(){
            @Override
            public int compare(Pair p1, Pair p2){
                if(p1.repeat == p2.repeat) return p1.num - p2.num;
                return p1.repeat - p2.repeat;
            }
        });

        for(int i = 0; i < rowSize; i ++){
            HashMap<Integer, Integer> map = new HashMap<>();
            for(int j = 0; j < colSize; j ++){
                if(arr[i][j] == 0) continue;
                map.put(arr[i][j], map.getOrDefault(arr[i][j], 0) + 1);
            }

            for(int k : map.keySet()){
                pq.offer(new Pair(k, map.get(k)));
            }

            int idx = 0;
            colSize = Math.max(colSize, pq.size() * 2);
            int[] temp = new int[pq.size() * 2];
            while(!pq.isEmpty()){
                Pair p = pq.poll();
                temp[idx ++] = p.num;
                temp[idx ++] = p.repeat;
            }
            for(int j = 0; j < temp.length; j ++){
                arr[i][j] = temp[j];
            }
            for(int j = idx; j < 100; j ++){
                arr[i][j] = 0;
            }
        }
    }

    public static void colSort(){
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>(new Comparator<>(){
            @Override
            public int compare(Pair p1, Pair p2){
                if(p1.repeat == p2.repeat) return p1.num - p2.num;
                return p1.repeat - p2.repeat;
            }
        });
        
        for(int i = 0; i < colSize; i ++){
            HashMap<Integer, Integer> map = new HashMap<>();
            for(int j = 0; j < rowSize; j ++){
                if(arr[j][i] == 0) continue;
                map.put(arr[j][i], map.getOrDefault(arr[j][i], 0) + 1);
            }

            for(int k : map.keySet()){
                pq.offer(new Pair(k, map.get(k)));
            }

            int idx = 0;
            rowSize = Math.max(rowSize, pq.size() * 2);
            int[] temp = new int[pq.size() * 2];
            while(!pq.isEmpty()){
                Pair p = pq.poll();
                temp[idx ++] = p.num;
                temp[idx ++] = p.repeat;
            }
            for(int j = 0; j < temp.length; j ++){
                arr[j][i] = temp[j];
            }
            for(int j = idx; j < 100; j ++){
                arr[j][i] = 0;
            }
        }
    }
}