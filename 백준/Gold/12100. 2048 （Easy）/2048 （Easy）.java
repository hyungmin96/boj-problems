import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {
	
	int N, answer = 0;
	int[][] map;
	int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for(int i = 0; i < N; i ++){
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j ++){
				map[i][j] = Integer.parseInt(st.nextToken());
				answer = Math.max(answer, map[i][j]);
			}
		}
		dfs(0, map);
		System.out.println(answer);
	}

	public void down(int[][] tmp_map) {
		for (int c = 0; c < N; c++) {
			int row = N - 1;
			for (int i = N - 2; i >= 0; i --) {
				if (tmp_map[i][c] > 0) {
					if (tmp_map[i][c] == tmp_map[row][c]) {
						tmp_map[row][c] *= 2;
						answer = Math.max(answer, tmp_map[row][c]);
						tmp_map[i][c] = 0;
						row --;
					}else if(tmp_map[row][c] == 0){
						tmp_map[row][c] = tmp_map[i][c];
						tmp_map[i][c] = 0;
					}else{
						int tmp = tmp_map[i][c];
						tmp_map[i][c] = 0;
						tmp_map[row - 1][c] = tmp;
						row --;
					}
				}
			}
		}
	}

	public void right(int[][] tmp_map){
		for(int i = 0; i < N; i ++){
			int col = N - 1;
			for(int c = N - 2; c >= 0; c --){
				if(tmp_map[i][c] > 0){
					if(tmp_map[i][c] == tmp_map[i][col]){
						tmp_map[i][col] *= 2;
						answer = Math.max(answer, tmp_map[i][col]);
						tmp_map[i][c] = 0;
						col --;
					}else if(tmp_map[i][col] == 0){
						tmp_map[i][col] = tmp_map[i][c];
						tmp_map[i][c] = 0;
					}else{
						int tmp = tmp_map[i][c];
						tmp_map[i][c] = 0;
						tmp_map[i][col - 1] = tmp;
						col --;
					}
				}
			}
		}
	}

	public void up(int[][] tmp_map) {
		for (int c = 0; c < N; c++) {
			int row = 0;
			for (int i = 1; i < N; i++) {
				if (tmp_map[i][c] > 0) {
					if (tmp_map[i][c] == tmp_map[row][c]) {
						tmp_map[row][c] *= 2;
						answer = Math.max(answer, tmp_map[row][c]);
						tmp_map[i][c] = 0;
						row++;
					}else if(tmp_map[row][c] == 0){
						tmp_map[row][c] = tmp_map[i][c];
						tmp_map[i][c] = 0;
					}else{
						int tmp = tmp_map[i][c];
						tmp_map[i][c] = 0;
						tmp_map[row + 1][c] = tmp;
						row ++;
					}
				}
			}
		}
	}

	public void left(int[][] tmp_map){
		for(int i = 0; i < N; i ++){
			int col = 0;
			for(int c = 1; c < N; c ++){
				if(tmp_map[i][c] > 0){
					if(tmp_map[i][c] == tmp_map[i][col]){
						tmp_map[i][col] *= 2;
						answer = Math.max(answer, tmp_map[i][col]);
						tmp_map[i][c] = 0;
						col ++;
					}else if(tmp_map[i][col] == 0){
						tmp_map[i][col] = tmp_map[i][c];
						tmp_map[i][c] = 0;
					}else{
						int tmp = tmp_map[i][c];
						tmp_map[i][c] = 0;
						tmp_map[i][col + 1] = tmp;
						col ++;
					}
				}
			}
		}
	}


	public void dfs(int depth, int[][] cur){
		if(depth == 5){
			return ;
		}

		for(int d = 0; d < 4; d ++){
			int[][] tmp = copy(cur);
			switch(d){
				case 0:
					up(tmp);
					break;
				case 1:
					right(tmp);
					break;
				case 2:
					left(tmp);
					break;
				case 3:
					down(tmp);
					break;
			}
			dfs(depth + 1, tmp);
		}
	}

	public int[][] copy(int[][] m){
		int[][] tmp = new int[N][N];
		for(int r = 0; r < N; r ++){
			for(int c = 0; c < N; c ++){
				tmp[r][c] = m[r][c];
			}
		}

		return tmp;
	}
}
