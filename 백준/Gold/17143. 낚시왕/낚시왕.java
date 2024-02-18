import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

	public class Shark{
		int r, c, s, d, size;
		public Shark(int r, int c, int s, int d, int size){
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.size = size;
		}
	}

	int N, M, K;
	int[][] dirs = {{-1,0},{1,0},{0,1},{0,-1}};
	int[][] map;
	HashMap<Integer, Shark> sharks = new HashMap<>();

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i = 0; i < K; i ++){
			st = new StringTokenizer(br.readLine(), " ");

			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int size = Integer.parseInt(st.nextToken());

			sharks.put(size, new Shark(r, c, s, d, size));
			map[r][c] = size;
		}

		solve();
	}

	public void solve(){
		int answer = 0;
		for(int c = 0; c < M; c ++){
			//System.out.println((c + 1) + "===============");
			answer += catchShark(c);
			moveSharks();
		}
		System.out.println(answer);
	}

	public void moveSharks(){
		int[][] tmp = new int[N][M];
		ArrayList<Integer> deleted = new ArrayList<>();
		for(int key : sharks.keySet()){
			Shark s = sharks.get(key);
			map[s.r][s.c] = 0;
			if(s.d == 2 || s.d == 3){
				s = moveLeftOrRight(s);
			}else{
				s = moveUpOrDown(s);
			}
			
			if(tmp[s.r][s.c] == 0){
				tmp[s.r][s.c] = s.size;
			}else{
				int cur = tmp[s.r][s.c];
				if(cur < s.size){
					deleted.add(cur);
					tmp[s.r][s.c] = s.size;
				}else{
					deleted.add(s.size);
				}
			}
		}

		for(int d : deleted){
			sharks.remove(d);
		}

		for(int key : sharks.keySet()){
			Shark s = sharks.get(key);
			map[s.r][s.c] = s.size;
			//System.out.println("size " + s.size + " |row " + s.r + " |col " + s.c + " |d " + s.d);
		}
	}

	public Shark moveUpOrDown(Shark s){
		int dist = s.s;
		int cur_d = s.d;

		dist = dist % (2 * (N - 1));
		while(dist > 0){
			int next = s.r + (dist * dirs[cur_d][0]);
			if(next < 0){
				cur_d = 1;
				dist -= s.r;
				s.r = 0;
			}else if(next > N - 1){
				cur_d = 0;
				dist -= ((N - 1) - s.r);
				s.r = N - 1;
			}else{
				s.r = next;
				dist = 0;
			}
		}

		s.d = cur_d;
		return s;
	}

	public Shark moveLeftOrRight(Shark s){
		int dist = s.s;
		int cur_d = s.d;

		dist = dist % (2 * (M - 1));
		while(dist > 0){
			int next = s.c + (dist * dirs[cur_d][1]);
			if(next > (M - 1)){
				cur_d = 3;
				dist -= ((M - 1) - s.c);
				s.c = M - 1;
			}else if(next < 0){
				cur_d = 2;
				dist -= s.c;
				s.c = 0;
			}else{
				s.c = next;
				dist = 0;
			}
		}

		s.d = cur_d;
		return s;
	}

	public int catchShark(int col){
		for(int i = 0; i < N; i ++){
			if(map[i][col] > 0){
				int size = map[i][col];
				sharks.remove(map[i][col]);
				map[i][col] = 0;
				return size;
			}
		}
		return 0;
	}
}
