import java.io.*;
import java.util.*;

public class Main{
	static final int INF = Integer.MAX_VALUE;
	static int N, M, S, D;
	static ArrayList<int[]>[] nodes;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		nodes = new ArrayList[N + 1];
		for(int i = 0; i <= N; i ++){ nodes[i] = new ArrayList<>(); }
		for(int i = 0; i < M; i ++){
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			nodes[from].add(new int[] { to, weight });
			nodes[to].add(new int[] { from, weight });
		}
		st = new StringTokenizer(br.readLine(), " ");
		S = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		System.out.println(bfs());
	}

	public static int bfs(){
		int[] dp = new int[N + 1];
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
			@Override
			public int compare(int[] o1, int[] o2){
				return o2[1] - o1[1];
			}
		});
		pq.offer(new int[] { S, INF });
		while(!pq.isEmpty()){
			int[] cur = pq.poll();
			if(cur[0] == D) return dp[D];
			for(int[] next : nodes[cur[0]]){
				if(dp[next[0]] < Math.min(cur[1], next[1])){
					dp[next[0]] = Math.min(cur[1], next[1]);
					pq.offer(new int[] { next[0], Math.min(cur[1], next[1]) });
				}
			}
		}

		return -1;
	}
}