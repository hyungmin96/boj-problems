import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int num[][];
	static int pick[], mxT[];
	static int mx, res, mxS;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		num = new int [N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				num[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		pick = new int [3];
		mxT = new int [N];
		com(0, 0);
		System.out.println(res);
	}
	
	static void com(int idx, int cnt) {
		if(cnt == 3) {
			mx = 0;
			for (int i = 0; i < N; i++) {
				mxS = 0;
				for (int j = 0; j < 3; j++) {
					if(mxS < num[i][pick[j]]) {
						mxS = num[i][pick[j]];
					}
				}
				mxT[i] = mxS;
			}
			for (int i = 0; i < N; i++) {
				mx += mxT[i];
			}
			if(mx > res) {
				res = mx;
			}
			return;
		}
		
		for (int i = idx; i < M; i++) {
			pick[cnt] = i;
			com(idx + 1, cnt + 1);
		}
	}
}