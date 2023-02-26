import java.util.*;
import java.io.*;

class Main {

    static class Node{
        int s, e;
        public Node(int s, int e){
            this.s = s;
            this.e = e;
        }
    }
    static int N, T, P;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        Node[] clients = new Node[T];
        for(int i = 0; i < T; i ++){
            st = new StringTokenizer(br.readLine(), " ");

            int s = stringToTime(st.nextToken());
            int e = stringToTime(st.nextToken());
            clients[i] = new Node(s, e);
        }

        Arrays.sort(clients, new Comparator<>(){
            @Override
            public int compare(Node o1, Node o2){
                if(o1.s == o2.s) return o1.e - o2.e;
                return o1.s - o2.s;
            }
        });

        boolean[][] check = new boolean[1261][N];
        solution(check, clients);
        int answer = 0;
        for(int i = 540; i < 1260; i ++)
            if(!check[i][P - 1]) answer ++;
        System.out.println(answer);
    }

    public static void solution(boolean[][] check, Node[] clients) {
        for(int i = 0; i < clients.length; i ++){
            int seat_index = findSeat(check, clients[i]);
            for(int j = clients[i].s; j < clients[i].e; j ++)
                check[j][seat_index] = true;
        }
    }

    public static int findSeat(boolean[][] check, Node client){
        int seat_index = 0;
        int max_dist = 0;
        for(int i = 0; i < N; i ++){
            if(!check[client.s][i]){
                int dist = getDistance(client.s, i, check);
                if(dist > max_dist){
                    max_dist = dist;
                    seat_index = i;
                }
            }
        }
        return seat_index;
    }

    public static int getDistance(int time, int seat_index, boolean[][] check){
        int dist = 101;
        for(int i = 0; i < N; i ++){
            if(i == seat_index) continue;
            if(check[time][i]){
                dist = Math.min(dist, Math.abs(i - seat_index));
            }
        }
        return dist ;
    }

    public static int stringToTime(String str){
        String h = str.substring(0, 2);
        String m = str.substring(2);

        return (Integer.parseInt(h) * 60) + Integer.parseInt(m);
    }
}