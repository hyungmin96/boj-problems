import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {
	
	int N;
	int[][] edge;
	boolean[] v;
	long[] dist;
	ArrayList<int[]>[] nodes;

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nodes = new ArrayList[N];
		edge = new int[N - 1][3];
		dist = new long[N];
		for(int i = 0; i < N; i ++){
			nodes[i] = new ArrayList<>();
		}

		for(int i = 0; i < N - 1; i ++){
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			nodes[from].add(new int[] { to, cost });
			nodes[to].add(new int[] { from, cost });
			
			edge[i] = new int[] { from , to, cost };
		}

		long max = Integer.MIN_VALUE;
		for(int[] e : edge){
			int cost = e[2];
			
			v = new boolean[N];
			int n1 = dfs(e[0], e[0], e[1]);

			v = new boolean[N];
			dfs(n1, e[0], e[1]);
			// int n2 = dfs(n1, e[0], e[1]);
			// long t1 = dist[n2];

			v = new boolean[N];
			int n2 = dfs(e[1], e[0], e[1]);

			v = new boolean[N];
			dfs(n2, e[0], e[1]);
			// n2 = dfs(n1, e[0], e[1]);
			// long t2 = dist[n2];

			max = Math.max(max, dist[n1] + dist[n2] + cost);
		}
		System.out.println(max);
	}

	public int dfs(int node, int e1, int e2){
		dist[node] = 0;
		v[node] = true;
		int cur_node = node;
		for(int[] next : nodes[node]){
			if((e1 == next[0] && e2 == node) || (e1 == node && e2 == next[0])){
				continue;
			}
			if(v[next[0]]){
				continue;
			}
			int tmp_node = dfs(next[0], e1, e2);
			if(dist[node] < dist[next[0]] + next[1]){
				dist[node] = dist[next[0]] + next[1];
				cur_node = tmp_node;
			}
		}
		return cur_node;
	}
}
