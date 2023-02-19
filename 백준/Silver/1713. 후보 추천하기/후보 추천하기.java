import java.io.*;
import java.util.*;

public class Main {

    public static class Pair{
        int idx, like, time;
        public Pair(int idx, int like, int time){ this.idx = idx; this.like = like; this.time = time; }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        PriorityQueue<Pair> pq = new PriorityQueue<Pair>(new Comparator<>(){
            @Override
            public int compare(Pair p1, Pair p2){
                if(p1.like == p2.like) return p2.time - p1.time;
                return p1.like - p2.like;
            }
        });

        int[] people = new int[101];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < m; i ++){
            int num = Integer.parseInt(st.nextToken());
            if(pq.size() < n && people[num] == 0){
                pq.offer(new Pair(num, 1, 0));
            }else{
                if(people[num] > 0){
                    ArrayList<Pair> temp = new ArrayList<>();
                    while(!pq.isEmpty()){
                        Pair p = pq.poll();
                        if(num == p.idx){
                            temp.add(new Pair(num, p.like + 1, p.time));
                        }else{
                            temp.add(new Pair(p.idx, p.like, p.time));
                        }
                    }
                    for(Pair p : temp) pq.offer(p);
                }else{
                    int deleteIdx = pq.poll().idx;
                    people[deleteIdx] = 0;
                    pq.offer(new Pair(num, 1, 0));
                }
            }
            ArrayList<Pair> time = new ArrayList<>();
            while(!pq.isEmpty()) time.add(pq.poll());
            for(Pair p : time) pq.offer(new Pair(p.idx, p.like, p.time + 1));
            people[num] ++;
        }

        ArrayList<Integer> list = new ArrayList<>();
        while(!pq.isEmpty()) list.add(pq.poll().idx);
        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        for(Integer i : list) sb.append(i + " ");
        System.out.println(sb.toString());
    }
}