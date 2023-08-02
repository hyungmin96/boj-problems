import java.util.*;
import java.io.*;

public class Main {

	static int n, m;
	static int[][] dirs = {{ -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }};
	static int[][] map, temp;
	static ArrayList<int[]> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		temp = new int[n][m];
		for(int i = 0; i < n; i ++){
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < m; j ++){
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if(num == 1) list.add(new int[] { i, j });
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int sr = Integer.parseInt(st.nextToken()) - 1;
		int sc = Integer.parseInt(st.nextToken()) - 1;
		int er = Integer.parseInt(st.nextToken()) - 1;
		int ec = Integer.parseInt(st.nextToken()) - 1;

		System.out.println(bfs(h, w, sr, sc, er, ec));
	}

	public static int bfs(int h, int w, int sr, int sc, int er, int ec){
		boolean[][] check = new boolean[n][m];
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] { sr, sc, 0 });
		check[sr][sc] = true;

		while(!que.isEmpty()){
			int[] cur = que.poll();
			if(cur[0] == er && cur[1] == ec) return cur[2];
			for(int d = 0; d < 4; d ++){
				int nr = cur[0] + dirs[d][0];
				int nc = cur[1] + dirs[d][1];
				if (nr < 0 || nc < 0 || nr >= n || nc >= m) continue;
				if (temp[nr][nc] == 1) continue;
				if (check[nr][nc]) continue;

				if(!check(cur[0], cur[1], w, h)) continue;
				check[nr][nc] = true;
				que.offer(new int[] { nr, nc, cur[2] + 1 });
			}
		}
		return -1;
	}

	public static boolean check(int cr, int cc, int w, int h){
		int nr = cr + h;
		int nc = cc + w;

		// 위치한 노드에서 + w, h를 하면 w+1이 되기때문에 -1을 더해준다.
		if(nr - 1 >= n || nc - 1 >= m) return false;
		for(int[] l : list){
			if(l[0] >= cr && l[0] <= nr - 1 && l[1] >= cc && l[1] <= nc - 1) return false;
		}
		return true;
	}
}