import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

	int R, C;
	char[][] map;
	boolean flag = false;
	boolean[][] v;
	int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for(int i = 0; i < R; i ++){
			String s = br.readLine();
			for(int j = 0; j < C; j ++){
				map[i][j] = s.charAt(j);
			}
		}

		int nums = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		solve(nums, st);
	}

	public void solve(int nums, StringTokenizer st){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < nums; i ++){
			int height = Integer.parseInt(st.nextToken());

			int[] pos = throwsSpear(height, i);
			if(pos[0] == -1){
				continue;
			}
			for(int d = 0; d < 4; d ++){
				v = new boolean[R][C];
				int nr = pos[0] + dirs[d][0];
				int nc = pos[1] + dirs[d][1];
				if(isOutRange(nr, nc)){
					continue;
				}
				if(!v[nr][nc] && map[nr][nc] == 'x'){
					flag = true;
					ArrayList<int[]> nodes = new ArrayList<>();
					dfs(new int[] { nr, nc }, nodes);
					if(flag){
						dropCluster(nodes);
						break;
					}
				}
			}
		}
		
		for(int r = 0; r < R; r ++){
			for(int c = 0; c < C; c ++){
				sb.append(map[r][c]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	public void dropCluster(ArrayList<int[]> nodes){
		int len = 987654321;
		Collections.sort(nodes, new Comparator<int[]>(){
			@Override
			public int compare(int[] o1, int[] o2){
				return o2[0] - o1[0];
			}
		});

		for(int[] p : nodes){
			int nr = p[0];
			while(nr + 1 < R && (v[nr + 1][p[1]] || map[nr + 1][p[1]] == '.')){
				nr ++;
			}
			len = Math.min(len, nr - p[0]);
		}

		for(int[] p : nodes){
			map[p[0]][p[1]] = '.';
			map[p[0] + len][p[1]] = 'x';
		}
	}

	public void dfs(int[] pos, ArrayList<int[]> nodes){
		if(v[pos[0]][pos[1]]) return;
		v[pos[0]][pos[1]] = true;
		nodes.add(new int[] { pos[0], pos[1] });
		if(pos[0] == R - 1){
			flag = false;
		}
		for(int d = 0; d < 4; d ++){
			int nr = pos[0] + dirs[d][0];
			int nc = pos[1] + dirs[d][1];
			if(isOutRange(nr, nc)){
				continue;
			}
			if(map[nr][nc] == '.'){
				continue;
			}
			dfs(new int[] { nr, nc }, nodes);
		}
	}

	public boolean isOutRange(int r, int c){
		return r < 0 || c < 0 || r >= R || c >= C;
	}

	public int[] throwsSpear(int height, int round){
		height = R - height;
		if(round % 2 == 0){
			for(int i = 0; i < C; i ++){
				if(map[height][i] == 'x'){
					map[height][i] = '.';
					return new int[] { height, i };
				}
			}
		}else{
			for(int i = C - 1; i >= 0; i --){
				if(map[height][i] == 'x'){
					map[height][i] = '.';
					return new int[] { height, i };
				}
			}
		}
		return new int[] { -1 };
	}
}
