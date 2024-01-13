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
    StringBuilder[] sb;

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        sb = new StringBuilder[C];

        if(R == 2){
            System.out.println(0);
            return;
        }

        for(int r = 0; r < R; r ++){
            String str = br.readLine();
            for(int c = 0; c < C; c ++){
                if(sb[c] == null){
                    sb[c] = new StringBuilder();
                }
                sb[c].append(str.charAt(c));
            }
        }

        System.out.println(bs());
    }

    public int bs(){
        int l = 0, r = R;
        int ret = 987654321;
        while(l <= r){
            int mid = (r + l) / 2;
            if(!check(mid)){
                ret = mid;
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }

        if(ret == 987654321)
            return 0;

        return ret - 1;
    }

    public boolean check(int mid){
        HashSet<String> set = new HashSet<>();
        for(int c = 0; c < C; c ++){
            String str = sb[c].substring(mid);
            if(!set.contains(str)){
                set.add(str);
            }else{
                return false;
            }
        }
        return true;
    }
}

